import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Navbar from './components/Navbar'
import Login from './pages/Login'
import Register from './pages/Register'
import Chat from './pages/Chat'
import UploadPdf from './pages/UploadPdf'
import History from './pages/History'

const isAuthed = () => !!localStorage.getItem('token')

function Protected({ children }) {
    console.log("AKASHD" + isAuthed())
  return isAuthed() ? children : <Navigate to="/login" replace />
}

export default function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <div className="container">
        <Routes>
          <Route path="/voice-chat" element={<Chat />} />
          <Route path="/upload" element={<Protected><UploadPdf /></Protected>} />
          <Route path="/history" element={<Protected><History /></Protected>} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      </div>
    </BrowserRouter>
  )
}
