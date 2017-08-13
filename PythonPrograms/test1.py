# Complete the function below.

def getOutcomeOfTheFeat(weights, marginOfError):
    sum = 0
    i = 0
    length = len(weights)

    while i < length:
        sum += weights[i]
        i += 1
    d = [[0 for i in range(sum + 1)] for j in range(length + 1)]

    i = 0
    while i <= length:
        d[i][0] = 1
        i += 1
    i = 1
    while i <= sum:
        d[0][i] = 0
        i += 1
    i = 1
    j = 1
    while i <= length:
        while j <= sum:
            d[i][j] = d[i - 1][j]
            if weights[i - 1] <= j:
                d[i][j] = d[i][j] or d[i - 1][j - weights[i - 1]]
            j += 1
        i += 1
    
    difference = 9999999999
    j = sum/2

    print d
    while j >= 0:
        if d[length][j] == True:
            difference = sum - 2 * j
            break
        j -= 1
    print difference
    if difference == 0:
        return "Perfectly Balanced"
    elif difference <= marginOfError:
        return "Balanced within " + str(difference)
    else:
        return "Not Balanced"

print getOutcomeOfTheFeat([3,1,4,2,2,1], 4)