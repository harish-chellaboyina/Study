package DynamicProgramming;

public class MinCutsToMakeAllStringsPalindrome {
	
	public int findMinCuts(char[] a) {
		
		int[][] t = new int[a.length][a.length];
		
		//Processing for length '2'
		for (int i = 0;i < a.length - 1;i++) {
			if (a[i] == a[i + 1])
				t[i][i + 1] = 0;
			else
				t[i][i + 1] = 1;
		}
		
		for (int l = 3;l <= a.length;l ++) {
			for (int i = 0;i + l - 1 < a.length;i++ ){
				int j = i + l - 1;
				if (a[i] == a[j])
					t[i][j] = t[i + 1][j - 1];
				else
					for (int k = i + 1;k < j;k ++) {
						t[i][j] = 1 + Math.min(t[i][j], t[i][k - 1] + t[k][j]);
					}
			}
		}
		
		return t[0][a.length - 1];
		
	}
	
	public static void main(String[] args) {
		MinCutsToMakeAllStringsPalindrome mc = new MinCutsToMakeAllStringsPalindrome();
		String a = "abcbz";
		System.out.println(mc.findMinCuts(a.toCharArray()));
	}

}
