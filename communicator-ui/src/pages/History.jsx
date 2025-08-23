import { useListSessionsQuery, useGetSessionMessagesQuery } from '../services/sessionsApi'
import { useState } from 'react'

export default function History() {
  const { data: sessions } = useListSessionsQuery()
  const [active, setActive] = useState(null)
  const { data: messages } = useGetSessionMessagesQuery(active, { skip: !active })

  return (
    <div className="card">
      <h2>History</h2>
      <div className="row" style={{alignItems:'flex-start'}}>
        <div style={{flex:'0 0 280px'}}>
          <h3>Sessions</h3>
          <ul>
            {sessions?.map(s => (
              <li key={s.id}>
                <button onClick={()=>setActive(s.id)} style={{background:'transparent', border:'none', color:'#93c5fd'}}>
                  {s.mode} — {new Date(s.started_at).toLocaleString()}
                </button>
              </li>
            ))}
          </ul>
        </div>
        <div style={{flex:1}}>
          <h3>Messages {active ? `(${active})` : ''}</h3>
          {!active ? <p>Select a session</p> : (
            <ul>
              {messages?.map(m => (
                <li key={m.id}><b>{m.role}:</b> {m.text}</li>
              ))}
            </ul>
          )}
        </div>
      </div>
    </div>
  )
}
