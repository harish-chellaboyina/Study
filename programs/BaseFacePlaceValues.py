num = int(input())
fv = raw_input()
pv = int(input())

def baseN(num,b,numerals="0123456789abcdefghijklmnopqrstuvwxyz"):
    return ((num == 0) and numerals[0]) or (baseN(num // b, b, numerals).lstrip(numerals[0]) + numerals[num % b])


base = 2
op = ""
while base <= 36:
    s = list(baseN(num, base))
    s.reverse()
    if s[pv].upper() == fv.upper():
        op += str(base) + " "
    base += 1
print op

