__author__ = 'kh1911'




def findMinDistance(a, b):

    m = len(a)
    n = len(b)

    dp = [[0 for x in range(n + 1)] for y in range(m + 1)]

    for i in range(m + 1):
        dp[i][0] = i
    for i in range(n + 1):
        dp[0][i] = i


    for i in range(1, m + 1):
        for j in range(1, n+ 1):
            if a[i - 1] == b[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]
            else:
                dp[i][j] = 1 + min(dp[i - 1][j], dp[i - 1][j - 1], dp[i][j - 1])

    return dp[m][n]



"""a = raw_input()
b = raw_input()

print findMinDistance(a, b)
"""

print findMinDistance("elp", "hello")