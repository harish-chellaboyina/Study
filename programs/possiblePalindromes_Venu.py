from itertools import permutations

import sys
s = raw_input().strip()
d = {}
if len(s) == 1:
    print s
    sys.exit(0)


for each in s:
    if d.has_key(each):
        d[each] += 1
    else:
        d[each] = 1

oddCount = 0
oddChar = ''
for each in d:
    if d[each] %2:
        oddCount += 1
        oddChar = each

if oddCount > 1:
    print 'No possible palindromes!'
else:
    s = ""
    for each in d:
        if d[each] %2 != 1:
            s += each * (d[each]/2)


    per = [''.join(p) for p in permutations(s)]
    output = []
    for each in per:
        temp =  each + oddChar + each[::-1]
        if temp not in output:
            print temp
            output.append(temp)