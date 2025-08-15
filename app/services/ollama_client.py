import httpx, json
from ..config import settings

HINGLISH_SYSTEM = (
    "Tum ek friendly Hinglish AI ho. Short acknowledgements do ('haan', 'hmm', 'samjha'), "
    "phir concise aur helpful reply do. Hindi aur English ko naturally mix karo."
)

async def stream_llm(
    prompt_user: str,
    system: str = HINGLISH_SYSTEM,
    temperature: float = 0.3,
    model: str = None
):
    """
    Streams text chunks from Ollama /api/generate using Phi-3 by default.
    """
    if model is None:
        model = settings.OLLAMA_MODEL  # default "phi3:latest"
    payload = {
        "model": model,
        "prompt": f"<<SYS>>{system}<</SYS>>\nUser: {prompt_user}\nAssistant:",
        "stream": True,
        "options": {"temperature": temperature}
    }
    async with httpx.AsyncClient(timeout=None) as client:
        async with client.stream("POST", f"{settings.OLLAMA_URL}/api/generate", json=payload) as r:
            async for line in r.aiter_lines():
                if not line:
                    continue
                try:
                    obj = json.loads(line)
                    if "response" in obj:
                        yield obj["response"]
                except json.JSONDecodeError:
                    continue

async def generate_json(sys_prompt: str, text: str):
    """Ask the model for JSON; try to parse from the stream buffer."""
    buf = []
    async for chunk in stream_llm(text, system=sys_prompt, temperature=0.1):
        buf.append(chunk)
    whole = "".join(buf).strip()
    import re, json
    try:
        return json.loads(whole)
    except:
        m = re.search(r"\{.*\}|\[.*\]", whole, re.S)
        if m:
            return json.loads(m.group(0))
        raise ValueError("Model did not return JSON")
