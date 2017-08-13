__author__ = 'kh1911'

numOfLines = input()

lineNumber = 1

numOfLines = numOfLines / 2

while lineNumber <= numOfLines:

    a =  " " * (numOfLines - lineNumber)
    b =  "* " * lineNumber

    print a + b


    lineNumber = lineNumber + 1

lineNumber = numOfLines - 1

while lineNumber >= 1:

    a =  " " * (numOfLines - lineNumber)
    b =  "* " * lineNumber

    print a + b


    lineNumber = lineNumber - 1