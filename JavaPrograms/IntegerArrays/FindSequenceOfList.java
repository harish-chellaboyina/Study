package IntegerArrays;

/*
 * Author : Harish
 * Logic Provider: Rajesh Babu
 */
public class FindSequenceOfList {
	
	
	public void perform(int a[]) {
		String INCREASING_SEQUENCE_KEY = "Increasing";
		String DECREASING_SEQUENCE_KEY = "Decreasing";
		
		String startSequence = null;
		String endSequence = null;
		if (a.length <= 1) {
			System.out.println("Type unknown. Size should be greater than 2");
			return;
		} else if (a.length == 2) {
			if (a[0] < a[1])
				startSequence = INCREASING_SEQUENCE_KEY;
			else
				startSequence = DECREASING_SEQUENCE_KEY;
		} else if (a.length == 3) {
			if (a[0] < a[1])
				startSequence = INCREASING_SEQUENCE_KEY;
			else
				startSequence = DECREASING_SEQUENCE_KEY;
			
			if (a[1] < a[2])
				endSequence = INCREASING_SEQUENCE_KEY;
			else
				endSequence = DECREASING_SEQUENCE_KEY;
		} else {
			if (a[0] < a[1])
				startSequence = INCREASING_SEQUENCE_KEY;
			else
				startSequence = DECREASING_SEQUENCE_KEY;
			
			if (a[a.length - 2] < a[a.length - 1])
				endSequence = INCREASING_SEQUENCE_KEY;
			else
				endSequence = DECREASING_SEQUENCE_KEY;

		}
		String output = "";
		if (startSequence != null) {
			output = startSequence;
			if (endSequence != null && endSequence != startSequence)
				output += " & " + endSequence;
		}
		System.out.println(output);

	}
	
	public static void main(String args[]) {
		int a[] = {1, 2, 3, 4, 5};
		int b[] = {5, 4, 3, 2, 1};
		int c[] = {5, 4, 3, 6, 7};
		int d[] = {2, 5, 7, 4, 1};
		
		FindSequenceOfList obj = new FindSequenceOfList();
		obj.perform(a);
		obj.perform(b);
		obj.perform(c);
		obj.perform(d);
		
		
	}

}
