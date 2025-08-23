# create_tables.py
from app.database import Base, engine
from app import models  # This imports your User and Conversation classes

Base.metadata.create_all(bind=engine)
print("✅ Tables created successfully!")
