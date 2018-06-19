package com.ctci.problems.c4;

import java.util.LinkedList;
import java.util.Queue;

import com.ctci.util.GraphNode;

public class P1<T> {
	
	public boolean findPath(GraphNode<T> origin, GraphNode<T> destination) {
		Queue<GraphNode<T>> bfsQueue = new LinkedList<>();
		
		bfsQueue.add(origin);
		while (!bfsQueue.isEmpty()) {
			GraphNode<T> node = bfsQueue.poll();
			if (!node.visited) {
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
}
