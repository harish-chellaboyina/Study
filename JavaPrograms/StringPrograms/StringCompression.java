package StringPrograms;

import java.util.Arrays;

public class StringCompression {
	
	public int writeAt(char a[], int index, int number) {
		if (number <= 1)
			return index;
		String num = Integer.toString(number);
		for (int i = 0;i < num.length();i++) {
			a[index++] = num.charAt(i);
		}
		return index;
	}
	
	public String compressString(String s) {
		if (s == null || s.length() <= 1)
			return s;
		
		char a[] = s.toCharArray();
		char temp = a[0];
		int count = 1, j = 1;
		
		for (int i = 1;i < a.length;i++) {
			if (a[i] == temp) {
				count++;
			} else {
				if (count > 1) {
					j = this.writeAt(a, j, count);
				}
				temp = a[i];
				a[j] = temp;
				j++;
				count = 1;
			}
		}
		j = this.writeAt(a, j, count);
		a = Arrays.copyOfRange(a, 0, j);

		return new String(a);
	}
	
	public static void main(String args[]) {
		StringCompression obj = new StringCompression();
		System.out.println(obj.compressString("AAAABBCCC"));
	}
}
