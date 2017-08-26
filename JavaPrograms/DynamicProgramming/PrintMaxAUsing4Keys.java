package DynamicProgramming;

public class PrintMaxAUsing4Keys {
	
	public int findMaxAUsing4Keys(int n) {
		if (n < 7)
			return n;
		
		int[] t = new int[n + 1];
		int i;
		
		for (i = 1;i < 7;i ++)
			t[i] = i;
		
		for (;i <= n;i ++) {
			for (int k = i - 3;k >= 0;k --) {
				t[i] = Math.max(t[i], t[k] * (i - k - 1));
			}
		}
		
		return t[n];
	}
	
	public static void main(String[] args) {
		PrintMaxAUsing4Keys m4 = new PrintMaxAUsing4Keys();
		for (int i = 0;i <= 20;i++)
			System.out.println(m4.findMaxAUsing4Keys(i));
	}

}
