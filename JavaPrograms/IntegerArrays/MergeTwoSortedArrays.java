package IntegerArrays;

public class MergeTwoSortedArrays {
	
	public int[] mergeTwoSortedArrays (int a[], int b[], int m, int n) {
		int lastIndex = m + n - 1;
		
		while (lastIndex >= 0) {
			if (n <= 0) {
				a[lastIndex --] = a[m - 1] ;
				m --;
			} else if(m <= 0) {
				a[lastIndex --] = b[n - 1] ;
				n --;
			} else {
				if (a[m - 1] > b[n - 1]) {
					a[lastIndex --] = a[m - 1] ;
					m --;
				} else {
					a[lastIndex --] = b[n - 1] ;
					n --;
				}
			}	
		}
		
		return a;
	}
	
	public static void main(String args[]) {
		MergeTwoSortedArrays obj = new MergeTwoSortedArrays();
		int a[] = {2,3,5,8,9,0,0,0,0};
		int b[] = {1,4,7,11};
		a = obj.mergeTwoSortedArrays(a, b, 5, b.length);
		
		String outputStr = "";
		for (int i = 0;i < a.length;i++)
			 outputStr += a[i] + " ";
		System.out.println(outputStr);
	}
	

}
