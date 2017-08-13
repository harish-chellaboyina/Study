import sys


n = int(input())
pointsTable = []
c = 0
teamsMap = {}

t = raw_input().split()

for each in t:
	pointsTable.append([0, 0, 0, 0, each])
	teamsMap[each] = c
	c += 1

t = input()
m = int(t)
teamCount = {}

for i in range(m):
    t = raw_input()
    t = t.split()
    t1 = t[0]
    t2 = t[1]
    
    
    s1 = int(t[2])
    s2 = int(t[3])
    
    if t1 == t2:
        print("Invalid Input")
        sys.exit(0)
    
    if teamCount.has_key(t1):
    	
        if teamCount[t1] > 1:
            print("Invalid Input")
            sys.exit(0)
        else:
            teamCount[t1] += 1
    else:
        teamCount[t1] = 0
        
    
    if teamCount.has_key(t2):
        if teamCount[t2] > 1:
            print("Invalid Input")
            sys.exit(0)
        else:
            teamCount[t2] += 1
    else:
        teamCount[t2] = 0


    t1 = teamsMap[t1]
    t2 = teamsMap[t2]

    pointsTable[t1][0] += s1
    pointsTable[t1][1] += s2
    pointsTable[t1][2] = pointsTable[t1][0] - pointsTable[t1][1]

    pointsTable[t2][0] += s2
    pointsTable[t2][1] += s1
    pointsTable[t2][2] = pointsTable[t2][0] - pointsTable[t2][1]

    if s1 > s2:
        pointsTable[t1][3] += 2
    elif s2> s1:
        pointsTable[t2][3] += 2
    else:
        pointsTable[t1][3] += 1
        pointsTable[t2][3] += 1


def compare(a, b):

    if a[3] > b[3]:
        return 1
    elif b[3] < a[3]:
        return -1

    if a[2] > b[2]:
        return 1
    elif b[2] < a[2]:
        return -1

    if a[0] > b[0]:
        return 1
    elif b[0] < a[0]:
        return -1

    if a[4] > b[4]:
        return -1
    elif b[4] > a[4]:
        return 1


pointsTable = sorted(pointsTable, cmp=compare)

for each in pointsTable:
    print(each)
