package com.ctci.problems.c4;

import com.ctci.util.BinaryTreeNode;

public class P5 {
	/*
	 * Method 1 : Compare each root value with a min and max value coming from previous recursions
	 * Time O(n) / Space O(log n) - might need to recurse the whole tree
	 */
	public boolean isBinarySearchTree1(BinaryTreeNode<Integer> root) {
		return root!=null && isBinarySearchTree1(root, null, null);
	}
	
	protected boolean isBinarySearchTree1(BinaryTreeNode<Integer> root, Integer min, Integer max) {
		if (root==null) { return true; }
		System.out.println(min + " < " + root.data + " <= " + max);
		if (min!=null && root.data <= min) { return false; }
		if (max!=null && root.data > max) { return false; }
		return isBinarySearchTree1(root.left, min, root.data) && isBinarySearchTree1(root.right, root.data, max);
		
	}
	
	/*
	 * Method 2 : Navigate in-order comparing each value with the previous. 
	 * When comparing left side of tree uses "<=" operation, when comparing right side uses "<"
	 * This guarantees that no values equal to previous root will be in the right side of the tree
	 * To track the previous value an integer wrapper is needed to be passed as reference parameter 
	 * Time O(n)
	 */
	protected class Wrapper {
		public Integer value;
	}
	
	public boolean isBinarySearchTree2(BinaryTreeNode<Integer> root) {
		if (root==null) { return false; }
		Wrapper last = new Wrapper();
		last.value=null;
		return isBinarySearchTree2(root, true, last);
	}
	
	protected boolean isBinarySearchTree2(BinaryTreeNode<Integer> root, boolean lessEqual, Wrapper last) {
		if (root==null) { return true; }
		if (!isBinarySearchTree2(root.left, true, last)) {
			return false;
		}
		if (last.value!=null) {
			if(lessEqual && root.data < last.value || !lessEqual && root.data <= last.value) return false;
		}
		last.value = root.data;
		return isBinarySearchTree2(root.right, false, last);
	}
	
	public static void main(String[] args) {
		P5 problem = new P5();
		BinaryTreeNode<Integer> root = problem.buildBinarySearchTree(false);
		System.out.println(problem.isBinarySearchTree1(root));
		System.out.println(problem.isBinarySearchTree2(root));
		
	}
	
	/*
	 *             7
	 *        5         12
	 *      5  6(5)   10
	 *    2          9  11
	 *  1  3
	 * 
	 */
	protected BinaryTreeNode<Integer> buildBinarySearchTree(boolean isBalanced) {
		@SuppressWarnings("unchecked")
		BinaryTreeNode<Integer>[] nodes = new BinaryTreeNode[11];
		for (int i=0; i<nodes.length; i++) {
			nodes[i] = new BinaryTreeNode<Integer>(0); 
		}
		
		nodes[0].data=7;
		nodes[0].left = nodes[1];
		nodes[0].right = nodes[2];
		
		nodes[1].data = 5;
		nodes[1].left = nodes[3];
		nodes[1].right = nodes[4];

		nodes[2].data = 12;
		nodes[2].left = nodes[5];

		nodes[3].data = 5;
		nodes[3].left = nodes[6];

		nodes[4].data = isBalanced ? 6 : 5;
		
		nodes[5].data = 10;
		nodes[5].left = nodes[7];
		nodes[5].right = nodes[8];
		
		nodes[6].data = 2;
		nodes[6].left = nodes[9];
		nodes[6].right = nodes[10];
		
		nodes[7].data = 9;
		nodes[8].data = 11;
		nodes[9].data = 1;
		nodes[10].data = 3;
		
		return nodes[0];
	}
}
