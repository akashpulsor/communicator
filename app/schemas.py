from pydantic import BaseModel, EmailStr
from typing import Optional
from uuid import UUID

class SignupReq(BaseModel):
    email: EmailStr
    password: str
    name: Optional[str] = None
    phone: str
    country_code: str

class SigninReq(BaseModel):
    email: EmailStr
    password: str

class TokenPair(BaseModel):
    access_token: str
    refresh_token: str
    token_type: str = "bearer"

class SessionCreate(BaseModel):
    mode: str
    document_id: Optional[UUID] = None
    chapter_id: Optional[UUID] = None
