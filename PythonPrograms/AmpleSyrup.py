from decimal import *
import operator

t = input()
p = 0
while t:
    p += 1
    t -= 1
    n, k = raw_input().split()
    n = int(n)
    k = int(k)

    i = 0
    inputsList = []
    another = {}
    while i < n:
        r, h = raw_input().split()
        r = int(r)
        h = int(h)

        surfaceArea = (r * r ) + (2 * r * h)

        inputsList.append({"r": r, "h": h, "sa": surfaceArea, "index": i})

        another[i] = {"r": r, "h": h, "sa": surfaceArea, "index": i}

        i += 1


    inputsList.sort(key=lambda x: x['h'], reverse=True)
    #inputsList = sorted(inputsList, key=lambda x: (x['r'], x['h']), reverse=True)
    #print sorted(another.values(), key=operator.attrgetter("h"))
    heightOrder =  sorted(another, key = lambda m: another[m]['h'], reverse=True)


    i = 0
    totalheight = 0
    while i < k:
        a = another[heightOrder[i]]
        totalheight += (2 * a['r'] * a['h'])
        i += 1

    base  = another[heightOrder[0]]['r'] * another[heightOrder[0]]['r']

    #total = (base + totalheight) * Decimal('3.1415926535897953')
    total = (base + totalheight)
    max = total

    last= [total]

    while i < n:

        s = 2 * another[heightOrder[i - k]]['h'] * another[heightOrder[i - k]]['r']
        l = (another[heightOrder[i - k]]['r'] * another[heightOrder[i - k]]['r']) - (another[heightOrder[i - k + 1]]['r'] * another[heightOrder[i - k + 1]]['r'])
        total = total - (s + l)

        total += 2 * another[heightOrder[i]]['r'] * another[heightOrder[i]]['h']

        last.append(total)

        if total > max:
            max = total

        i += 1

    max = max * Decimal('3.1415926535897953')

    print "Case #" + str(p) + ": " +  '{0:.9f}'.format(max)





