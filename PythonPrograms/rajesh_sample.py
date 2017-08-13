__author__ = 'kh1911'


a = 1

c = 0

while a != 100:

    b = 2

    while a > b and a % b > 0:
        b = b + 1

    if a == b:
        c = c + 1


    a = a+1

print c