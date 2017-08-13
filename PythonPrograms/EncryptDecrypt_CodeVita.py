op = raw_input()

if op == 'E':
    s = raw_input()
    id = raw_input()
    pre = str(len(str(id))) + "-" + str(id)
    l = []
    for each in s:
        a = list(hex(ord(each)).upper()[2:])
        a.reverse()
        pre += '-' + ''.join(a)
    print pre

elif op == 'D':
    s = raw_input()
    s = s.split("-")
    print s[1]
    i = 2
    t = ""
    while i < len(s):
        t += chr(int(s[i][::-1], 16))
        i += 1

    print t
