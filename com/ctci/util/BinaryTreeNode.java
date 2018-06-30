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
	
	public BinaryTreeNode(T value, T leftValue, T rigthValue) {
		this(value);
		if(leftValue!=null) { left = new BinaryTreeNode<T> (leftValue); }
		if(rigthValue!=null) { right = new BinaryTreeNode<T> (rigthValue); }
	}
	
	public BinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
		this(value);
		if(left!=null) { this.left = left; }
		if(right!=null) { this.right = right; }
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
