package TreePrograms;

public class SegmentTree {
	
	public static void constructSegmentTree(int input[], int segTree[], int low, int high, int pos) {
 		if (low == high)  {
 			segTree[pos] = input[low];
 			return;
 		}
 		
 		int mid = (low + high)/2;
 		
 		constructSegmentTree(input, segTree, low, mid, 2 * pos + 1);
 		constructSegmentTree(input, segTree, mid + 1, high, 2 * pos + 2);
 		
 		segTree[pos] = Math.min(segTree[2*pos + 1], segTree[2 * pos + 2]);
	}
	
	public static void updateSegmentTree(int index, int value, int segTree[], int low, int high, int pos) {
 		
		if (index < low || index > high)
			return;
		
		if (low == high) {
 			segTree[pos] = value;
 			return;
 		}
 		
 		int mid = (low + high) / 2;
 		
 		updateSegmentTree(index, value, segTree, low, mid, 2 * pos + 1);
 		updateSegmentTree(index, value, segTree, mid + 1, high, 2 * pos + 2);
 		
 		segTree[pos] = Math.min(segTree[2*pos + 1], segTree[2*pos + 2]);
	}
	
	public static int rangeQuery(int segTree[], int qlow, int qhigh, int low, int high, int pos) {
		
		
		//Completely overlapping
		if (qlow <= low && qhigh >= high) {
			return segTree[pos];
		}
		
		//No overlap
		if (qlow > high || qhigh < low)
			return Integer.MAX_VALUE;
		
		//Partial Overlap
		int mid = (low + high) / 2;
		int a = rangeQuery(segTree, qlow, qhigh, low, mid, 2*pos + 1);
		int b = rangeQuery(segTree, qlow, qhigh, mid + 1, high, 2*pos + 2);
		
		return Math.min(a, b);
	}
	
	public static void main(String[] args) {
		int a[] = {-1,2,4,0};
		int[] segTree = new int[2 * a.length - 1];
		constructSegmentTree(a, segTree, 0, a.length - 1, 0);
//		for(int i = 0;i < segTree.length;i++)
//			System.out.print(segTree[i] + " ");
		updateSegmentTree(2, -2, segTree, 0, a.length, 0);
		System.out.println("Range is " + rangeQuery(segTree, 1, 3, 0, a.length - 1, 0));
	}

}
