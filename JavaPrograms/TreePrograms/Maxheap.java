package TreePrograms;

public class Maxheap {
	
	private void swap(int a[], int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	private void heapify(int a[], int index, int length) {
		int leftChild = 2 * index;
		int rightChild = leftChild + 1;
		
		int largest = index;
		if (leftChild < length && a[leftChild] > a[largest])
			largest = leftChild;
		if (rightChild < length && a[rightChild] > a[largest])
			largest = rightChild;
		
		if (largest != index) {
			swap(a, largest, index);
			heapify(a, largest, length);
		}
	}
	
	public void buildHeap(int a[]) {
		
		for (int i = a.length/2;i >= 0;i--) {
			heapify(a, i, a.length);
		}
	}
	
	public int getMaxFromHeap(int a[], int length) {
		int max = a[0];
		a[0] = a[length - 1];
		heapify(a, 0, length - 1);
		return max;
	}
	
	public static void main(String[] args) {
		Maxheap mh = new Maxheap();
		int a[] = {3,1,2,4,1,8,4,6,5};
		
		mh.buildHeap(a);
		
		for (int i = 0;i < a.length;i ++) {
			System.out.print(mh.getMaxFromHeap(a, a.length - i) + " ");
		}
		
	}

}
