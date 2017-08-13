__author__ = 'kh1911'

def findMedian(A):
        m = len(A)
        n = len(A[0])

        t = [0] * m

        i = 0
        k = ((m * n)/2) + 1

        while True:
            j = 0
            minIndex = 0
            minValue = 99999999999
            while j < m:
                if t[j] < n and A[j][t[j]] < minValue:
                    minIndex = j
                    minValue = A[j][t[j]]
                j += 1
            i += 1
            t[minIndex] += 1
            if i == k:
                return minValue

print findMedian([[1,3,5], [2,6,9], [3,6,9]])
print findMedian([[1]])