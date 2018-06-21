package com.ctci.problems.c4;

import com.ctci.util.BinaryTreeNode;

public class P6<T> {
	
	public BinaryTreeNode<T> whosNextInOrder(BinaryTreeNode<T> node) {
		
		if (node==null) { return null; }
		
		// If there is a right node, find the smallest node
		if(node.right!=null) { return findSmallestNode(node.right); }
		
		//Go up to find the next right node (or the tree end)
		BinaryTreeNode<T> current = node;
		while(current.parent!=null && current.parent.left!=current) {
			current = current.parent;
		}
		return current.parent;
	}
	
	protected BinaryTreeNode<T> findSmallestNode(BinaryTreeNode<T> node) {
		if (node==null) return null;
		BinaryTreeNode<T> leftNode=findSmallestNode(node.left);
		return leftNode==null ? node : leftNode;
	}
	
	public static void main(String[] args) {
		P6<Integer> problem = new P6<Integer>();
		BinaryTreeNode<Integer>[] nodes = problem.buildBinarySearchTree(true);
		for(BinaryTreeNode<Integer> node : nodes) {
			System.out.println(node + " " + problem.whosNextInOrder(node));
		}
	}
	
	/*
	 *             7
	 *        5         12
	 *      5  6(5)   10
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

		nodes[3].data = 5;
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

}
