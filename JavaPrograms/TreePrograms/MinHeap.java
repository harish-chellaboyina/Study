package TreePrograms;

public class MinHeap {
	
	private static void heapify(int a[], int i) {
		int largest = i;
		int leftChild = 2 * i + 1;
		int rightChild = (2 * i) + 2;
		
		if (leftChild < a.length && a[leftChild] < a[largest])
			largest = leftChild;
		
		if (rightChild < a.length && a[rightChild] < a[largest])
			largest = rightChild;
		
		if (largest != i) {
			//Swap
			int temp = a[i];
			a[i] = a[largest];
			a[largest] = temp;
			
			heapify(a, largest);
		}
	}
	
	public int getMin(int a[], int length) {
		int minVal = a[0];
		a[0] = a[length - 1];
		heapify(a, 0);
		return minVal;
	}
	
	public int[] buildHeap(int a[]) {
		if (a.length == 1)
			return a;
		
		for (int i = (a.length -1) /2;i >= 0;i --)
			heapify(a, i);
		return a;
	}
	
	public static void main(String[] args) {
		int a[] = {3,1,2,4,1,8,4,6,5};
		MinHeap mh = new MinHeap();
		mh.buildHeap(a);
		for (int i = 0;i < a.length;i ++)
			System.out.print(mh.getMin(a, a.length - i) + " ");
	}
	

}
