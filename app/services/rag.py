import numpy as np
from typing import List, Tuple

def embed_text(text: str) -> list[float]:
    # Tiny placeholder embedding (hash trick). Replace with a real model later.
    vec = np.zeros(256, dtype=np.float32)
    for w in text.lower().split():
        vec[hash(w) % 256] += 1.0
    n = np.linalg.norm(vec) + 1e-8
    return (vec / n).tolist()

def topk_chapters(query: str, chapters: List[Tuple[str, str, list[float]]], k=3) -> List[int]:
    q = np.array(embed_text(query))
    sims = []
    for i, (_, _, emb) in enumerate(chapters):
        v = np.array(emb)
        sim = float(np.dot(q, v) / (np.linalg.norm(q)*np.linalg.norm(v)+1e-8))
        sims.append((sim, i))
    sims.sort(reverse=True)
    return [i for _, i in sims[:k]]
