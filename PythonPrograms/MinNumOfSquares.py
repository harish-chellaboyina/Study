__author__ = 'kh1911'

n = input()

dp = [-1] * (n + 1)

def calculateMinNumOfSquares(n):

    if n < 4:
        dp[n] = n
        return n

    dp[n] = n
    for i in range(1, n + 1):

        temp = i * i
        if temp > n:
            break

        if dp[n - temp] != -1:
            dp[n] = min(dp[n], dp[n - temp] + 1)
        else:
            dp[n] = min(dp[n], calculateMinNumOfSquares(n - temp) + 1)
    return dp[n]

print calculateMinNumOfSquares(n)
