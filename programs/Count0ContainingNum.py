__author__ = 'kh1911'

def countNumWith0(numOfDigits):
    totalNum = pow(10, numOfDigits) - pow(10, numOfDigits - 1)
    return totalNum - (pow(9, numOfDigits))

def getNumLength(number):
    return len(str(number))
def getNearest10Power(number):
    temp = 1
    while pow(10, temp) < number:
        temp += 1
    return temp - 1

def has0(number):
    number = str(number)
    return number.__contains__("0")


def count0Containers(n):
    if n < 10:
        return
    if n < 100:
        return n/10
    temp = getNearest10Power(n)
    res = countNumWith0(temp)
    """
    for i in range(pow(10, temp), n + 1):
        if has0(i):
            res += 1"""

    remaining = n % pow(10, temp)
    pro = n / pow(10, temp)
    print n, res
    return res + (count0Containers(remaining) + (9 * (getNumLength(remaining) - 1))) + 1

n = input()
print count0Containers(n)