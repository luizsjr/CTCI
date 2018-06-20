package com.ctci.problems.c4;

import java.util.LinkedList;
import java.util.Queue;

import com.ctci.util.GraphNode;

public class P1<T> {
	
	// Breadth-First:
	// - Will find shortest paths
	// - needs an extra queue
	public boolean findPathBF(GraphNode<T> origin, GraphNode<T> destination) {
		if (origin==null || destination==null) { return false; }
		Queue<GraphNode<T>> bfsQueue = new LinkedList<>();
		bfsQueue.add(origin);
		while (!bfsQueue.isEmpty()) {
			GraphNode<T> node = bfsQueue.poll();
			if (!node.visited) {
				node.visited=true;
				if ( destination==node) { return true; }
				for (GraphNode<T> child : node.children) {
					if (!child.visited) {
						bfsQueue.add(child);
					}
				}
			}
		}
		return false;
	}
	
	// Depth-First:
	// - easier to implement (simple recursion)
	// - Goes deep into a node before searching adjacents. 
	// - might find bigger paths
	public boolean findPathDF(GraphNode<T> origin, GraphNode<T> destination) {
		if (origin==null) { return false; }
		if (origin==destination) { return true; }
		origin.visited=true;
		for (GraphNode<T> child : origin.children) {
			if (!child.visited) {
				if (findPathDF(child, destination)) { return true; }
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unchecked")
		GraphNode<Integer>[] nodes = new GraphNode[8];
		for (int i=0; i<nodes.length; i++) {
			nodes[i] = new GraphNode<Integer>(i+1); 
		}
		
		nodes[0].children.add(nodes[1]);
		nodes[0].children.add(nodes[2]);
		nodes[0].children.add(nodes[3]);
		
		nodes[1].children.add(nodes[4]);
		nodes[1].children.add(nodes[5]);
		nodes[1].children.add(nodes[6]);
		
		nodes[3].children.add(nodes[2]);
		nodes[3].children.add(nodes[0]);
		
		nodes[4].children.add(nodes[7]);
		
		P1<Integer> problem = new P1<>();
		System.out.println(problem.findPathDF(nodes[0], nodes[6]));
		
		for (int i=0; i<nodes.length; i++) {
			nodes[i].visited=false; 
		}
		System.out.println(problem.findPathBF(nodes[0], nodes[6]));
	}
}
