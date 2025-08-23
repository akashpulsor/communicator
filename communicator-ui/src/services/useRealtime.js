// src/services/useRealtime.js
import { useEffect, useMemo, useRef, useState, useCallback } from "react";

const WS_URL = `ws://localhost:8000/realtime/ws`; // backend WS

function b64ToArrayBuffer(b64) {
  const byteString = atob(b64);
  const len = byteString.length;
  const bytes = new Uint8Array(len);
  for (let i = 0; i < len; i++) bytes[i] = byteString.charCodeAt(i);
  return bytes.buffer;
}

function makeDummySessionId() {
  return "dummy-" + Math.random().toString(36).slice(2, 10);
}

/**
 * Backward-compatible signature:
 * - useRealtime({ sessionId, onServerMessage })
 * - useRealtime(sessionId, onServerMessage)
 */
export function useRealtime(arg1, arg2) {
  // Normalize args safely (no destructuring from null/undefined)
  let initialSessionId = null;
  let onServerMessage = null;

  if (typeof arg1 === "object" && arg1 !== null) {
    initialSessionId = arg1.sessionId ?? null;
    onServerMessage = arg1.onServerMessage ?? null;
  } else {
    initialSessionId = arg1 ?? null;
    onServerMessage = arg2 ?? null;
  }

  // If no sessionId provided, create a dummy one once
  const [sid, setSid] = useState(() => initialSessionId || makeDummySessionId());
  useEffect(() => {
    // If parent later provides a real sessionId, adopt it once
    if (initialSessionId && initialSessionId !== sid) setSid(initialSessionId);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [initialSessionId]);

  const wsRef = useRef(null);
  const mediaRecorderRef = useRef(null);
  const mediaStreamRef = useRef(null);
  const audioCtxRef = useRef(null);

  const [connected, setConnected] = useState(false);
  const [listening, setListening] = useState(false);
  const [speaking, setSpeaking] = useState(false);
  const [messages, setMessages] = useState([]);

  const appendMsg = useCallback((m) => {
    setMessages((prev) => [...prev, m]);
    // Also bubble to parent callback (if provided)
    if (onServerMessage && m?.type) {
      try { onServerMessage(m); } catch {}
    }
  }, [onServerMessage]);

  // Connect WS when we have a sid
  useEffect(() => {
    if (!sid) return;

    const ws = new WebSocket(WS_URL);
    wsRef.current = ws;

    ws.onopen = () => {
      setConnected(true);
      ws.send(JSON.stringify({ type: "start", session_id: sid }));
    };

    ws.onmessage = async (evt) => {
      if (typeof evt.data === "string") {
        const msg = JSON.parse(evt.data);

        // Mirror server messages locally too (optional)
        if (msg.type === "transcript") {
          appendMsg({ role: msg.role || "user", text: msg.text, type: "transcript" });
        } else if (msg.type === "stt.partial" || msg.type === "stt.final") {
          appendMsg(msg);
        } else if (msg.type === "llm.delta" || msg.type === "llm.final") {
          appendMsg(msg);
        } else if (msg.type === "error") {
          console.error("WS error:", msg.detail || msg);
        } else if (msg.type === "audio_chunk" || msg.type === "tts.audio") {
          // Try to play audio
          try {
            const base64 = msg.data;
            const arrBuf = b64ToArrayBuffer(base64);
            if (!audioCtxRef.current) {
              audioCtxRef.current = new (window.AudioContext || window.webkitAudioContext)();
            }
            const audioBuffer = await audioCtxRef.current.decodeAudioData(arrBuf.slice(0));
            const src = audioCtxRef.current.createBufferSource();
            src.buffer = audioBuffer;
            src.connect(audioCtxRef.current.destination);
            src.onended = () => setSpeaking(false);
            setSpeaking(true);
            src.start(0);
          } catch (e) {
            console.error("Audio decode failed", e);
          }
        }
      }
    };

    ws.onclose = () => {
      setConnected(false);
      wsRef.current = null;
    };

    return () => {
      try { ws.close(); } catch {}
    };
  }, [sid, appendMsg]);

  // Mic start
  const startMic = useCallback(async () => {
    if (listening) return;
    const stream = await navigator.mediaDevices.getUserMedia({ audio: true });
    mediaStreamRef.current = stream;

    const mr = new MediaRecorder(stream, { mimeType: "audio/webm;codecs=opus" });
    mediaRecorderRef.current = mr;

    mr.ondataavailable = async (e) => {
      if (e.data && e.data.size > 0 && wsRef.current?.readyState === 1) {
        const buf = await e.data.arrayBuffer();
        const b64 = btoa(String.fromCharCode(...new Uint8Array(buf)));
        wsRef.current.send(JSON.stringify({ type: "audio_chunk", data: b64, mime: "audio/webm;codecs=opus" }));
      }
    };

    mr.start(250);
    setListening(true);
  }, [listening]);

  const stopMic = useCallback(() => {
    try { mediaRecorderRef.current?.stop(); } catch {}
    mediaStreamRef.current?.getTracks()?.forEach((t) => t.stop());
    mediaRecorderRef.current = null;
    mediaStreamRef.current = null;
    setListening(false);
    try { wsRef.current?.send(JSON.stringify({ type: "stop" })); } catch {}
  }, []);

  const disconnect = useCallback(() => {
    stopMic();
    try { wsRef.current?.close(); } catch {}
  }, [stopMic]);

  // Optional helper to send a PDF via REST (fallback)
  const sendUserFile = useCallback(async (formData) => {
    const token = localStorage.getItem("token");
    await fetch("http://localhost:8000/documents/upload", {
      method: "POST",
      headers: token ? { Authorization: `Bearer ${token}` } : undefined,
      body: formData,
    });
  }, []);

  return {
    connected,
    listening,
    speaking,
    messages,
    sessionId: sid,
    startMic,
    stopMic,
    disconnect,
    sendUserFile,
    appendClientMsg: (text) => appendMsg({ role: "user", text, type: "client" }),
  };
}
