__author__ = 'kh1911'

#coins = [25, 10, 5]
coins = [2,5,6]
n = input()
dp = [-1] * (n + 1)

def minNumOfCoins(n):

    if n <= 0:
        return 0

    dp[n] = n
    for i in range(len(coins)):
        if coins[i] <= n:
            temp = coins[i]
            if dp[n - temp] != -1:
                dp[n] = max(dp[n], dp[n - temp] + 1)
            else:
                dp[n] = max(dp[n], minNumOfCoins(n - temp) + 1)

    return dp[n]

print minNumOfCoins(n)