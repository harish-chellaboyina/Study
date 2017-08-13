__author__ = 'kh1911'

import collections
import itertools

keyIndex = 0
keyLength = 1
availableKeys = []
assignedKeys = []


def generateUniqueKey():
    global availableKeys, assignedKeys, keyLength, keyIndex
    if keyIndex == len(availableKeys):
        tempKeys = itertools.permutations(getString(), keyLength)
        availableKeys = []
        for each in tempKeys:
            availableKeys.append(''.join(each))
        keyIndex = 0
        keyLength += 1
    tempKey = availableKeys[keyIndex]
    keyIndex += 1
    assignedKeys.append(tempKey)
    return tempKey

def compressData(data):
    finalString = ""
    wordsMap = {}
    words = data.split(" ")
    counter = collections.Counter(words)
    keys = counter.keys()
    for each in keys:
        wordsMap[each] = generateUniqueKey()
    for each in words:
        finalString += wordsMap[each] + " "
    print wordsMap

    i = 0
    tempString = ""
    while i < len(data):
        if data[i] == " " or data[i] == "\n":
            if wordsMap.has_key(tempString):
                finalString += wordsMap[tempString]
            finalString += data[i]
        else:
            tempString += data[i]
        i += 1
    print wordsMap
    print finalString


def getString():
    a = ""
    for i in range(33, 127):
        a += chr(i)
    return a

f = open("/Volumes/OS/Users/kh1911/Desktop/test.txt", "r+")
data = f.read()
compressData(data)
f.close()