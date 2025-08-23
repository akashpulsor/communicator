import { useEffect, useRef, useState } from 'react'
import { useRealtime } from '../services/useRealtime'
import MessageBubble from '../components/MessageBubble'
import { Mic, Square, Upload } from 'lucide-react'

// dummy session id banane ka helper
const generateDummySession = () =>
  "dummy-" + Math.random().toString(36).substring(2, 10)

export default function Chat() {
  const [messages, setMessages] = useState([
    { role: 'assistant', text: 'Namaste! Hinglish me baat karte hain 😊' }
  ])
  const [sessionId, setSessionId] = useState(null)
  const chatEndRef = useRef(null)

  // handle server messages (STT + LLM + TTS)
  const onServerMessage = (msg) => {
    if (!msg?.type) return

    if (msg.type === 'stt.final') {
      setMessages(m => [...m, { role: 'user', text: msg.text || '' }])
    } 
    else if (msg.type === 'llm.delta') {
      setMessages(m => {
        const last = m[m.length - 1]
        if (last?.role === 'assistant' && !last.final) {
          return [...m.slice(0, -1), { ...last, text: last.text + (msg.text || '') }]
        }
        return [...m, { role: 'assistant', text: msg.text || '', final: false }]
      })
    } 
    else if (msg.type === 'llm.final') {
      setMessages(m => {
        const last = m[m.length - 1]
        if (last?.role === 'assistant') return [...m.slice(0, -1), { ...last, final: true }]
        return m
      })
    }
    else if (msg.type === 'tts.audio') {
      playAudioChunk(msg.data)
    }
  }

  // hook with WS + mic (guard against null sessionId)
  let connected = false, listening = false, startMic = () => {}, stopMic = () => {}, sendUserFile = async () => {}
  if (sessionId) {
    ({ connected, listening, startMic, stopMic, sendUserFile } = useRealtime(sessionId, onServerMessage))
  }

  // scroll chat
  useEffect(() => {
    chatEndRef.current?.scrollIntoView({ behavior: 'smooth' })
  }, [messages])

  // create dummy session once
  useEffect(() => {
    setSessionId(generateDummySession())
  }, [])

  // play streamed audio helper
  const playAudioChunk = async (base64Data) => {
    const audioData = Uint8Array.from(atob(base64Data), c => c.charCodeAt(0)).buffer
    const ctx = new AudioContext()
    const decoded = await ctx.decodeAudioData(audioData)
    const src = ctx.createBufferSource()
    src.buffer = decoded
    src.connect(ctx.destination)
    src.start()
  }

  // handle PDF upload
  const onUpload = async (e) => {
    const file = e.target.files[0]
    if (!file) return
    const formData = new FormData()
    formData.append('file', file)
    try {
      await sendUserFile(formData)
      setMessages(m => [...m, { role: 'system', text: `📄 Uploaded: ${file.name}` }])
    } catch (err) {
      alert('Upload failed')
    }
  }

  return (
    <div style={{display:'flex', flexDirection:'column', height:'100vh'}}>
      {/* Messages */}
      <div style={{flex:1, overflowY:'auto', padding:'16px'}}>
        {messages.map((m, i) => (
          <MessageBubble key={i} role={m.role} text={m.text} />
        ))}
        <div ref={chatEndRef} />
      </div>

      {/* Upload + Controls */}
      <div style={{
        position:'fixed', bottom:20, left:0, right:0, 
        display:'flex', justifyContent:'center', gap:12
      }}>
        <label style={{cursor:'pointer'}}>
          <Upload size={22} />
          <input type="file" accept="application/pdf" hidden onChange={onUpload} />
        </label>

        {!listening ? (
          <button
            title="Speak"
            onClick={startMic}
            style={{
              borderRadius:'50%', padding:20,
              background:'#4cafef', color:'#fff',
              boxShadow:'0 4px 12px rgba(0,0,0,0.2)'
            }}
          >
            <Mic size={24} />
          </button>
        ) : (
          <button
            title="Stop"
            onClick={stopMic}
            style={{
              borderRadius:'50%', padding:20,
              background:'#ef4444', color:'#fff',
              boxShadow:'0 4px 12px rgba(0,0,0,0.2)'
            }}
          >
            <Square size={24} />
          </button>
        )}
      </div>
    </div>
  )
}
