import cv2

img = cv2.imread('0.jpg')
rows, cols = img.shape

for angle in range(1, 25):
    M = cv2.getRotationMatrix2D((rows/2, cols/2), angle*15, 1)
