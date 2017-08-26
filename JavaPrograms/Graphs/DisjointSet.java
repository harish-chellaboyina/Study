package Graphs;

import java.util.HashMap;

public class DisjointSet {
	
	private HashMap<Integer, Node> hm = new HashMap<Integer, Node>();
	
	class Node {
		int rank = 0;
		int data;
		Node parent;
	}
	
	public void makeSet(int a) {
		Node t = new Node();
		t.data = a;
		t.parent = t;
		hm.put(a, t);
	}
	
	public int findSet(int data) {
		return findSet(hm.get(data)).data;
	}
	
	public Node findSet(Node t) {
		Node parent = t.parent;
		
		if( t == parent)
			return parent;
		
		t.parent = findSet(parent);
		return t.parent;
	}
	
	public void union(int a, int b) {
		Node parentA = findSet(hm.get(a));
		Node parentB = findSet(hm.get(b));
		
		if (parentA.data == parentB.data) {
			return;
		} else {
			if (parentA.rank > parentB.rank) {
				parentB.parent = parentA;
				parentA.rank = (parentA.rank == parentB.rank)? parentA.rank + 1 : parentA.rank;
			} else {
				parentA.parent = parentB;
				parentB.rank = (parentA.rank == parentB.rank)? parentB.rank + 1 : parentB.rank;
			}
		}
	}
	public static void main(String[] args) {
		Integer a = 3;
		Integer b = 2;
		System.out.println(b.compareTo(a));
	}

}
