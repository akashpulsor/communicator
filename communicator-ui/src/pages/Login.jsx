import { useState } from 'react'
import { useLoginMutation } from '../services/authApi'
import { useNavigate } from 'react-router-dom'
export default function Login() {
  const [form, setForm] = useState({ email: '', password: '' })
  const [login, { isLoading }] = useLoginMutation()
  const navigate = useNavigate()
  const submit = async () => {
    try {
      await login(form).unwrap()
      // ✅ redirect to voice chat page
      navigate('/voice-chat') 
    } catch (e) {
      alert('Login failed')
    }
  }

  return (
    <div className="card" style={{ maxWidth: 480, margin: '40px auto' }}>
      <h2>Login</h2>

      <div className="row">
        <input
          placeholder="Email"
          value={form.email}
          onChange={e => setForm({ ...form, email: e.target.value })}
        />
      </div>

      <div className="row">
        <input
          type="password"
          placeholder="Password"
          value={form.password}
          onChange={e => setForm({ ...form, password: e.target.value })}
        />
      </div>

      <button disabled={isLoading} onClick={submit}>
        {isLoading ? 'Logging in...' : 'Login'}
      </button>
    </div>
  )
}
