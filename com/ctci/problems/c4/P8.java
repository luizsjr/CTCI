package com.ctci.problems.c4;

import com.ctci.util.BinaryTreeNode;

public class P8<T> {
	
	public BinaryTreeNode<T> findCommonAncestor(BinaryTreeNode<T> tree, BinaryTreeNode<T> nodeA, BinaryTreeNode<T> nodeB) {
		Result r = findCommonAncestorRecursive(tree, nodeA, nodeB);
		return r.node;
	}
	
	protected class Result {
		public boolean foundA=false;
		public boolean foundB=false;
		public BinaryTreeNode<T> node;
		
		public Result(boolean fA, boolean fB, BinaryTreeNode<T> n) {
			foundA=fA;
			foundB=fB;
			node=n;
		}
	}
	
	protected Result findCommonAncestorRecursive(BinaryTreeNode<T> tree, BinaryTreeNode<T> nodeA, BinaryTreeNode<T> nodeB) {
		
		// End of the tree, return what was found so far
		if (tree==null) { 
			return new Result(nodeA==null, nodeB==null, null);
		}
		
		// Check if tree is A or B
		if (nodeA==tree) { nodeA=null; }
		if (nodeB==tree) { nodeB=null; }
		
		// Found both nodes, stop searching
		if (nodeA==null && nodeB==null) {
			return new Result(true, true, null);
		}
		
		// Search on the left side
		Result resultLeft = findCommonAncestorRecursive(tree.left, nodeA, nodeB);
		
		// Check if left result is conclusive
		if (validateResult(resultLeft, tree)) {	return resultLeft; }
		
		// Search on the right side
		Result resultRight = findCommonAncestorRecursive(tree.right, nodeA, nodeB);
		
		// Merge Left and Right results
		resultRight.foundA = resultLeft.foundA || resultRight.foundA;
		resultRight.foundB = resultLeft.foundB || resultRight.foundB;
		
		// Check if right result is conclusive
		validateResult(resultRight, tree);
		
		return resultRight;
	}
	
	protected boolean validateResult(Result result, BinaryTreeNode<T> tree) {
		//Found the ancestor inside the tree, return it
		if (result.node!=null) { return true; }
		
		//tree is the common ancestor, set in the result object
		if (result.foundA && result.foundB && result.node==null) {
			result.node=tree;
			return true;
		}
		return false;
	}
	
	/*
	 *             7
	 *        5         12
	 *      4  6(5)   10
	 *    2          9  11
	 *  1  3
	 * 
	 */
	protected BinaryTreeNode<Integer>[] buildBinarySearchTree(boolean isBalanced) {
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
		nodes[1].parent = nodes[0];

		nodes[2].data = 12;
		nodes[2].left = nodes[5];
		nodes[2].parent = nodes[0];

		nodes[3].data = 4;
		nodes[3].left = nodes[6];
		nodes[3].parent = nodes[1];

		nodes[4].data = isBalanced ? 6 : 5;
		nodes[4].parent = nodes[1];
		
		nodes[5].data = 10;
		nodes[5].left = nodes[7];
		nodes[5].right = nodes[8];
		nodes[5].parent = nodes[2];
		
		nodes[6].data = 2;
		nodes[6].left = nodes[9];
		nodes[6].right = nodes[10];
		nodes[6].parent = nodes[3];
		
		nodes[7].data = 9;
		nodes[7].parent = nodes[5];
		
		nodes[8].data = 11;
		nodes[8].parent = nodes[5];
		
		nodes[9].data = 1;
		nodes[9].parent = nodes[6];
		
		nodes[10].data = 3;
		nodes[10].parent = nodes[6];
		
		return nodes;
	}
	
	public static void main(String[] args) {
		P8<Integer> problem = new P8<>();
		BinaryTreeNode<Integer>[] nodes = problem.buildBinarySearchTree(true);
		System.out.println( problem.findCommonAncestor(nodes[0], nodes[5], nodes[8]) );
	}
}