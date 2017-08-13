t = input()

while t:
    t -= 1
    i = 1
    k = int(raw_input().split()[0])
    output = "Case #" + str(i) + ": "
    while i <= k:
        output += str(i) + " "
        i += 1
    print output