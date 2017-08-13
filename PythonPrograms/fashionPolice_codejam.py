import itertools


t = input()
count = 0
while t:
    count += 1
    t -= 1
    st = raw_input().strip().split()
    j = int(st[0])
    p = int(st[1])
    s = int(st[2])
    k = int(st[3])
    a = [range(1,j+1), range(1,p+1), range(1,s+1)]
    a = list(itertools.product(*a))

    d = {}
    jp = {}
    ps = {}
    sj = {}
    for each in a:
        j = each[0]
        p = each[1]
        s = each[2]
        if not d.has_key(each):
            if jp.has_key((j,p)):
                if jp[(j,p)] == k:
                    continue

            if ps.has_key((p,s)):
                if ps[(p,s)] == k:
                    continue

            if sj.has_key((s,j)):
                if sj[(s,j)] == k:
                    continue

            if jp.has_key((j,p)):
                jp[(j,p)] += 1
            else:
                jp[(j,p)] = 1

            if ps.has_key((p,s)):
                ps[(p,s)] += 1
            else:
                ps[(p,s)] = 1

            if sj.has_key((s,j)):
                sj[(s,j)] += 1
            else:
                sj[(s,j)] = 1

            d[each] = 0
    a = d.keys()
    print "Case #" + str(count) + ": " + str(len(a))
    for each in a:
        print str(each[0]) + " " + str(each[1]) + " " + str(each[2])
