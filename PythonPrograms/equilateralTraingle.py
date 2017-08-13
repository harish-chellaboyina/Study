__author__ = 'Rajesh'

n=input()

n = n/2
lineno=1

while lineno <= n:
    a =  " "*(n-lineno)
    b = "* " *lineno

    print a+b
    lineno = lineno +1


lineno = n-1

while lineno >=1:

    a =  " "*(n-lineno)
    b = "* " *lineno

    print a+b
    lineno = lineno -1