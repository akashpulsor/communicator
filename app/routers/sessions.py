from fastapi import APIRouter, Depends, HTTPException, WebSocket, WebSocketDisconnect
from sqlalchemy.orm import Session
from typing import Dict, Any
import asyncio, json

from ..database import get_db
from ..models import Session as DBSession
from ..schemas import SessionCreate
from ..auth import get_current_user_id
from ..services.ollama_client import stream_llm
from ..services.whisper_stream import transcribe_stream
from ..services.yourtts_stream import tts_stream
from ..utils.audio import pcm16_from_base64, base64_from_pcm16

router = APIRouter(prefix="/sessions", tags=["sessions"])

@router.post("")
def create_session(payload: SessionCreate, db: Session = Depends(get_db), user_id: str = Depends(get_current_user_id)):
    s = DBSession(user_id=user_id, mode=payload.mode, document_id=payload.document_id, chapter_id=payload.chapter_id)
    db.add(s); db.commit(); db.refresh(s)
    return {"id": str(s.id), "mode": s.mode, "document_id": s.document_id, "chapter_id": s.chapter_id}

@router.get("")
def list_sessions(db: Session = Depends(get_db), user_id: str = Depends(get_current_user_id)):
    ss = db.query(DBSession).filter(DBSession.user_id == user_id).order_by(DBSession.started_at.desc()).all()
    return [{"id": str(x.id), "mode": x.mode, "document_id": x.document_id, "chapter_id": x.chapter_id} for x in ss]

# ---------------- Realtime WS ----------------
@router.websocket("/realtime/{session_id}")
async def realtime(ws: WebSocket, session_id: str):
    await ws.accept()
    state: Dict[str, Any] = {
        "buffer": bytearray(),
        "sample_rate": 16000,
        "barge": False,
    }

    async def buffered_iter():
        if state["buffer"]:
            yield bytes(state["buffer"])
            state["buffer"].clear()

    async def handle_turn(user_text: str):
        # stream LLM deltas
        async def text_iter():
            async for delta in stream_llm(user_text):
                await ws.send_text(json.dumps({"type":"llm.delta","text": delta}))
                yield delta
            await ws.send_text(json.dumps({"type":"llm.final"}))

        async for audio_chunk in tts_stream(text_iter()):
            await ws.send_text(json.dumps({"type":"tts.chunk","audio_base64": base64_from_pcm16(audio_chunk)}))
        await ws.send_text(json.dumps({"type":"tts.done"}))

    try:
        while True:
            msg = await ws.receive_text()
            data = json.loads(msg)
            t = data.get("type")

            if t == "auth":
                # TODO: validate JWT from data["token"] for production
                await ws.send_text(json.dumps({"type":"state.update","ok":True}))

            elif t == "audio.chunk":
                pcm = pcm16_from_base64(data["pcm16_le_base64"])
                state["buffer"].extend(pcm)

            elif t == "user.stop_speaking":
                async for stt_evt in transcribe_stream(buffered_iter(), sample_rate=state["sample_rate"]):
                    await ws.send_text(json.dumps({"type": f"stt.{stt_evt['type']}", "text": stt_evt.get("text","")}))
                    if stt_evt["type"] == "final":
                        text = stt_evt.get("text","").strip()
                        if text:
                            await handle_turn(text)

            elif t == "barge_in":
                state["barge"] = True
                # Implement pause/cancel in your real TTS; here we just ACK
                await ws.send_text(json.dumps({"type":"state.update","barge":True}))
    except WebSocketDisconnect:
        return
