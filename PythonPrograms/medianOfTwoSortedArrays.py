__author__ = 'kh1911'

def findMedianSortedArrays(A, B):

        b = None
        a = None
        m = len(A)
        n = len(B)

        i = 0
        j = 0

        if m == 0:
            if n % 2 == 0:
                return (B[n/2 - 1] + B[(n/2)])/2
            return B[n/2]
        if n == 0:
            if m % 2 == 0:
                return (A[m/2 - 1] + A[(m/2)])/2
            return A[m/2]

        count = 0
        while True:
            num = None
            if i >= m:
                num = B[j]
                j += 1
            elif j >= n:
                num = A[i]
                i += 1
            else:
                if A[i] < B[j]:
                    num = A[i]
                    i += 1
                else:
                    num = B[j]
                    j += 1

            count += 1
            if count  == (m + n) /2:
                a = num
                if (m + n) % 2 == 0:
                    if i >= m:
                        b = B[j]
                    elif j >= n:
                        b = A[i]
                    else:
                        if A[i] < B[j]:
                            b = A[i]
                        else:
                            b = B[j]

                break
        if b != None:
            return (a + b) / 2
        return a

a = [1, 2, 3]
b = [2, 3, 6, 7]
print findMedianSortedArrays(a, b)

a = a + b
a.sort()
print a
t = len(a)

if t % 2 == 0:
    print (a[t/2 - 1] + a[(t/2)])/2
else:
    print a[t/2]