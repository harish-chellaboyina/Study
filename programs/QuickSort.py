__author__ = 'kh1911'


def sortUtil(array, start, end):

    if start<end:
        pass
    else:
        return

    pivot = array[end]

    pIndex = 0

    i = start

    while i < end:

        if array[i] <= pivot:
            temp = array[pIndex]
            array[pIndex] = array[i]
            array[i] = temp
            pIndex += 1
        i += 1

    temp = array[pIndex]
    array[pIndex] = pivot
    array[end] = temp

    sortUtil(array, start, pIndex - 1)
    sortUtil(array, pIndex + 1, end)





def quickSort(array):
    sortUtil(array, 0, len(array) - 1)
    pass

print quickSort([2,7,3,1,4,2,8,5,6])