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



t = input()
j = 0
while t:
    t -= 1
    j += 1
    s = raw_input().strip().split()
    b = int(s[0])
    n = int(s[1])
    i = 0
    times = raw_input().strip().split()
    while i < len(times):
        times[i] = int(times[i])
        i += 1
    l = lcmm(*times)
    count = 0
    for each in times:
        count += (l/each)

    if count < n:
        n = n - (count * (n/count))
    elif count == n:
        print "Case #" + str(j) + ": " + str(len(times))
        continue
    if n == 0:
        print "Case #" + str(j) + ": " + str(len(times))
        continue
    if n < len(times):
        print "Case #" + str(j) + ": " + str(n)
        continue

    count = 0
    i = 1
    while i < 10000:
        for each in times:
            if i % each == 0:
                count += 1
        i += 1

    temp = (n/count) * 10000
    count = 0
    for each in times:
        count += (temp/each)

    if count > n:
        print "Failed"
        continue

    found = False
    while True:
        temp += 1
        k = 0
        for each in times:
            k += 1
            if temp%each == 0:
                count += 1
                if count == n:
                    print "Case #" + str(j) + ": " + str(k)
                    found = True
                    break
        if found:
            break

