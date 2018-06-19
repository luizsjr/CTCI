package com.ctci.util;

public class GraphNode<T> {
	
	public T data;
	public GraphNode<T>[] children;
	public boolean visited;
}
