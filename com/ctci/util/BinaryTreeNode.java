package com.ctci.util;

public class BinaryTreeNode<T> {
	
	public T data;
	public BinaryTreeNode<T> left;
	public BinaryTreeNode<T> right;
	public BinaryTreeNode<T> parent;
	public boolean visited;
	
	public BinaryTreeNode(T value) {
		data = value;
		visited = false;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
