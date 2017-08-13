__author__ = 'kh1911'

import math

val = 3 + math.sqrt(5)

c = input()
j = 0
while c:
    c -= 1
    j += 1
    n = input()
    i = 0
    pro = 1

    while i < n:
        pro = pro * val
        temp = str(pro)
        temp = temp.split(".")
        if len(temp[0]) > 6:
            t = int(temp[0])%1000000
            temp[0] = str(t)
            pro = float(temp[0] + "." + temp[1])
        i += 1
    output = str(int(str(pro).split(".")[0]) % 1000)
    print "Case #" + str(j) + ": " +  "0" * (3 - len(output)) + output