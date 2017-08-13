__author__ = 'Harish'

import sys

def checkDoubleString(str, length):
    return str[0:length/2] == str[length/2:]

def findOut(str):
    length = len(str)


    if length <= 1:
        return False

    if length == 2:
        return str[0] == str[1]

    if length % 2 == 0:
        return checkDoubleString(str, length)

    i = 0
    temp = length / 2

    misMatchOccured = False
    while True:
        if i == (length / 2) or temp == length + 1:
            return True

        if str[i] == str[temp]:
            i += 1
            temp += 1
        else:
            if misMatchOccured:
                break
            misMatchOccured = True
            temp += 1

    i = 0
    temp = (length / 2) + 1

    misMatchOccured = False
    while True:
        if i == (length / 2) + 1 or temp == length + 1:
            return True

        if str[i] == str[temp]:
            i += 1
            temp += 1
        else:
            if misMatchOccured:
                break
            misMatchOccured = True
            i += 1

    return False

n = input()
while n:
    n -= 1
    a = raw_input()
    a = a.split()[0]
    if findOut(a):
        print "YES"
    else:
        print "NO"