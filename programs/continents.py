path = raw_input()

f = open(path, "r")
data = f.read()
f.close()

l = []
row = 2
column = 2

matrix = []
temp = []
for i in range(row):
    l = []
    for j in range(column):
        l.append(0)
    temp.append(l)


l = []
j = 0

for each in data:
    if each == " " or each == "#":
        l.append(each)
        j += 1
        if j == column:
           matrix.append(l)
           l = []
           j = 0


def doBFS(a, b, sum):

    if a < 0 or b < 0 or a > row - 1 or b > row - 1 or temp[a][b] == 1 or matrix[a][b] == " ":
        return 0

    temp[a][b] = 1
    sum += 1

    sum += doBFS(a + 1, b, 0)
    sum += doBFS(a, b + 1, 0)
    sum += doBFS(a + 1, b + 1, 0)
    sum += doBFS(a - 1, b, 0)
    sum += doBFS(a, b - 1, 0)
    sum += doBFS(a - 1, b - 1, 0)
    sum += doBFS(a - 1, b + 1, 0)
    sum += doBFS(a + 1, b - 1, 0)

    return sum

p = []
maxSum = 0

for i in range(row):
    for j in range(column):
        if matrix[i][j] == "#" and temp[i][j] == 0:
            obtainedSum = doBFS(i, j, 0)
            p.append(obtainedSum)

p.sort(reverse=True)

i = 1

while i <= len(p):
    print("Island " + str(i) + ": " + str(p[i-1]))
    i += 1
print("Number of continents: " + str(len(p)))