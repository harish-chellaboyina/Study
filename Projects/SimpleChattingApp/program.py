__author__ = 'kh1911'


n = input()
copiedCount = 0
bufferSize = 0


#Algo 1

maxList = {}

def startProgram(n):
    if n < 6:
        print n
        return

    if n >=3:
        bufferSize = 3


    print calcaulateMax(bufferSize, 0, 4, n)



def calcaulateMax(bufferSize, copiedCount, i, n):
    a = 0
    b = 0

    if i <6:
        return i
    if i > n:
        return

    if copiedCount > 0:
        a = calcaulateMax(bufferSize + copiedCount, copiedCount, i - 1, n)
    if n -i >= 3:
        b = calcaulateMax(bufferSize * 2, bufferSize, i - 3, n)
    else:
        b = bufferSize + (copiedCount * i)


    if a > b:
        b = a
    maxList[i] = b
    return b

startProgram(n)



#Algo 2
"""
while n > 2:

    if (copiedCount * 3) < (2 * bufferSize):
        print "1", bufferSize
        n -= 3
        copiedCount = bufferSize
        bufferSize *= 2
    else:
        print "2", bufferSize
        n -= 1
        bufferSize += copiedCount
    print "here", copiedCount, bufferSize

bufferSize += copiedCount * n
print bufferSize

"""

#Algo 3

"""
def findoptimal(N):
    if (N <= 6):
        return N

    screen = [0] * N

    for n in range(1, 7):
        screen[n-1] = n

    for n in range(7, N+1):
        screen[n-1] = 0

        b = n - 3
        while b >= 1:
            curr = (n-b-1) * screen[b-1]
            if curr > screen[n-1]:
                    screen[n-1] = curr
            b -= 1
    return screen[N-1]

print findoptimal(50)"""