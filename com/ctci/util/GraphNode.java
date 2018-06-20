package com.ctci.util;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
	
	public T data;
	public List<GraphNode<T>> children;
	public boolean visited;
	
	public GraphNode(T value) {
		data = value;
		children = new ArrayList<>();
		visited = false;
	}
}
