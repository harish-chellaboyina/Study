import string


t = input()

while t:
    t -=1
    d = {}
    s= raw_input().strip()
    for each in s:
        if d.has_key(each):
            d[each] += 1
        else:
            d[each] = 1
    i = 25
    letters = string.ascii_lowercase
    output = ""
    while i >= 0:
        if not d.has_key(letters[i]):
            output += letters[i] + " "
        i -= 1


    o = {}

    for each in d.keys():
        if o.has_key(d[each]):
            o[d[each]].append(each)
        else:
            o[d[each]] = [each]
    d = {}
    o = sorted(o.items(), key=lambda x: x[0])

    for each in o:
        l = each[1]
        l.sort(reverse=True)

        for temp in l:
            output += temp + " "

    print output

