from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from fastapi.staticfiles import StaticFiles

from .database import Base, engine
from .routers import auth as r_auth
from .routers import documents as r_docs
from .routers import sessions as r_sessions
from .routers import messages as r_messages
from .config import settings

def create_app():
    app = FastAPI(title="Hinglish Voice Backend (Phi-3)", version="0.2.0")

    app.add_middleware(
        CORSMiddleware,
        allow_origins=["*"],
        allow_credentials=True,
        allow_methods=["*"],
        allow_headers=["*"],
    )

    app.include_router(r_auth.router)
    app.include_router(r_docs.router)
    app.include_router(r_sessions.router)
    app.include_router(r_messages.router)

    app.mount("/static", StaticFiles(directory=settings.UPLOADS_ROOT), name="static")

    @app.get("/healthz")
    def healthz():
        return {"ok": True, "model": settings.OLLAMA_MODEL}

    return app

Base.metadata.create_all(bind=engine)
app = create_app()
