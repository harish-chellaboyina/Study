package StringPrograms;

import java.util.ArrayList;
import java.util.List;

public class ShortestWordLadder {
	
	private boolean isAdjacent(String a, String b) {
		
		int count = 0;
		
		for (int i = 0;i < Math.min(a.length(), b.length()); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				count ++;
			}
		}
		count += Math.abs(a.length() - b.length());
		if (count > 1)
			return false;
		return true;
	}
	
	public int findShortestLadder (String source, String target, List<String> words) {
		
		int count = 0;
		
		List<String> currList = new ArrayList<String>();
		List<String> tempList = null;
		currList.add(source);
		
		while (true) {
			tempList = new ArrayList<String>();
			List<Integer> removeList = new ArrayList<Integer>();
			for (int i = 0;i < currList.size();i ++){
				String src = currList.get(i);
				if (src.equals(target))
					return count;
				if (words.size() == 0)
					return -1;
				
				
				for (int j = 0;j < words.size();j ++) {
					if (isAdjacent(src, words.get(j))) {
						tempList.add(words.get(j));
						removeList.add(j);
					}
				}
				
			}
			for (int j = 0;j < removeList.size();j ++) {
				words.remove(removeList.get(j));
			}
			currList.clear();
			currList = tempList;
			count ++;
			
		}
	}
	
	public static void main(String[] args) {
		ShortestWordLadder swl = new ShortestWordLadder();
		List<String> D = new ArrayList<String>();
		D.add("poo");
		D.add("pooe");
		D.add("same");
		D.add("peon");
		D.add("plie");
		D.add("poin");
		D.add("peen");
		
		System.out.println(swl.findShortestLadder("poon", "peen", D));
	}

}
