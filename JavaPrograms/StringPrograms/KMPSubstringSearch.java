package StringPrograms;

public class KMPSubstringSearch {
	
	private static int singleCharSearch(char[] text, char pattern) {
		for (int i = 0;i < text.length;i ++)
			if (text[i] == pattern)
				return i;
		return -1;
	}
	
	private static int[] buildPrefixArrayForPattern(char[] pattern) {
		int[] tempArray = new int[pattern.length];
		int j = 0;
		for (int i = 1;i < pattern.length;) {
			if (pattern[i] == pattern[j]) {
				tempArray[i] = j + 1;
				j ++;
				i ++;
			} else {
				if (j != 0)
					j = tempArray[j - 1];
				else {
					tempArray[i] = 0;
					i ++;
				}
			}
		}
		return tempArray;
	}
	
	public int KMP(char[] text, char[] pattern) {
		if (pattern.length > text.length)
			return -1;
		if (pattern.length == text.length)
			if (pattern.equals(text))
				return 0;
			else
				return -1;
		if (pattern.length == 1) 
			return singleCharSearch(text, pattern[0]);
		
		int[] prefixArray = buildPrefixArrayForPattern(pattern);
		
		int i = 0, j = 0;
		
		while (i < text.length && j < pattern.length) {
			if (text[i] == pattern[j]) {
				i ++;j++;
			} else {
				if (j != 0)
					j = prefixArray[j - 1];
				else
					i++;
			}
		}
		if (j == pattern.length)
			return i - pattern.length;
		return -1;
	}
	
	public static void main(String[] args) {
		KMPSubstringSearch kmp = new KMPSubstringSearch();
		System.out.println(kmp.KMP("abcxabcdabcdabcy".toCharArray(), "abcdabcy".toCharArray()));
	}
}
