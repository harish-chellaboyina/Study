from itertools import permutations

def getAllPerms(str):
    perms = [''.join(p) for p in permutations(str)]
    return list(set(perms))

def validate(order):
    temp = ['RR', 'OO', 'YY', 'GG', 'BB', 'VV', 'OG', 'GO', 'GV', 'VG', 'OV', 'VO']
    i = 0
    tempOrder = order + order[0]
    while i < len(temp):
        if tempOrder.find(temp[i]) != -1:
            return 0
        i += 1
    return 1

def getAllowedChars(presentChar):
    if presentChar == '':
        return ['R', 'O', 'Y', 'G', 'B', 'V']
    if presentChar == 'R':
        return ['Y', 'B', 'G']
    if presentChar == 'B':
        return ['O', 'R', 'G']
    if presentChar == 'Y':
        return ['V', 'B', 'R']

    if presentChar == 'O':
        return ['B']
    if presentChar == 'G':
        return ['R']
    if presentChar == 'V':
        return ['Y']




def process(countArray, outputList, index):
    lastChar = ""
    if countArray.count(0) == len(countArray) - 1:
        return outputList
    outputList = list(outputList)
    countArray = list(countArray)
    if index != 0:
        lastChar = outputList[index - 1]
    colors = ['R', 'O', 'Y', 'G', 'B', 'V']
    d = {'R': 1, 'O': 2, 'Y': 3, 'G': 4, 'B': 5, 'V': 6}

    temp = getAllowedChars(lastChar)
    i = 0
    while i < len(temp):
        colorIndex = d[temp[i]]
        if countArray[colorIndex] > 0:

            outputList[index] = temp[i]
            countArray[colorIndex] -= 1
            t = process(countArray, outputList, index + 1)
            if t != False:
                return t
        i += 1
    return False



t = input()
j = 0
while t:
    j += 1
    t -= 1
    a = raw_input().split()
    n = int (a[0])

    i = 0

    colors = ['R', 'O', 'Y', 'G', 'B', 'V']
    while i < len(a):
        a[i] = int(a[i])
        i += 1

    invalidCombinations = ['RR', 'OO', 'YY', 'GG', 'BB', 'VV', 'OG', 'GO', 'GV', 'VG', 'OV', 'VO']
    outputList = [''] * n

    output = process(a, outputList, 0)

    if output == False:
        output = "IMPOSSIBLE"
    else:
        output = ''.join(output)


    print "Case #" + str(j) + ": " + output
