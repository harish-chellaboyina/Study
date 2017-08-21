package DynamicProgramming;

import IntegerArrays.LargestAreaInHistogram;

public class MaximumAreaRextangleInMatrix {
	
	public int findMaximumAreaRextangleInMatrix(int t[][]) {
		int max = 0;
		int[] temp = new int[t[0].length];
		
		LargestAreaInHistogram lah = new LargestAreaInHistogram();
		
		for (int i = 0;i < t.length;i ++) {
			for (int j = 0;j < t[0].length;j ++) {
				if (t[i][j] == 0)
					temp[j] = 0;
				else
					temp[j] += t[i][j];
			}
			int currMax = lah.findLargestArea(temp);
			if (currMax > max)
				max = currMax;
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		MaximumAreaRextangleInMatrix mar = new MaximumAreaRextangleInMatrix();
		int t[][] = {
				{1,1,1,0},
                {1,1,1,1},
                {1,1,1,0},
                {0,1,1,1},
                {1,0,0,1},
                {1,1,1,1}};
		
		System.out.println(mar.findMaximumAreaRextangleInMatrix(t));
	}

}
