package Graphs;

import java.util.Stack;

public class FloydWarshallAllPairShortestPath {
	
	private static final int INF = Integer.MAX_VALUE;
	
	public int[][] findAllPairShortestPath(int[][] distance) {
		int[][] path = new int[distance.length][distance.length];
		int[][] d = new int[distance.length][distance.length];
		int length = distance.length;
		
		for (int i = 0;i < length;i ++) {
			for (int j = 0;j < length;j ++) {
				d[i][j] = distance[i][j];
				if (distance[i][j] != INF && i != j) {
					path[i][j] = i;
				} else {
					path[i][j] = -1;
				}
			}
		}
		
		for(int k = 0;k < length;k ++) {
			for (int i = 0;i < length;i ++) {
				for (int j = 0;j < length;j ++) {
					if (d[i][k] != INF && d[k][j] != INF){
						if (d[i][j] > d[i][k] + d[k][j]) {
							d[i][j] = d[i][k] + d[k][j];
							path[i][j] = path[k][j];
						}
					}
				}
			}
		}
		
		
		for (int i = 0;i < length;i ++) {
			for (int j = 0;j < length;j ++) {
				if (distance[i][j] < 0) {
					System.out.println("Negative weight cycle found");
					i = length;
					break;
				}
			}
		}
		
		printPath(path, 3, 2);
		
		return d;
	}
	
	public void printPath(int[][] path, int source, int destination) {
		
		if (source == destination)
			return;
		
		Stack<Integer> output = new Stack<Integer>();
		output.push(destination);
		while (true) {

			if (source == destination)
				break;
			if (path[source][destination] == -1)
				return;
			output.push(path[source][destination]);
			destination = path[source][destination];
		}
		
		while (!output.isEmpty())
			System.out.print(output.pop() + " ");
		
	}
	
	public static void main(String[] args) {
		int[][] graph = {
                {0,   3,   6,   15},
                {INF, 0,  -2,   INF},
                {INF, INF, 0,   2},
                {1,   INF, INF, 0}
        };

        FloydWarshallAllPairShortestPath shortestPath = new FloydWarshallAllPairShortestPath();
        int[][] distance = shortestPath.findAllPairShortestPath(graph);
        
        for (int i = 0;i < distance.length;i ++) {
			for (int j = 0;j < distance.length;j ++) {
				if (distance[i][j] != INF)
					System.out.println("Distance from " + i + " to " + j + " is " + distance[i][j]);
			}
		}

	}
	

}
