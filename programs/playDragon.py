t = input()

min = 9999999999999

def check(hd, ad, hk, ak, B, D, attackType, turnCount, originalHealth):

    global min
    possibleActions = ["A", "B", "C", "D"]
    if hk <= 0:
        if min > turnCount:
            min = turnCount
            return

    if attackType == "A":
        hk -= ad
    elif attackType == "B":
        ad += B
    elif attackType == "C":
        hd = originalHealth
    elif attackType == "D":
        ak -= D
        if ak < 0:
            ak = 0

    turnCount += 1
    if turnCount > min:
        return

    if hk <= 0:
        if min > turnCount:
            min = turnCount
            return
    hd -= ak

    if hd <= 0:
        return

    i = 0
    while i < len(possibleActions):
        check(hd, ad, hk, ak, B, D, possibleActions[i], turnCount, originalHealth)
        i += 1

while t:
    t -= 1
    temp = raw_input().split()
    hd = int(temp[0])
    ad = int(temp[1])
    hk = int(temp[2])
    ak = int(temp[3])
    B = int(temp[4])
    D = int(temp[5])
    min = 9999999999999

    possibleActions = ["A", "B", "C", "D"]
    i = 0
    while i < len(possibleActions):
        check(hd, ad, hk, ak, B, D, possibleActions[i], 0, hd)
        i += 1

    if min == 9999999999999:
        print "IMPOSSIBLE"
    else:
        print min