t = input()

while t:
    t -= 1
    n = input()
    items = {}
    count = 0
    while n:
        n -= 1
        s = raw_input().strip().split()
        a = s[0]
        b = s[1]
        if items.has_key(a):
            items[a] += 1
        else:
            items[a] = 1

        if items.has_key(b):
            if items[b] == 0:
                count += 1
            else:
                items[b] -= 1
        else:
            count += 1
    print count