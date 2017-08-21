package DynamicProgramming;

public class MinEditDistance {
	
	public int findMinDistance(char[] a, char[] b) {
		
		int[][] t = new int[a.length + 1][b.length + 1];
		
		for (int i = 1;i <= a.length;i ++) {
			for (int j = 1;j <= b.length;j ++) {
				if (a[i - 1] == b[j - 1])
					t[i][j] = t[i - 1][j - 1];
				else
					t[i][j] = 1 + Math.min(t[i-1][j], Math.max(t[i][j - 1], t[i - 1][j - 1]));
			}
		}
		
		return t[a.length][b.length];
	}
	
	public static void main(String[] args) {
		MinEditDistance med = new MinEditDistance();
		String a = "sunday";
		String b = "saturday";
		
		System.out.println(med.findMinDistance(a.toCharArray(), b.toCharArray()));
	}
}
