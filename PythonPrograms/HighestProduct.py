def maxp3(A):
        
        i = 0
        n = len(A)
        while i < 3:
            maxi = i
            j = i + 1
            while j < n:
                if A[j] > A[maxi]:
                    maxi = j
                j += 1
            temp = A[i]
            A[i] = A[maxi]
            A[maxi] = temp
            i += 1
            
        i = 0
        while i < 2:
            maxi = i
            j = i + 1
            while j < n - i:
                if A[j] < A[maxi]:
                    maxi = j
                j += 1
            temp = A[n - i - 1]
            A[n - i - 1] = A[maxi]
            A[maxi] = temp
            i += 1
        print A
        return max((A[0] * A[1] * A[2]), A[0] * A[n - 1] * A[n - 2])


print maxp3([ -65, 41, 15, -11, 69, 23, -63, -4, 49, -99, -61, 17, -61 ])
a = 5

if a > 6:
    print "Hello"
elif a == 5:
    print "World"
else:
    print "nothing"