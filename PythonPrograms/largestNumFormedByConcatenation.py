
def compare(a,b):
    length = min(len(a), len(b))

    i = 0
    while i < length:
        if int(a[i]) < int (b[i]):
            return True
        elif int(a[i]) > int (b[i]):
            return False
        i += 1
    if len(a) > len(b):
        return True
    return False


d = ["3", "30", "34", "9", "4"]
i = 0

while i < len(d):
    j = i + 1
    while j < len(d):
        if compare(d[i], d[j]) == True:
            temp = d[i]
            d[i] = d[j]
            d[j] = temp
        j += 1
    i += 1
print d