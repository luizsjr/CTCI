package com.ctci.problems.c4;

import com.ctci.util.BinaryTreeNode;

public class P10StringComparisson {
	
	// time: O(n+m) space O(n+m)
	public boolean isSubTree(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
		StringBuilder t1Str = new StringBuilder();
		readTreePreOrder(t1, t1Str);
		
		StringBuilder t2Str = new StringBuilder();
		readTreePreOrder(t2, t2Str);
		
		System.out.println(t1Str);
		System.out.println(t2Str);
		return t1Str.indexOf(t2Str.toString())>=0;
	}
	
	protected void readTreePreOrder(BinaryTreeNode<Integer> root, StringBuilder result) {
		if(root==null) {
			result.append("X ");
			return;
		}
		result.append(root.data).append(' ');
		readTreePreOrder(root.left,result);
		readTreePreOrder(root.right,result);
	}
	
	public static void main(String[] args) {
		/* false scenario */
		BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(7, 12, 13);
		BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(11, null, right);
		right = new BinaryTreeNode<Integer>(7, left,null);
		left = new BinaryTreeNode<Integer>(11);
		left = new BinaryTreeNode<Integer>(7, left, right);
		right = new BinaryTreeNode<Integer>(2,3,4);
		BinaryTreeNode<Integer> t1f = new BinaryTreeNode<Integer>(1,left,right);
		
		/* true scenario */
		left = new BinaryTreeNode<Integer>(11);
		right = new BinaryTreeNode<Integer>(7, 12, 13);
		right = new BinaryTreeNode<Integer>(7, left,right);
		left = new BinaryTreeNode<Integer>(11);
		left = new BinaryTreeNode<Integer>(7, left, right);
		right = new BinaryTreeNode<Integer>(2,3,4);
		BinaryTreeNode<Integer> t1t = new BinaryTreeNode<Integer>(1,left,right);
		
		
		left = new BinaryTreeNode<Integer>(11);
		right = new BinaryTreeNode<Integer>(7, 12, 13);
		BinaryTreeNode<Integer> t2 = new BinaryTreeNode<Integer>(7, left, right);
		
		P10StringComparisson problem = new P10StringComparisson();
		System.out.println(problem.isSubTree(t1f, t2));
		System.out.println("======================================");
		System.out.println(problem.isSubTree(t1t, t2));
	}
}
