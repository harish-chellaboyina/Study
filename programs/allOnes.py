a = [9,6,5,0,8,2,1,3]

def maxHeapify(i):
     l = 2 * i
     r = 2 * i + 1

     largest = i

     if l < len(a) and a[l] < largest:
         largest = l

     if r < len(a) and a[r] < largest:
         largest = r

     if largest != i:
         temp = a[i]
         a[i] = a[largest]
         a[largest] = temp
         print a
         maxHeapify(largest)


p = len(a) / 2

while p >= 0:
    maxHeapify(p)
    p -= 1
print a