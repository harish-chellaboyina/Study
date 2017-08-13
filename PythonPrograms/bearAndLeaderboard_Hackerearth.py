s = raw_input().strip().split()
n = int(s[0])
q = int(s[1])
d = {}

while q:
    q -= 1
    s = raw_input().strip().split()
    a = int(s[0])
    b = int(s[1])

    if d.has_key(a):
        d[a] += b
    else:
        d[a] = b

    t = d.values()
    t.sort(reverse=True)
    print t
    i = 1
    sum = t[0]
    prev = t[0]
    j = 1
    while i < len(t):
        if prev != t[i]:
            prev = t[i]
            j += 1
        sum += (t[i] * j)
        i += 1
    print sum