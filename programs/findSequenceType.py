__author__ = 'kh1911'

numbers = [4,2,1,5,6]

if numbers[0] < numbers[1]:
    a = "increasing"
else:
    a = "decreasing"


if numbers[len(numbers) - 1] < numbers[len(numbers) - 2]:
    b = "decreasing"
else:
    b = "increasing"

if a == b:
    print a
else:
    print a + " & " + b

