import { useState } from 'react'
import { useRegisterMutation } from '../services/authApi'

export default function Register() {
  const [form, setForm] = useState({ name: '', email: '', password: '' })
  const [register, { isLoading }] = useRegisterMutation()

  const submit = async () => {
    try {
      // Hardcoded for testing
      const payload = { 
        ...form, 
        country_code: '+91', 
        phone: '1234567890' 
      }

      await register(payload).unwrap()
      alert('Registered! Please login.')
      window.location.href = '/login'
    } catch (e) {
      alert('Registration failed')
    }
  }

  return (
    <div className="card" style={{ maxWidth: 480, margin: '40px auto' }}>
      <h2>Register</h2>

      <div className="row">
        <input
          placeholder="Name"
          value={form.name}
          onChange={e => setForm({ ...form, name: e.target.value })}
        />
      </div>

      <div className="row">
        <input
          placeholder="Email"
          type="email"
          value={form.email}
          onChange={e => setForm({ ...form, email: e.target.value })}
        />
      </div>

      {/* Hardcoded phone & country code fields */}
      <div className="row">
        <input placeholder="Country Code" value="+91" disabled />
      </div>
      <div className="row">
        <input placeholder="Phone" value="1234567890" disabled />
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
        {isLoading ? 'Creating...' : 'Create Account'}
      </button>
    </div>
  )
}
