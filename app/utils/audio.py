import base64

def pcm16_from_base64(b64: str) -> bytes:
    return base64.b64decode(b64)

def base64_from_pcm16(data: bytes) -> str:
    return base64.b64encode(data).decode("ascii")

def duration_ms_from_pcm16(pcm: bytes, sample_rate: int = 16000) -> int:
    samples = len(pcm) // 2
    return int(1000 * samples / sample_rate)
