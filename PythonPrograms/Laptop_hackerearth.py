n = input()

d = {}
while n:
    n -= 1
    s = raw_input().strip()

    if d.has_key(s):
        d[s] += 1
    else:
        d[s] = 1

temp =  sorted(d.items(), key=lambda x: x[1], reverse=True)

maxVal =  temp[0][1]
l = []

for each in temp:
    if each[1] == maxVal:
        l.append(each[0])
l.sort()
print l[0]
