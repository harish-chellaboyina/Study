package DynamicProgramming;

public class LongestPalindromicSubsequence {
	
	public int findLongestPalindromicSubsequence(char[] a) {
		int[][] t = new int[a.length][a.length];
		
		for (int i = 0;i < a.length;i ++)
			t[i][i] = 1;
		
		for (int l = 2;l <= a.length; l++) {
			for (int i = 0;i < a.length - l + 1;i++) {
				int j = i + l -1;
				if (l == 2 && a[i] == a[j]){
					t[i][j] = 2;
				}
				else if (a[i] == a[j]){
					t[i][j] = 2 + t[i + 1][j - 1];
				} else {
					t[i][j] = Math.max(t[i][j - 1], t[i + 1][j]);
				}
			}			
		}
		return t[0][a.length - 1];
	}

	public static void main(String[] args) {
		LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
		System.out.println(lps.findLongestPalindromicSubsequence("GEEKS FOR GEEKS".toCharArray()));
	}
}
