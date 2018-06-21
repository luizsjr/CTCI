package com.ctci.problems.c4;

import com.ctci.util.BinaryTreeNode;

public class P4<T> {
	
	public boolean isBalanced(BinaryTreeNode<T> root) {
		return nodeHeight(root) >= 0;
	}
	
	public int nodeHeight(BinaryTreeNode<T> root) {
		if(root==null) {
			return 0;
		}
		int heightLeft = nodeHeight(root.left);
		int heightRight = nodeHeight(root.right);
		if(heightLeft<0 || heightRight<0 || Math.abs(heightLeft-heightRight)>1) {
			return -1;
		}
		return Math.max(heightLeft, heightRight)+1;
	}
	
	protected BinaryTreeNode<Integer> buildUnbalancedTree() {
		@SuppressWarnings("unchecked")
		BinaryTreeNode<Integer>[] nodes = new BinaryTreeNode[9];
		for (int i=0; i<nodes.length; i++) {
			nodes[i] = new BinaryTreeNode<Integer>(i); 
		}
		
		nodes[0].left = nodes[1];
		nodes[0].right = nodes[2];
		
		nodes[1].left = nodes[3];
		nodes[1].right = nodes[4];

		nodes[2].right = nodes[5];

		nodes[4].left = nodes[6];
		nodes[4].right = nodes[7];
		
		nodes[7].left = nodes[8];
		
		return nodes[0];
	}
	
	protected BinaryTreeNode<Integer> buildBalancedTree() {
		@SuppressWarnings("unchecked")
		BinaryTreeNode<Integer>[] nodes = new BinaryTreeNode[8];
		for (int i=0; i<nodes.length; i++) {
			nodes[i] = new BinaryTreeNode<Integer>(i); 
		}
		
		nodes[0].left = nodes[1];
		nodes[0].right = nodes[2];
		
		nodes[1].left = nodes[3];
		nodes[1].right = nodes[4];
		
		nodes[2].left = nodes[5];
		nodes[2].right = nodes[6];
		
		nodes[5].left = nodes[7];
		
		return nodes[0];
	}
	
	public static void main(String[] args) {
		P4<Integer> problem = new P4<Integer>();
		
		BinaryTreeNode<Integer> unbalancedTree = problem.buildUnbalancedTree();
		System.out.println(problem.isBalanced(unbalancedTree));
		
		BinaryTreeNode<Integer> balancedTree = problem.buildBalancedTree();
		System.out.println(problem.isBalanced(balancedTree));
	}

}
