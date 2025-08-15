import os
from dotenv import load_dotenv

load_dotenv()

class Settings:
    DATABASE_URL = os.getenv("DATABASE_URL", "mysql+pymysql://username:password@localhost:3306/communicator")
    JWT_SECRET = os.getenv("JWT_SECRET", "changeme")
    JWT_ALG = os.getenv("JWT_ALG", "HS256")
    ACCESS_TOKEN_EXPIRE_MINUTES = int(os.getenv("ACCESS_TOKEN_EXPIRE_MINUTES", "60"))
    REFRESH_TOKEN_EXPIRE_DAYS = int(os.getenv("REFRESH_TOKEN_EXPIRE_DAYS", "30"))
    STORAGE_ROOT = os.getenv("STORAGE_ROOT", "./storage")
    UPLOADS_ROOT = os.getenv("UPLOADS_ROOT", "./uploads")
    PUBLIC_BASE_URL = os.getenv("PUBLIC_BASE_URL", "http://localhost:8000")

    OLLAMA_URL = os.getenv("OLLAMA_URL", "http://localhost:11434")
    OLLAMA_MODEL = os.getenv("OLLAMA_MODEL", "phi3:latest")
    WHISPER_CPP_BIN = os.getenv("WHISPER_CPP_BIN", "")
    WHISPER_MODEL = os.getenv("WHISPER_MODEL", "medium")
    YOURTTS_URL = os.getenv("YOURTTS_URL", "")

    STT_PARTIAL_MS = int(os.getenv("STT_PARTIAL_MS", "300"))
    TTS_CHUNK_MS = int(os.getenv("TTS_CHUNK_MS", "240"))

settings = Settings()
