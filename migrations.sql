CREATE DATABASE IF NOT EXISTS communicator
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- USERS TABLE
CREATE TABLE IF NOT EXISTS users (
  id CHAR(36) PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  country_code VARCHAR(10),
  mobile VARCHAR(20) UNIQUE NOT NULL,
  password_hash TEXT NOT NULL,
  name VARCHAR(255),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- SESSIONS TABLE
CREATE TABLE IF NOT EXISTS sessions (
  id CHAR(36) PRIMARY KEY,
  user_id CHAR(36) NOT NULL,
  mode ENUM('chat','pdf') NOT NULL,
  document_id CHAR(36),
  chapter_id CHAR(36),
  started_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  closed_at DATETIME,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- MESSAGES TABLE
CREATE TABLE IF NOT EXISTS messages (
  id CHAR(36) PRIMARY KEY,
  session_id CHAR(36),
  role ENUM('user','assistant','system') NOT NULL,
  text TEXT,
  stt_conf FLOAT,
  llm_tokens INT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE CASCADE
);

-- AUDIO BLOBS TABLE
CREATE TABLE IF NOT EXISTS audio_blobs (
  id CHAR(36) PRIMARY KEY,
  session_id CHAR(36),
  role ENUM('user','assistant') NOT NULL,
  mime VARCHAR(100),
  duration_ms INT,
  url TEXT,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE CASCADE
);

-- DOCUMENTS TABLE
CREATE TABLE IF NOT EXISTS documents (
  id CHAR(36) PRIMARY KEY,
  user_id CHAR(36),
  name VARCHAR(255),
  url TEXT,
  status VARCHAR(50),
  uploaded_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- CHAPTERS TABLE
CREATE TABLE IF NOT EXISTS chapters (
  id CHAR(36) PRIMARY KEY,
  document_id CHAR(36),
  idx INT,
  title VARCHAR(255),
  text TEXT,
  FOREIGN KEY (document_id) REFERENCES documents(id) ON DELETE CASCADE
);

-- CHAPTER EMBEDDINGS TABLE
CREATE TABLE IF NOT EXISTS chapter_embeddings (
  chapter_id CHAR(36) PRIMARY KEY,
  embedding JSON,
  FOREIGN KEY (chapter_id) REFERENCES chapters(id) ON DELETE CASCADE
);

-- INDEXES
CREATE INDEX idx_messages_session ON messages(session_id);
CREATE INDEX idx_chapters_doc ON chapters(document_id);
