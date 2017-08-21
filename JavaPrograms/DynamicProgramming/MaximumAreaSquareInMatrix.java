package DynamicProgramming;

public class MaximumAreaSquareInMatrix {
	
	public int findMaximumAreaSquareInMatrix(int[][] t) {
		int m = t.length, n = t[0].length;
		for (int i = 1;i < m; i++) {
			for (int j = 1;j < n;j++) {
				if (t[i][j] == 1) {
					t[i][j] = 1 + Math.min(t[i - 1][j - 1], Math.min(t[i - 1][j], t[i][j - 1]));
				}
			}
		}
		int max = t[0][0];
		for (int i = 0;i < m; i++) {
			for (int j = 0;j < n;j++) {
				if (t[i][j] > max)
					max = t[i][j];
			}
		}
		return max;
	}
	public static void main(String[] args) {
		MaximumAreaSquareInMatrix mas = new MaximumAreaSquareInMatrix();
        int t[][] = {{0,1,1,0,1},{1,1,1,0,0},{1,1,1,1,0},{1,1,1,0,1}};
        System.out.println(mas.findMaximumAreaSquareInMatrix(t));

	}
}
