import { Link, useLocation } from 'react-router-dom'

export default function Navbar() {
  const loc = useLocation()
  const authed = !!localStorage.getItem('token')

  const logout = () => {
    localStorage.removeItem('token')
    window.location.href = '/login'
  }

  return (
    <div style={{background:'#0f0f12', borderBottom:'1px solid #222', padding:'10px 0'}}>
      <div className="container row" style={{alignItems:'center', justifyContent:'space-between'}}>
        <div className="row" style={{gap:16}}>
          <Link to="/" style={{color:'#fff', textDecoration:'none', fontWeight:700}}>Communicator</Link>
          {authed && (
            <>
              <Link to="/" style={{color: loc.pathname==='/'?'#60a5fa':'#e5e7eb'}}>Chat</Link>
              <Link to="/upload" style={{color: loc.pathname==='/upload'?'#60a5fa':'#e5e7eb'}}>Upload PDF</Link>
              <Link to="/history" style={{color: loc.pathname==='/history'?'#60a5fa':'#e5e7eb'}}>History</Link>
            </>
          )}
        </div>
        <div>
          {!authed ? (
            <>
              <Link to="/login" style={{color:'#e5e7eb', marginRight:12}}>Login</Link>
              <Link to="/register" style={{color:'#e5e7eb'}}>Register</Link>
            </>
          ) : (
            <button onClick={logout}>Logout</button>
          )}
        </div>
      </div>
    </div>
  )
}
