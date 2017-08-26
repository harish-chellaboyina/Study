package SortingAlgorithms;

public class BubbleSort {
	
	public void sort(int a[]) {
		for (int i = 0; i < a.length; i++) {
			boolean swapHappened = false;
			for (int j = 0;j < a.length - i - 1;j ++) {
				if (a[j] > a[j + 1]) {
					swapHappened = true;
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
			if (!swapHappened)
				break;
		}
	}
	
	public static void main(String[] args) {
		int a[] = {3,1,2,4,1,8,4,6,5};
		
		BubbleSort bs = new BubbleSort();
		bs.sort(a);
		for (int i = 0;i < a.length;i ++)
			System.out.print(a[i] + " ");
	}

}
