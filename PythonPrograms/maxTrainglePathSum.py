__author__ = 'kh1911'

import math

tri = [55,
	94, 48,
	95, 30, 96,
	77, 71, 26, 67]


length = len(tri)
base = int((math.sqrt(8*length + 1) - 1) / 2)
step = base - 1
stepc = 0
i = length - base - 1
while i>= 0:
    tri[i] += max(tri[i + step], tri[i + step + 1])
    stepc += 1
    if stepc == step:
        step -= 1
        stepc = 0
    i -= 1
print tri[0]