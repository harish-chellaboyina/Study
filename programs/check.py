# RETURN AN EMPTY LIST IF NO ANAGRAM FOUND

def compareDictionaries(a, b):
    if len(a.keys()) != len(b.keys()):
        return False
    keysList = a.keys()
    i = 0
    while i < len(keysList):
        if b.has_key(keysList[i]) and (a[keysList[i]] == b[keysList[i]]):
            pass
        else:
            return False
        i += 1
    return True

def getAnagramIndices(haystack, needle):
    d = {}
    t = {}
    i = 0
    if len(needle) > len(haystack):
        return []
    outputList = []
    while i < len(needle):
        if t.has_key(needle[i]):
            t[needle[i]] += 1
        else:
            t[needle[i]] = 1
        i += 1

    i = 0
    while i < len(needle):
        if d.has_key(haystack[i]):
            d[haystack[i]] += 1
        else:
            d[haystack[i]] = 1
        i += 1

    j = 0
    if compareDictionaries(d, t) == True:
        outputList.append(j)

    while i < len(haystack):

        if d.has_key(haystack[i]):
            d[haystack[i]] += 1
        else:
            d[haystack[i]] = 1

        if d[haystack[j]] == 1:
            del d[haystack[j]]
        else:
            d[haystack[j]] -= 1

        if compareDictionaries(d, t) == True:
            outputList.append(j + 1)

        i += 1
        j += 1

    return outputList

print getAnagramIndices("abcabdefcabc", "cab")