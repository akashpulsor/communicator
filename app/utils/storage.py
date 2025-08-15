from pathlib import Path
from typing import BinaryIO
import uuid
from ..config import settings

root = Path(settings.STORAGE_ROOT); root.mkdir(parents=True, exist_ok=True)
uploads = Path(settings.UPLOADS_ROOT); uploads.mkdir(parents=True, exist_ok=True)

def save_upload(filename: str, fileobj: BinaryIO) -> str:
    ext = Path(filename).suffix or ""
    key = f"{uuid.uuid4().hex}{ext}"
    path = uploads / key
    with open(path, "wb") as f:
        f.write(fileobj.read())
    return str(path)

def public_url_from_path(path: str) -> str:
    return f"{settings.PUBLIC_BASE_URL}/static/{Path(path).name}"
