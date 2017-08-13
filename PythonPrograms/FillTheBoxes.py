s = raw_input().split()[1]
count = 0
i = 1
other = s[0]
while i < len(s):
    if s[i] == "G":
        if other[len(other) - 1] == "G":
            pass
        else:
            other += s[i]
    elif s[i] == "R":
        other += s[i]
    i += 1
print len(other) - 1
