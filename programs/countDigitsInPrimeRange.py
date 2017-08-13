import math
import datetime

def isPrime(n):
    if n in [2, 3, 5, 7, 11]:
        return True
    if n %2 == 0 or n % 3 == 0 or n % 5 == 0 or n % 7 == 0 or n % 11 == 0:
        return False
    i = 12
    sqrt = math.sqrt(n)
    while i <= sqrt:
        if i + 1 < sqrt and n % (i + 1) == 0:
            return False
        if i + 5 < sqrt and n % (i + 5) == 0:
            return False
        i += 6
    return True

t = input()
p = datetime.
while t:
    t -= 1
    a = raw_input().split()
    l = int(a[0])
    r = int(a[1])

    a = [0] * 10

    while l <= r and l <= 5:
        if isPrime(l):
            a[l] += 1
        l += 1

    if l%6 != 0:
        l += 6 - (l % 6)

    while True:
        if l + 1 > r:
            break
        if isPrime(l + 1):
            temp = list(str(l + 1))
            i = 0
            while i < len(temp):
                a[int(temp[i])] += 1
                i += 1
        if l + 5 > r:
            break
        if isPrime(l + 5):
            temp = str(l + 5)
            i = 0
            while i < len(temp):
                a[int(temp[i])] += 1
                i += 1

        l += 6

    i = 1
    max = 0
    while i < len(a):
        if a[i] >= a[max]:
            max = i
        i += 1

    print max