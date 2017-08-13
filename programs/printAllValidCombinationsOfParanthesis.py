__author__ = 'kh1911'




def printAllValidCombinations(openSum, closeSum, n, presentIndex):

    if (openSum + closeSum == n * 2):
        print ''.join(string)
        return

    if openSum < n:
        string[presentIndex] = '{'
        printAllValidCombinations(openSum + 1, closeSum, n, presentIndex + 1)

    if openSum - closeSum > 0:
        string[presentIndex] = '}'
        printAllValidCombinations(openSum, closeSum + 1, n, presentIndex + 1)

n = 3
string = " "* (n * 2)
string = list(string)

printAllValidCombinations(0, 0, n, 0)