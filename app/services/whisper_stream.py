"""
Streaming-ish wrapper for whisper.cpp (Hinglish). If whisper not configured,
falls back to a safe stub so you can test end-to-end.
"""
import asyncio, json, shutil, os, tempfile
from typing import AsyncIterator
from ..config import settings

HAS_WHISPER = bool(settings.WHISPER_CPP_BIN) and shutil.which(settings.WHISPER_CPP_BIN) is not None

async def transcribe_stream(pcm_iter: AsyncIterator[bytes], sample_rate: int = 16000) -> AsyncIterator[dict]:
    if not HAS_WHISPER:
        # Fallback: emit a stub transcript so wiring works
        buf = bytearray()
        async for chunk in pcm_iter:
            buf.extend(chunk)
        yield {"type": "final", "text": "(dev) whisper.cpp not configured — please set WHISPER_CPP_BIN"}
        return

    # MVP: buffer → run once; for true streaming feed -su and parse partials
    with tempfile.NamedTemporaryFile(suffix=".pcm", delete=False) as f:
        async for chunk in pcm_iter:
            f.write(chunk)
        pcm_path = f.name

    wav_path = pcm_path.replace(".pcm", ".wav")
    cmd_wav = f"ffmpeg -f s16le -ar {sample_rate} -ac 1 -i {pcm_path} -y {wav_path}"
    proc = await asyncio.create_subprocess_shell(cmd_wav, stdout=asyncio.subprocess.DEVNULL, stderr=asyncio.subprocess.DEVNULL)
    await proc.wait()

    out_json = wav_path + ".json"
    cmd = f'{settings.WHISPER_CPP_BIN} -m {settings.WHISPER_MODEL} -f {wav_path} -oj -of {out_json}'
    proc2 = await asyncio.create_subprocess_shell(cmd, stdout=asyncio.subprocess.DEVNULL, stderr=asyncio.subprocess.DEVNULL)
    await proc2.wait()

    try:
        txt = ""
        with open(out_json, "r", encoding="utf-8") as j:
            data = json.load(j)
            for seg in data.get("result", []):
                txt += seg.get("text", "")
        yield {"type": "final", "text": txt.strip()}
    except Exception:
        yield {"type": "final", "text": ""}
    finally:
        for p in [pcm_path, wav_path, out_json]:
            try: os.remove(p)
            except: pass
