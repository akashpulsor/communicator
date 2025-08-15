from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from ..database import get_db
from ..models import Message
from ..auth import get_current_user_id

router = APIRouter(prefix="/messages", tags=["messages"])

@router.get("/{session_id}")
def list_messages(session_id: str, db: Session = Depends(get_db), user_id: str = Depends(get_current_user_id)):
    msgs = db.query(Message).filter(Message.session_id == session_id).order_by(Message.created_at.asc()).all()
    return [{"id": str(m.id), "role": m.role, "text": m.text} for m in msgs]
