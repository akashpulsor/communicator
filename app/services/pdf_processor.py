from pdfminer.high_level import extract_text
from typing import List, Dict
from ..services.ollama_client import generate_json

def extract_pdf_text(path: str) -> str:
    return extract_text(path)

HINGLISH_CHAPTERIZER_SYS = (
    "Split long PDF text into logical chapters. Return strict JSON as a list of "
    '{"title": str, "start_idx": int, "end_idx": int} with 0-based char indices. '
    "Keep 5-15 chapters if possible."
)

async def propose_chapters_with_llm(full_text: str) -> List[Dict]:
    text = full_text[:200000]  # safety cap
    prompt = f"Text:\n{text}\n\nReturn JSON now."
    chapters = await generate_json(HINGLISH_CHAPTERIZER_SYS, prompt)
    if not isinstance(chapters, list):
        raise ValueError("Bad JSON")
    cleaned = []
    for i, ch in enumerate(chapters):
        title = str(ch.get("title", f"Chapter {i+1}"))
        s = max(0, int(ch.get("start_idx", 0)))
        e = max(s+1, int(ch.get("end_idx", s+1)))
        cleaned.append({"title": title, "start_idx": s, "end_idx": e})
    return cleaned

def heuristic_fallback(full_text: str):
    import re
    parts = [p for p in re.split(r"\n{2,}", full_text) if p.strip()]
    chapters = []
    cursor = 0
    for i, p in enumerate(parts):
        t = p.strip().split("\n", 1)[0][:60] or f"Section {i+1}"
        s, e = cursor, cursor + len(p)
        chapters.append({"title": t, "start_idx": s, "end_idx": e})
        cursor = e + 2
    # merge toward ~10 parts
    if len(chapters) <= 12:
        return chapters
    step = max(1, len(chapters)//10)
    merged = []
    for i in range(0, len(chapters), step):
        chunk = chapters[i:i+step]
        merged.append({
            "title": f"Part {len(merged)+1}",
            "start_idx": chunk[0]["start_idx"],
            "end_idx": chunk[-1]["end_idx"]
        })
    return merged
