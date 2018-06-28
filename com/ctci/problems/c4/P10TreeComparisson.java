package com.ctci.problems.c4;

import com.ctci.util.BinaryTreeNode;

public class P10TreeComparisson<T> {
	
	// the runtime is closer to O( n + km)
	public boolean isSubTree(BinaryTreeNode<T> t1, BinaryTreeNode<T> t2) {
		if (t1==null || t2==null) {
			return false;
		}
		if (t1.data.equals(t2.data) && matchTrees(t1, t2)) {
			return true;
		}
		
		return isSubTree(t1.left, t2) || isSubTree(t1.right, t2);
	}

	protected boolean matchTrees(BinaryTreeNode<T> t1, BinaryTreeNode<T> t2) {
		if (t1==null && t2==null) {
			return true;
		}
		if (t1==null || t2==null || !t1.data.equals(t2.data)) {
			return false;
		}
		return matchTrees(t1.left, t2.left) && matchTrees(t1.right, t2.right);
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
		
		P10TreeComparisson<Integer> problem = new P10TreeComparisson<Integer>();
		System.out.println(problem.isSubTree(t1f, t2));
		System.out.println(problem.isSubTree(t1t, t2));
	}
}
