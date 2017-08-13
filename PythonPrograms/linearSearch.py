__author__ = 'kh1911'


def linearSearch(numbers, key):
    flag=0
    for i in range(len(numbers)):
        if numbers[i]==key:
            flag=1
            break

    if flag==1:
        return i
    else:
        return -1

def binarySearch(numbers, key):
    low=0
    flag=0
    high=len(numbers) - 1
    if key<numbers[low] or key>numbers[high]:
        return -1


    while low<=high:
        mid=(low+high)/2
        if numbers[mid]==key:
            flag=1
            return mid
        elif key>numbers[mid]:
            low=mid+1
        else:
            high=mid-1

    if flag == 0:
        return -1


print binarySearch([1,3,5,6,9,15,23], 15)
#print linearSearch([23,1,36,11,43,92,1,45,21,53,23], 21)
#print linearSearch([23,1,36,11,43,92,1,45,21,53,23], 44)
