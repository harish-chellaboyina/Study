package DynamicProgramming;

public class KnapsackProblem {
	
	public int getMaxTotal(int[] val, int[] wt, int maxWeight) {
		
		int[][] t = new int[wt.length + 1][maxWeight + 1];
		
		for (int i = 1;i <= wt.length;i ++) {
			for (int j = 1;j <= maxWeight;j ++) {
				t[i][j] = t[i - 1][j];
				if (j - wt[i - 1] >= 0) {
					t[i][j] = Math.max(t[i][j], val[i - 1] + t[i - 1][j - wt[i - 1]]);
				}	
			}
		}
		return t[wt.length][maxWeight];
		
	}
	
	public static void main(String[] args) {
		KnapsackProblem kp = new KnapsackProblem();
		int[] val = {1, 4, 5, 7};
		int[] wt = {1, 3, 4, 5};
		System.out.println(kp.getMaxTotal(val, wt, 7));
	}
	

}
