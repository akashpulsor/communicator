import numpy as np

# 1 second of 440Hz sine wave
rate = 16000
t = np.linspace(0, 1, rate, endpoint=False)
x = (0.5*np.sin(2*np.pi*440*t) * 32767).astype(np.int16)

# write to raw PCM
x.tofile("input.pcm")
