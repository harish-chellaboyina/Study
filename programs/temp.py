__author__ = 'kh1911'
import time
import decimal
"""
    This function takes list of numbers as input and returns the maximum element of the list
"""
""""
from itertools import permutations
perms = [''.join(p) for p in permutations('aab')]
print list(set(perms))
def findGreatest(numbers):
    greatest=numbers[0]
    for i in range(len(numbers)):
        if numbers[i]>greatest:
            greatest=numbers[i]

    return greatest

def secondGreatest(numbers):
    greatest=numbers[0]
    secondGreat = numbers[0]
    for i in range(len(numbers)):
        if numbers[i]>greatest:
            secondGreat = greatest
            greatest=numbers[i]
        elif secondGreat<numbers[i]:
            secondGreat=numbers[i]

    return secondGreat"""

def sqr3(n, numbeOfDecimals):
    #0.0000000001
    #1.7320508075688772

    a = time.time()


    k = 1.0
    requiredPrecision = 1/float(pow(10, numbeOfDecimals))
    while((k*k - n) > requiredPrecision or (n - k * k) > requiredPrecision):
            k = (decimal.Decimal(k) + decimal.Decimal(n / k)) / 2

    #print time.time() - a
    return k

def sqrnew(n, numbeOfDecimals):
    #0.0000000001
    #1.7320508075688772

    a = time.time()


    k = 1.0
    j = 0
    while j < 40:
            k = (decimal.Decimal(k) + decimal.Decimal(n / k)) / 2
            j += 1
            print k

    #print time.time() - a
    return k

# A Naive recursive Python program to fin minimum number
# operations to convert str1 to str2
def editDistance(str1, str2, m , n):

    # If first string is empty, the only option is to
    # insert all characters of second string into first
    if m==0:
         return n

    # If second string is empty, the only option is to
    # remove all characters of first string
    if n==0:
        return m

    # If last characters of two strings are same, nothing
    # much to do. Ignore last characters and get count for
    # remaining strings.
    if str1[m-1]==str2[n-1]:
        return editDistance(str1,str2,m-1,n-1)

    # If last characters are not same, consider all three
    # operations on last character of first string, recursively
    # compute minimum cost for all three operations and take
    # minimum of three values.
    return 1 + min(editDistance(str1, str2, m, n-1),    # Insert
                   editDistance(str1, str2, m-1, n),    # Remove
                   editDistance(str1, str2, m-1, n-1)    # Replace
                   )

# Driver program to test the above function
str1 = "abc"
str2 = "def"
print editDistance(str1, str2, len(str1), len(str2))



# This code is contributed by Bhavya Jain

#print secondGreatest([234,14,125,3124,1,523,41,3])
#print sqr3(3, 4)
#print sqr3(12763512763571235123, 3)
