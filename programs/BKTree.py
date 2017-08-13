__author__ = 'Harish'

import WordsDictionary
import timeit

class BKTreeNode:

    def __init__(self, string):
        self.word = string
        self.children = {}

    def getWord(self):
        return self.word

    def getChildren(self):
        return self.children

def getPredcitions(rootNode, tolerance, word, finalOutput):

    if rootNode == None:
        return

    dist = findMinDistance(word, rootNode.getWord())

    if dist <= tolerance:
        finalOutput.append(rootNode.getWord())

    children = rootNode.getChildren()

    lowerBound = tolerance - dist if tolerance - dist >=0 else 0
    upperBound = tolerance + dist

    childKeys = children.keys()

    for each in childKeys:
        if each >= lowerBound and each <= upperBound:
            finalOutput = getPredcitions(children[each], tolerance, word, finalOutput)

    return finalOutput


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


def InsertIntoTree(rootNode, word):

    if rootNode == None:
        return BKTreeNode(word)


    dist = findMinDistance(word, rootNode.getWord())

    if rootNode.getChildren().has_key(dist):
        rootNode.getChildren()[dist] = InsertIntoTree(rootNode.getChildren()[dist], word)
    else:
        rootNode.getChildren()[dist] = BKTreeNode(word)
    return rootNode


start = timeit.default_timer()


root = None

#words = ["help", "hell", "hello", "loop", "shell", "helper", "troop"]

words = WordsDictionary.getAllWords()

for each in words:
    root = InsertIntoTree(root, each)

#print getPredcitions(root, 2, "elp", [])

print timeit.default_timer() - start
while True:
    b = raw_input("Enter word : ")
    c = input("Enter distance : ")
    if c == -1:
        break
    print getPredcitions(root, c, b, [])
