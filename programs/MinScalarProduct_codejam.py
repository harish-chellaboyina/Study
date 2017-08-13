__author__ = 'kh1911'

t = input()
j = 0
while t:
    j += 1
    t -= 1
    n = input()
    a = (raw_input().strip()).split()
    b = (raw_input().strip()).split()
    i = 0
    while i < len(a):
        a[i] = int(a[i])
        b[i] = int(b[i])
        i +=1
    a.sort()
    b.sort(reverse=True)
    i = 0
    pro = 0
    while i < len(a):
        pro += (a[i] * b[i])
        i += 1
    print "Case #" + str(j) + ": " + str(pro)
