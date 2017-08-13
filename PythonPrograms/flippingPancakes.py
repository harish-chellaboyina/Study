
def flip(ch):
    if ch == '+':
        return '-'
    return '+'

def flipBits(pancakes, index, k):
    i = 0
    while i < k:
        pancakes[index + i] = flip(pancakes[index + i])
        i += 1
    return pancakes


t = input()
p = 1
while t:
    t -= 1
    string, k = raw_input().split()
    k = int(k)
    string = list(string)
    i = 0
    count = 0
    while i  < len(string):
        if string[i] == '-':
            if i + k > len(string):
                count = "IMPOSSIBLE"
                break
            string = flipBits(string, i, k)
            count += 1
        i += 1
    print "Case #" + str(p) + ": " + str(count)
    p += 1

