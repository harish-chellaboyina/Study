package StringPrograms;

public class PermutationsOfString {
	
	private void swap(char a[], int i, int j) {
		if (i != j) {
			char temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	public void printPermutations(char a[], int start, int end) {
		
		if (start == end) {
			for (int i = 0;i < a.length;i ++) {
				System.out.print(a[i]);
			}
			System.out.println();
			return;
		}
			
		
		for (int i = start;i <= end; i++) {
			swap(a, start, i);
			printPermutations(a, start + 1, end);
			swap(a, i, start);
		}
	}
	
	public static void main(String[] args) {
		PermutationsOfString ps = new PermutationsOfString();
		char a[] = {'A', 'B', 'C'};
		ps.printPermutations(a, 0, a.length - 1);
	}

}
