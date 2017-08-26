package SortingAlgorithms;

public class QuickSort {
	
	private void swap(int a[], int i, int j) {
		if (i != j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	private int getPartitionIndex(int a[], int start, int end) {
		int pIndex = start;
		int pivot = end;
		
		for (int i = start;i <= end - 1;i ++) {
			if (a[i] <= a[pivot]) {
				swap(a, i, pIndex);
				pIndex ++;
			}
		}
		swap(a, pivot, pIndex);
		return pIndex;
	}
	
	private void sortUtil(int a[], int start, int end) {
		if (start < end) {
			int partitionIndex =  getPartitionIndex(a, start, end);
			sortUtil(a, start, partitionIndex - 1);
			sortUtil(a, partitionIndex + 1, end);
		}
	}
	
	public void sort(int a[]) {
		sortUtil(a, 0, a.length - 1);
	}
	
	public static void main(String[] args) {
		int a[] = {3,1,2,4,1,8,4,6,5};
		QuickSort qs = new QuickSort();
		qs.sort(a);
		for (int i = 0;i < a.length;i ++)
			System.out.print(a[i] + " ");
	}

}
