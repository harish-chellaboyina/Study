

list1 = [1, 2, 3, 4, 6, 9]
list2 = [3, 4, 5]

length = min(len(list1), len(list2))

mergedList = []
i = 0

while i < length:
    mergedList.append(list1[i])
    mergedList.append(list2[i])
    i += 1

if len(list1) > length:

    while i < len(list1):
        mergedList.append(list1[i])
        i += 1
else:
    while i < len(list2):
        mergedList.append(list2[i])
        i += 1

print mergedList
