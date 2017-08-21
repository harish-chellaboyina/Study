package DynamicProgramming;

import java.util.Stack;

public class LongestCommomSubsequence {
	
	private static void printLongestCommonSubstring(int t[][], int m, int n, char[] a) {
		int i = m - 1, j = n - 1;
		
		Stack<Character> st = new Stack<Character>();
		
		while (i >=1 && j >= 1) {
			if (t[i][j]!= t[i - 1][j] && t[i][j] != t[i][j - 1]) {
				st.push(a[i - 1]);
				i--;j --;
			} else if (t[i][j] == t[i - 1][j]){
				i --;
			} else {
				j --;
			}
		}
		
		System.out.print("Longest common subsequene is - ");
		while (!st.isEmpty())
			System.out.print(st.pop() + " ");
		System.out.println();
	}
	
	public int findLongestCommonSubstring(char a[], char b[]) {
		
		int[][] t = new int[a.length + 1][b.length + 1];
		
		for (int i = 1;i <= a.length;i ++) {
			for (int j = 1;j <= b.length;j ++) {
				if (a[i - 1] == b[j - 1])
					t[i][j] = t[i - 1][j - 1] + 1;
				else
					t[i][j] = Math.max(t[i-1][j], t[i][j - 1]);
			}
		}
		printLongestCommonSubstring(t, a.length + 1, b.length + 1, a);
		return t[a.length][b.length];
	}
	
	public static void main(String[] args) {
		String a = "abcdaf";
		String b = "acbcf";
		LongestCommomSubsequence lcs = new LongestCommomSubsequence();
		
		System.out.println("Length is " + lcs.findLongestCommonSubstring(a.toCharArray(), b.toCharArray()));
		
	}

}
