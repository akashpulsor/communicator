export default function MessageBubble({ role, text }) {
  const isUser = role === 'user'
  return (
    <div style={{ display:'flex', justifyContent: isUser?'flex-end':'flex-start' }}>
      <div className="card" style={{
        maxWidth:'70%',
        background: isUser ? '#1e3a8a' : '#141417',
        borderColor: isUser ? '#1e40af' : '#2a2a2e',
        color: isUser ? '#e5e7eb' : '#d4d4d8',
        borderRadius:12,
      }}>
        {text}
      </div>
    </div>
  )
}
