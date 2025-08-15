from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from ..schemas import SignupReq, SigninReq, TokenPair
from ..database import get_db
from ..models import User
from ..auth import hash_password, verify_password, create_access_refresh

router = APIRouter(prefix="/auth", tags=["auth"])

@router.post("/signup", response_model=TokenPair)
def signup(payload: SignupReq, db: Session = Depends(get_db)):
    if db.query(User).filter(User.email == payload.email).first():
        raise HTTPException(status_code=400, detail="Email already exists")
    u = User(email=payload.email, password_hash=hash_password(payload.password), name=payload.name)
    db.add(u); db.commit(); db.refresh(u)
    access, refresh = create_access_refresh(str(u.id))
    return {"access_token": access, "refresh_token": refresh}

@router.post("/signin", response_model=TokenPair)
def signin(payload: SigninReq, db: Session = Depends(get_db)):
    u = db.query(User).filter(User.email == payload.email).first()
    if not u or not verify_password(payload.password, u.password_hash):
        raise HTTPException(status_code=401, detail="Invalid credentials")
    access, refresh = create_access_refresh(str(u.id))
    return {"access_token": access, "refresh_token": refresh}
