__author__ = 'kh1911'

import urllib

url = "http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt"

a = urllib.urlopen(url).read()
words = a.split()

d = {}
for each in words:
    a = list(each)
    a.sort()
    a = ''.join(a)
    if d.has_key(a):
        d[a].append(each)
    else:
        d[a] = [each]

final = []
maxLen = 0

for each in d:
    #print len(d[each])
    if len(d[each]) > maxLen:
        maxLen = len(d[each])
        max = d[each]


print max, maxLen