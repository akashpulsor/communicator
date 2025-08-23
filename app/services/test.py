import pyttsx3
import tempfile
import wave
import base64

# Hinglish text
text = "Namaste! Ye ek sample audio hai jo Hinglish me hai."

# Initialize TTS engine
engine = pyttsx3.init()
engine.setProperty('rate', 150)
engine.setProperty('volume', 1.0)

# Temporary WAV file
with tempfile.NamedTemporaryFile(suffix=".wav", delete=False) as tmp_wav:
    wav_path = tmp_wav.name

# Save speech to WAV
engine.save_to_file(text, wav_path)
engine.runAndWait()

# Read WAV PCM data
with wave.open(wav_path, 'rb') as wf:
    n_frames = wf.getnframes()
    pcm_bytes = wf.readframes(n_frames)

# Convert to base64
b64_string = base64.b64encode(pcm_bytes).decode('ascii')

print(b64_string)
print("\nTotal length:", len(b64_string))
