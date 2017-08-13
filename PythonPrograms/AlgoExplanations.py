__author__ = 'kh1911'

#Trapping rain water

#http://www.geeksforgeeks.org/trapping-rain-water/

"""
    First we calculate max arrays from left to right and right to left. So now for every element in array,
    we know the largest bar in its left as well as in its right.

    suppose left[] is the array holding left max bar, and right[] for right max

    Now we are more concerned abt the water present on every bar. So that if we add up all the water values present
    on every bar, we can get the total amount of water present on it.

    To know the amount of water present on every bar, we have to first find out the minimum of largest left side and right side bars

     i.e min (left[i], right[i])

     So this gives us the amout of water which can be present on the bar, now we have to add remove the length of the present bar from this value.

     min(left[i], right[i]) - arr[i]

     solution:
     for (int i = 0; i < n; i++)
       water += min(left[i],right[i]) - arr[i];

"""


#Minumum num of coins
