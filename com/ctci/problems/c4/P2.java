package com.ctci.problems.c4;

import com.ctci.util.BinaryTreeNode;

public class P2 {
	
	public BinaryTreeNode<Integer> createMinHeigthTree(int[] values) {
		if (values==null || values.length==0) return null;
		return createMinHeigthTree(0, values.length-1, values);
	}
	
	protected BinaryTreeNode<Integer> createMinHeigthTree(int begin, int end, int[] values) {
		if (begin==end) {
			return new BinaryTreeNode<Integer>(values[begin]);
		}
		int middle = calcMiddleIndex(begin, end);
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(values[middle]);
		root.left = middle>begin ? createMinHeigthTree(begin, middle-1, values) : null;
		root.right = createMinHeigthTree(middle+1, end, values);
		return root;
		
	}
	
	protected int calcMiddleIndex(int begin, int end) {
		return (end+begin)/2;
	}

	public static void main(String[] args) {
		int[] values = {0,1,2,3,4,5,6,7,8,9,10,11};
		P2 problem = new P2();
		BinaryTreeNode<Integer> tree = problem.createMinHeigthTree(values);
		System.out.println(tree);
	}

}
