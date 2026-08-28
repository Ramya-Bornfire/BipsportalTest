from PIL import Image
import numpy as np

# Load image
img = Image.open('src/main/resources/static/Image/QR_MUR_M.png').convert('RGB')
img = img.resize((900, 1100), Image.Resampling.LANCZOS)
arr = np.array(img)

# Find pixels that are completely white or almost white (the box for QR)
# The box is surrounded by off-white/beige card. The QR box is usually pure white.
# Let's find rows/cols with a long continuous stretch of pure white (> 245)
white_mask = (arr[:,:,0] > 245) & (arr[:,:,1] > 245) & (arr[:,:,2] > 245)

best_box = None
max_area = 0

# Simple heuristic: find the largest bounding box of mostly white pixels
import cv2
# OpenCV is easier, let's use it if available
