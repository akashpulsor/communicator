"""
YourTTS streaming stub for Hinglish.
Replace this with your actual YourTTS inference (HTTP server or in-proc).
Keep the same async generator interface.
"""
import asyncio
from typing import AsyncIterator

FRAME_MS = 240
SAMPLE_RATE = 22050
BYTES_PER_MS = int(SAMPLE_RATE * 2 / 1000)  # 16-bit mono

async def tts_stream(text_iter: AsyncIterator[str]) -> AsyncIterator[bytes]:
    # Emit silent frames per chunk (wire test). Replace with real TTS frames.
    async for _ in text_iter:
        await asyncio.sleep(FRAME_MS / 1000)
        yield b"\x00" * (BYTES_PER_MS * FRAME_MS)
