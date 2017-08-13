__author__ = 'kh1911'


def bubbleSort(numbers):
    flag=0
    for j in range(len(numbers) - 1):
        for i in range(len(numbers)-j-1):
            if numbers[i]>numbers[i+1]:
                temp=numbers[i]
                numbers[i]=numbers[i+1]
                numbers[i+1]=temp
                flag=1
        if flag==0:
            return numbers
    return numbers

def selectionSort(numbers):

    for i in range(len(numbers) - 1):

        min = numbers[i]
        minPosition = i
        j = i + 1
        while j < len(numbers):
            if min > numbers[j]:
                min = numbers[j]
                minPosition = j
            j += 1

        if minPosition != i:
            temp = numbers[minPosition]
            numbers[minPosition] = numbers[i]
            numbers[i] = temp
    return numbers

print bubbleSort([2,1,6,3,4,7,8,5])
print selectionSort([2,1,6,3,4,7,8,5])