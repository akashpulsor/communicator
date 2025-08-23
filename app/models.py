from sqlalchemy import Column, String, Text, DateTime, Enum, Float, Integer, JSON, ForeignKey
from sqlalchemy.dialects.mysql import CHAR
from sqlalchemy.sql import func
from app.database import Base
import uuid
import enum
class SessionMode(enum.Enum):
    chat = "chat"
    pdf = "pdf"


class MessageRole(enum.Enum):
    user = "user"
    assistant = "assistant"
    system = "system"


class AudioRole(enum.Enum):
    user = "user"
    assistant = "assistant"


class User(Base):
    __tablename__ = "users"

    id = Column(CHAR(36), primary_key=True, default=lambda: str(uuid.uuid4()))
    email = Column(String(255), unique=True, nullable=False)  # ✅ fixed length
    country_code = Column(String(10))
    mobile = Column(String(20), unique=True)
    password_hash = Column(Text, nullable=False)
    name = Column(String(255))
    created_at = Column(DateTime, default=func.now())

class Session(Base):
    __tablename__ = "sessions"

    id = Column(CHAR(36), primary_key=True)
    user_id = Column(CHAR(36), ForeignKey("users.id", ondelete="CASCADE"), nullable=False)
    mode = Column(Enum(SessionMode), nullable=False)
    document_id = Column(CHAR(36))
    chapter_id = Column(CHAR(36))
    started_at = Column(DateTime, default=func.now())
    closed_at = Column(DateTime)


class Message(Base):
    __tablename__ = "messages"

    id = Column(CHAR(36), primary_key=True)
    session_id = Column(CHAR(36), ForeignKey("sessions.id", ondelete="CASCADE"))
    role = Column(Enum(MessageRole), nullable=False)
    text = Column(Text)
    stt_conf = Column(Float)
    llm_tokens = Column(Integer)
    created_at = Column(DateTime, default=func.now())


class AudioBlob(Base):
    __tablename__ = "audio_blobs"

    id = Column(CHAR(36), primary_key=True)
    session_id = Column(CHAR(36), ForeignKey("sessions.id", ondelete="CASCADE"))
    role = Column(Enum(AudioRole), nullable=False)
    mime = Column(String(100))
    duration_ms = Column(Integer)
    url = Column(Text)
    created_at = Column(DateTime, default=func.now())


class Document(Base):
    __tablename__ = "documents"

    id = Column(CHAR(36), primary_key=True)
    user_id = Column(CHAR(36), ForeignKey("users.id", ondelete="CASCADE"))
    name = Column(String(255))
    url = Column(Text)
    status = Column(String(50))
    uploaded_at = Column(DateTime, default=func.now())


class Chapter(Base):
    __tablename__ = "chapters"

    id = Column(CHAR(36), primary_key=True)
    document_id = Column(CHAR(36), ForeignKey("documents.id", ondelete="CASCADE"))
    idx = Column(Integer)
    title = Column(String(255))
    text = Column(Text)


class ChapterEmbedding(Base):
    __tablename__ = "chapter_embeddings"

    chapter_id = Column(CHAR(36), ForeignKey("chapters.id", ondelete="CASCADE"), primary_key=True)
    embedding = Column(JSON)
