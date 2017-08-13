from itertools import permutations

t = input()
all = []

def createList(final, k):
    all = list(permutations[final])



def getColumn(matrix, index):
    return [row[index] for row in matrix]
i =0
while t:
    i += 1
    t -= 1
    if i == 27:
        pass
    n = input()
    j = 0
    final = []
    full = []
    while j < 2*n - 1:
        j += 1
        s = raw_input().strip().split()
        k = 0
        while k < len(s):
            s[k] = int(s[k])
            k +=1
        if s not in final:
            final.append(s)
        full.append(s)

    final.sort()
    d = {}
    for each in full:
        p = tuple(each)
        if d.has_key(p):
            d[p] += 1
        else:
            d[p] = 1

    k = 1
    output = [final[0]]
    while k < len(final):

        a = output[len(output) - 1]
        b = final[k]
        l = 0
        failed = False
        while l < len(a):
            if a[l] == b[l]:
                failed = True
                break
            l += 1
        if not failed:
            output.append(final[k])


        if len(output) == n:
            l = 0
            found = False

            for each in output:
                h = tuple(each)
                if d.has_key(h):
                    d[h] -= 1

            while l < n:
                temp = getColumn(output, l)

                if temp not in final:
                    s = ""
                    found = True
                    for each in temp:
                        s += str(each) + " "
                    print "Case #" + str(i) + ": " + s
                    k = len(final)
                    break
                else:
                    temp = tuple(temp)
                    if d.has_key(temp):
                        if d[temp] == 0:
                            s = ""
                            for each in temp:
                                s += str(each) + " "
                            print "Case #" + str(i) + ": " + s
                            found = True
                            break
                        else:
                            d[temp] -= 1

                l += 1
            if not found:
                s = ""
                for each in output[n - 1]:
                    s += str(each) + " "
                print "Case !#" + str(i) + ": " + s
                break
            else:
                break
        k += 1
        if k == len(final):
            print "came here"
            print final




