__author__ = 'kh1911'


def incrementLastDecimal(n):
    n = list(str(n))
    n[len(n) - 1] = str(int(n[len(n) - 1]) + 1)
    return float(''.join(n))

def addADecimalToLast(n):
    n = str(1)
    n += '0'
    return float(n)

def lcmm(*args):
    return reduce(lcm, args)

def gcd(a, b):
    """Return greatest common divisor using Euclid's Algorithm."""
    while b:
        a, b = b, a % b
    return a

def lcm(a, b):
    """Return lowest common multiple."""
    return a * b // gcd(a, b)


"""def lcm(x, y):

    if x > y:
        greater = x
    else:
        greater = y

    while True:
        if (greater % x == 0) and (greater % y == 0):
            l = greater
            break
        greater += 1

    return l"""


import heapq

listForTree = [1,2,3,4,5,6,7,8,9,10,11,25, 12,13,14,15]
heapq._heapify_max(listForTree)
for i in range(4):
    print heapq.heappop(listForTree)
    heapq._heapify_max(listForTree)