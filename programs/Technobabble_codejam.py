t = input()
j = 0
while t:
    t-= 1
    j +=1
    n = input()
    a = []
    b = []
    count = 0
    temp = []

    while n:
        n -= 1
        s = raw_input().strip().split()
        temp.append(s)
    temp.sort()
    a.append(temp[0].split()[0])
    b.append(temp[0].split()[1])

    i = 1


    print "Case #" + str(j) + ": " + str(count)