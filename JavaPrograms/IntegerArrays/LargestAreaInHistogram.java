package IntegerArrays;

import java.util.Stack;

public class LargestAreaInHistogram {
	
	public int findLargestArea(int a[]) {
		int max = 0, i;
		
		Stack<Integer> st = new Stack<Integer>();
		
		for (i = 0;i < a.length;i ++) {
			if (st.isEmpty() || a[i] >= a[st.lastElement()]) {
				st.push(i);
			} else{
				while (!st.isEmpty() && a[st.lastElement()] > a[i]) {
					int top = st.pop();
					int area;
					if (st.isEmpty())
						area = a[top] * i;
					else
						area = a[top] * (i - st.lastElement() - 1);
					if (area > max)
						max = area;
				}
				st.push(i);
			}
		}
		
		while (!st.isEmpty()) {
			int top = st.pop();
			int area;
			if (st.isEmpty())
				area = a[top] * i;
			else
				area = a[top] * (i - st.lastElement() - 1);
			if (area > max)
				max = area;
		}
		return max;
	}
	
	public static void main(String[] args) {
	    int histogram[] = {6, 2, 5, 4, 5, 1, 6};
	    
	    LargestAreaInHistogram lah = new LargestAreaInHistogram();
	    System.out.println(lah.findLargestArea(histogram));

	}

}
