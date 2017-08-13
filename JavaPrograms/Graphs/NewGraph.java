package Graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NewGraph {
	int numOfVertices;
	LinkedList[] adj;
	
	public NewGraph(int v) {
		numOfVertices = v;
		adj = new LinkedList[v];
		for (int i = 0;i < v;i++)
			adj[i] = new LinkedList();
	}
	
	public void addEdge(int sourceVertex, int targetVertex) {
		adj[sourceVertex].add(targetVertex);
	}
	
	public void topologicalSortUtil(int vertex, boolean[] visited, Stack st) {
		visited[vertex] = true;
		LinkedList node = adj[vertex];
		for (int i = 0;i < node.size();i ++) {
			if (!visited[(Integer) node.get(i)]){
				topologicalSortUtil((Integer) node.get(i), visited, st);
			}
		}
		st.push(vertex);
	}
	
	public void topologicalSort(boolean[] visited, Stack st){
		
		for (int i = 0;i < numOfVertices; i++) {
			if (!visited[i]) {
				topologicalSortUtil(i, visited, st);
			}
		}
	}
	
	public void addWordsToGraph(NewGraph ng, List<String> words) {
		for (int i = 0;i < words.size() - 1;i++) {
			String word1 = words.get(i);
			String word2 = words.get(i + 1);
			
			for (int j = 0; j < Math.min(word1.length(), word2.length());j++){
				if (word1.charAt(j) != word2.charAt(j)) {
					ng.addEdge(word1.charAt(j), word2.charAt(j));
				}
			}
		}
	}
	
	public static void main(String args[]){
		NewGraph ng = new NewGraph(6);
		ng.addEdge(5, 2);
	    ng.addEdge(5, 0);
	    ng.addEdge(4, 0);
	    ng.addEdge(4, 1);
	    ng.addEdge(2, 3);
	    ng.addEdge(3, 1);
	    
	    boolean[] visited = new boolean[6];
	    Stack st = new Stack();
	    
	    for (int i = 0;i < 6;i++) {
	    	visited[i] = false;
	    }
	    
	    ng.topologicalSort(visited, st);
	    while (st.empty()==false)
	         System.out.print(st.pop() + " ");

	}
}
