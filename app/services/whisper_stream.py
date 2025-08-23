"""
Streaming-ish wrapper for whisper.cpp (Hinglish). If whisper not configured,
falls back to a safe stub so you can test end-to-end.
"""
import asyncio, json, shutil, os, tempfile
from typing import AsyncIterator
from ..config import settings
import asyncio
import subprocess
import sys


# allow only 5 concurrent ffmpeg jobs at a time
FFMPEG_CONCURRENCY = 5
ffmpeg_semaphore = asyncio.Semaphore(FFMPEG_CONCURRENCY)

HAS_WHISPER = bool(settings.WHISPER_CPP_BIN) and shutil.which(settings.WHISPER_CPP_BIN) is not None

async def transcribe_stream(pcm_iter: AsyncIterator[bytes], sample_rate: int = 16000) -> AsyncIterator[dict]:
    if not HAS_WHISPER:
        # Fallback: emit a stub transcript so wiring works
        buf = bytearray()
        async for chunk in pcm_iter:
            buf.extend(chunk)
        yield {"type": "final", "text": "(dev) whisper.cpp not configured — please set WHISPER_CPP_BIN"}
        return

    # Write PCM to temp file
    with tempfile.NamedTemporaryFile(suffix=".pcm", delete=False) as f:
        async for chunk in pcm_iter:
            f.write(chunk)
        pcm_path = f.name
    print("PCM size:", os.path.getsize(pcm_path))
    wav_path = pcm_path.replace(".pcm", ".wav")
    out_prefix = wav_path.replace(".wav", "")   # whisper will add .json

    # Convert PCM → WAV
    cmd_wav = f"ffmpeg -f s16le -ar {sample_rate} -ac 1 -i {pcm_path} -y {wav_path}"
    print("Running:", cmd_wav)
    proc = await run_ffmpeg(cmd_wav)

    if proc.stdout:
        print("[ffmpeg stdout]", proc.stdout)

    if proc.returncode != 0:
        yield {"type": "final", "text": f"(ffmpeg failed: {proc.stderr[:200]})"}
        return

    # Run whisper.cpp
    cmd = f'{settings.WHISPER_CPP_BIN} -m {settings.WHISPER_MODEL} -f {wav_path} -oj -of {out_prefix}'
    print("Running:", cmd)
    proc2 = await run_and_log(
        f'{settings.WHISPER_CPP_BIN} -m {settings.WHISPER_MODEL} -f {wav_path} -oj -of {out_prefix}',
        "whisper.cpp"
    )
    
    if proc2 != 0:
        yield {"type": "final", "text": f"(whisper.cpp failed with code {proc2})"}
        return
    
    out_json = out_prefix + ".json"

    final_text = parse_whisper_json(out_json, pcm_path, wav_path)
    yield {"type": "final", "text": final_text}

async def run_ffmpeg(cmd: str):
    async with ffmpeg_semaphore:  # limit concurrent usage
        def _run():
            return subprocess.run(
                cmd, shell=True,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                text=True
            )
        return await asyncio.to_thread(_run)
    
async def run_and_log(cmd: str, name: str) -> int:
    """
    Run a shell command asynchronously, stream stdout/stderr line by line,
    and return the process exit code.
    Compatible with Windows (Python 3.13) and Linux/macOS.
    """
    print(f"Running {name}: {cmd}")

    if sys.platform.startswith("win"):
        # Windows: asyncio.create_subprocess_shell not supported in Python 3.13
        loop = asyncio.get_running_loop()

        def run_sync():
            proc = subprocess.Popen(
                cmd,
                shell=True,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                text=True,
                bufsize=1
            )
            for line in proc.stdout:
                print(f"[{name} stdout] {line.rstrip()}")
            for line in proc.stderr:
                print(f"[{name} stderr] {line.rstrip()}")
            proc.wait()
            return proc.returncode

        return await loop.run_in_executor(None, run_sync)

    else:
        # Linux/macOS: use proper asyncio subprocess
        proc = await asyncio.create_subprocess_shell(
            cmd,
            stdout=asyncio.subprocess.PIPE,
            stderr=asyncio.subprocess.PIPE
        )

        async def read_stream(stream, stream_name):
            async for line in stream:
                print(f"[{name} {stream_name}] {line.decode(errors='ignore').rstrip()}")

        await asyncio.gather(
            read_stream(proc.stdout, "stdout"),
            read_stream(proc.stderr, "stderr")
        )

        return await proc.wait()
    

def parse_whisper_json(out_json: str, pcm_path: str, wav_path: str) -> str:
    """
    Parse whisper.cpp JSON output and return concatenated transcript text.
    Handles both new (-oj) and old formats.
    """
    try:
        with open(out_json, "r", encoding="utf-8") as j:
            data = json.load(j)

        txt = ""

        # New format (preferred with -oj)
        if isinstance(data.get("transcription"), list):
            for seg in data["transcription"]:
                if isinstance(seg, dict):
                    txt += seg.get("text", "")

        # Old format (sometimes whisper.cpp outputs here)
        elif isinstance(data.get("result"), list):
            for seg in data["result"]:
                if isinstance(seg, dict):
                    txt += seg.get("text", "")

        return txt.strip()

    except Exception as e:
        # Debug dump for inspection
        try:
            debug_file = out_json + ".debug.json"
            shutil.copy(out_json, debug_file)
            print(f"[WARN] JSON parse failed, raw saved at {debug_file}")
        except:
            pass
        return f"(json parse failed: {e})"

    finally:
        for p in [pcm_path, wav_path, out_json]:
            try:
                os.remove(p)
            except:
                pass
