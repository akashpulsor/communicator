import { useState } from 'react'
import { useUploadDocumentMutation, useGetDocumentsQuery } from '../services/documentsApi'

export default function UploadPdf() {
  const [file, setFile] = useState(null)
  const [upload, { isLoading }] = useUploadDocumentMutation()
  const { data: docs, refetch } = useGetDocumentsQuery()   // ✅ yahan bhi same naam

  const submit = async () => {
    if (!file) return
    try {
      // ✅ file ko FormData me bhejna padega
      const formData = new FormData()
      formData.append('file', file)

      await upload(formData).unwrap()
      setFile(null)
      refetch()
      alert('Upload started! PDF process ho raha hai.')
    } catch {
      alert('Upload failed')
    }
  }

  return (
    <div className="card">
      <h2>Upload PDF</h2>
      <input type="file" accept=".pdf" onChange={(e)=>setFile(e.target.files?.[0] || null)} />
      <button disabled={!file || isLoading} onClick={submit}>Upload</button>

      <h3 style={{marginTop:24}}>Your Documents</h3>
      <ul>
        {docs?.map(d => (
          <li key={d.id}>{d.name} — {d.status}</li>
        ))}
      </ul>
    </div>
  )
}
