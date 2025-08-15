from fastapi import APIRouter, UploadFile, File, Depends, BackgroundTasks, HTTPException
from sqlalchemy.orm import Session
from ..database import get_db
from ..models import Document, Chapter, ChapterEmbedding
from ..utils.storage import save_upload
from ..services.pdf_processor import extract_pdf_text, propose_chapters_with_llm, heuristic_fallback
from ..services.rag import embed_text
from ..auth import get_current_user_id
import asyncio

router = APIRouter(prefix="/documents", tags=["documents"])

@router.post("/upload")
async def upload_pdf(
    file: UploadFile = File(...),
    db: Session = Depends(get_db),
    user_id: str = Depends(get_current_user_id),
    bg: BackgroundTasks = None
):
    if file.content_type not in ("application/pdf",):
        raise HTTPException(400, "Only PDF allowed")
    path = save_upload(file.filename, file.file)
    doc = Document(user_id=user_id, name=file.filename, url=path, status="uploaded")
    db.add(doc); db.commit(); db.refresh(doc)

    def process_document(doc_id: str, path: str):
        from ..database import SessionLocal
        s = SessionLocal()
        try:
            full_text = extract_pdf_text(path)
            try:
                proposed = asyncio.run(propose_chapters_with_llm(full_text))
            except Exception:
                proposed = heuristic_fallback(full_text)

            d = s.query(Document).get(doc_id)
            d.status = "processed"
            s.add(d); s.commit()

            for i, ch in enumerate(proposed):
                ch_text = full_text[ch["start_idx"]: ch["end_idx"]]
                row = Chapter(document_id=d.id, idx=i, title=ch["title"], text=ch_text)
                s.add(row); s.flush()
                s.add(ChapterEmbedding(chapter_id=row.id, embedding=embed_text(ch_text)))
            s.commit()
        finally:
            s.close()

    if bg:
        bg.add_task(process_document, str(doc.id), path)
    else:
        process_document(str(doc.id), path)

    return {"document_id": str(doc.id), "status": "processing"}

@router.get("")
def list_docs(db: Session = Depends(get_db), user_id: str = Depends(get_current_user_id)):
    docs = db.query(Document).filter(Document.user_id == user_id).all()
    return [{"id": str(d.id), "name": d.name, "status": d.status} for d in docs]

@router.get("/{doc_id}/chapters")
def list_chapters(doc_id: str, db: Session = Depends(get_db), user_id: str = Depends(get_current_user_id)):
    doc = db.query(Document).filter(Document.id == doc_id, Document.user_id == user_id).first()
    if not doc:
        raise HTTPException(404, "Document not found")
    ch = db.query(Chapter).filter(Chapter.document_id == doc.id).order_by(Chapter.idx.asc()).all()
    return [{"id": str(c.id), "idx": c.idx, "title": c.title} for c in ch]
