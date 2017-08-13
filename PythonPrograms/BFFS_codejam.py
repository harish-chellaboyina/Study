t = input()


def goRecursively(index1, index2, count):
    if proc[index1][index2] == -1:
        p = 0
        while p < n:
            if grid[p][index2] == 1 and proc[p][index2] != -1:
                count.append(p + 1)
                val = goRecursively(p, index2, count)
                output[p][index2] = val
                return val
            p += 1
        return count[:len(count) - 1]

    proc[index1][index2] = -1
    i = 0
    while i < n:
        if grid[index2][i] == 1:
            count.append(index2 + 1)
            val = goRecursively(index2, i, count)
            output[index2][i] = val
            return val
        i += 1
    return count

def check(s, grid):
    j = 0
    while j < len(s):
        grid[j][s[j] - 1] = 1
        j += 1

    a = 0
    maxLen = 0
    while a < n:
        b = 0
        while b < n:
            if grid[a][b] == 1:
                f = goRecursively(a, b, [a + 1])
                if f> output[a][b]:
                    output[a][b] = f
                    return
            b += 1
        a += 1

    pass

while t:
    t -= 1
    n = input()
    s = raw_input().strip().split()
    grid = []
    for i in range(n):
        grid.append([0] *n)
    output = []
    for i in range(n):
        output.append([[]] *n)
    proc = []
    for i in range(n):
        proc.append([0] *n)

    j = 0
    while j < len(s):
        s[j] = int(s[j])
        j += 1
    check(s,grid)
    i = 0
    j = 0
    maxLen = 0
    while i < n:
        while j < n:
            p = output[i][j]
            p = set(p)
            if len(p)> maxLen:
                maxLen = len(p)
                a = i
                b = j
            j += 1
        i += 1
    print maxLen
    print output[a][b]
