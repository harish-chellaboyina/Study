
def maximumAmount(a, k):
    i = 0
    a.sort()
    print a
    sum = 0
    while i < k:
        maxElement = a.pop()
        if len(a) > 0:
            secondMax = a[len(a) - 1]
        else:
            secondMax = 0

        if k - i == 1:
            sum += maxElement
            a.append(maxElement)
            a.sort()
            break
        if maxElement - secondMax < (k - i):
            n = maxElement - secondMax + 1
            sum += (n * ((2*secondMax) + (n - 1)))/2
            i += n
            maxElement = secondMax - 1
            a.append(maxElement)
            a.sort()

        elif maxElement - secondMax >= (k - i):
            n = maxElement - (k - i) + 1
            sum += (n * ((2*maxElement) + (1 - n)))/2
            maxElement -= n
            a.append(maxElement)
            a.sort()
            break
    print a
    return sum

print maximumAmount([2, 8,4,10,6], 20)
print maximumAmount([2, 5], 4)

print maximumAmount([2, 2, 2, 2, 3], 1)