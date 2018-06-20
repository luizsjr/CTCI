package com.ctci.util;

public class BinaryTreeNode<T> {
	
	public T data;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;
	public boolean visited;
	
	public BinaryTreeNode(T value) {
		data = value;
		visited = false;
	}
}
