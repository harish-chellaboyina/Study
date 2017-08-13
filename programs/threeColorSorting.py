def nextNonTwoIndex(t):
    while t >= 0 and s[t] =='2':
        t -= 1
    return t

s = raw_input().strip().split()

i = 0
j = nextNonTwoIndex(len(s) - 1)
moving = 0

while moving <= j:

    if s[moving] == '2':
        temp = s[j]
        s[j] = '2'
        if temp == '0':
            s[i] = '0'
            i += 1
        j = nextNonTwoIndex(j)
    elif s[moving] == '0':
        s[i] = '0'
        i += 1

    if i <= moving:
        s[moving] = '1'
    moving += 1

print s