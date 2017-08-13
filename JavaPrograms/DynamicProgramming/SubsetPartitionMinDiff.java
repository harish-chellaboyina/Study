package DynamicProgramming;

//A dynamic programming based Java program for partition problem
import java.io.*;

class SubsetPartitionMinDiff {

 static int findPartition (int arr[], int n)
 {
     int sum = 0;
     int i, j;

     for (i = 0; i < n; i++)
         sum += arr[i];

     int[][] dp=new int[sum/2+1][n+1];
     
     for (i = 0;i <= sum/2;i ++) {
    	 for (j = 0;j <= n;j ++) {
    		 dp[i][j] = -1;
    	 }
     }

     for (i = 0; i <= n; i++)
         dp[0][i] = 0;


     for (i = 1; i <= sum/2; i++)
     {
         for (j = 1; j <= n; j++)
         {
             dp[i][j] = dp[i][j-1];
             if (dp[i][j] != -1)
            	 continue;
             if (arr[j - 1] == i) {
            	 dp[i][j] = 1;
            	 continue;
             }
             if (i >= arr[j-1])
            	 if (dp[i - arr[j-1]][j-1] != -1)
            		 dp[i][j] = dp[i - arr[j-1]][j-1] + 1;
         }
     }
     int minDiff = 999999999;
     
     for (j = sum/2;j >= 0;j --) {
    	 if (dp[j][n] != -1) {
    		 int currDiff = Math.abs(j + dp[j][n] - (sum - j));
    		 if (currDiff < minDiff)
    			 minDiff = currDiff;
    		 currDiff = Math.abs(j - (sum - j + (n - dp[j][n])));
    		 if (currDiff < minDiff)
    			 minDiff = currDiff;
    	 }
     }
     
     return minDiff;
 }

 public static void main (String[] args)
 {
     int arr[] = {20};
     int n = arr.length;
     System.out.println(findPartition(arr, n));
//     if (findPartition(arr, n) == true)
//         System.out.println("Can be divided into two " 
//        		 +
//                            "subsets of equal sum");
//     else
//         System.out.println("Can not be divided into"
//                  +       " two subsets of equal sum");

 }
}
