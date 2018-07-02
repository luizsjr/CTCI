package com.ctci.problems.c4;

import java.util.concurrent.ThreadLocalRandom;

public class P11 {
	
	public class BinarySearchTreeNode {
		public int value;
		public BinarySearchTreeNode left;
		public BinarySearchTreeNode right;
		public int sizeLeft;
		public int sizeRight;
		
		public BinarySearchTreeNode(int v) {
			value=v;
			sizeLeft=0;
			sizeRight=0;
		}
		
		public boolean isLeaf() {
			return left==null && right==null;
		}
		
		@Override
		public String toString() {
			return Integer.toString(value);
		}
	}
	
	public class BinarySearchTree {
		public BinarySearchTreeNode root;
		public int size;
		
		public BinarySearchTree() {
			size=0;
		}
		
		public void insert(int value) {
			if (root==null) {
				root = insert(null, value);
			} else {
				insert(root, value);
			}
			size++;
		}
		
		protected BinarySearchTreeNode insert(BinarySearchTreeNode node, int value) {
			if (node==null) {
				return new BinarySearchTreeNode(value);
			}
			
			BinarySearchTreeNode newNode;
			if (value>node.value) {
				node.sizeRight++;
				newNode = insert(node.right, value);
				if(node.right==null) { node.right = newNode; }
			} else {
				node.sizeLeft++;
				newNode = insert(node.left, value);
				if(node.left==null) { node.left = newNode; }
			}
			return newNode;
		}
		
		public BinarySearchTreeNode find(int value) {
			if(root==null) {
				return null;
			}
			return find(root, value);
		}
		
		protected BinarySearchTreeNode find(BinarySearchTreeNode node, int value) {
			if (node==null) {
				return null;
			}
			if (value==node.value) {
				return node;
			}
			if (value>node.value) {
				return find(node.right, value);
			}
			return find(node.left, value);
		}
		public Integer getRandomNode() {
			if (root==null) { return null; }
			int randNode = ThreadLocalRandom.current().nextInt(size);
			return findRandomNode(root, randNode);
		}
		
		protected int findRandomNode(BinarySearchTreeNode node, int rand) {
			if (rand < node.sizeLeft) {
				return findRandomNode(node.left, rand);
			} else if (rand == node.sizeLeft) {
				return node.value;
			} else {
				return findRandomNode(node.right, rand-node.sizeLeft-1);
			}
		}
		
		public boolean delete(int value) {
			boolean result = delete(null, root, value);
			if (result) { size--; }
			return result;
		}
		
		protected boolean delete(BinarySearchTreeNode parent, BinarySearchTreeNode node, int value) {
			if (node==null) {
				return false;
			}
			if (value==node.value) {
				deleteNode(parent, node);
				return true;
			}
			if (value>node.value) {
				if(delete(node, node.right, value)) {
					node.sizeRight--;
					return true;
				}
			} else {
				if (delete(node, node.left, value)) {
					node.sizeLeft--;
					return true;
				}
			}
			return false;
		}
		
		protected void deleteNode(BinarySearchTreeNode parent, BinarySearchTreeNode node) {
			if (node.isLeaf()) {
				replaceNode(parent, node, null);
				return;
			}
			
			// Node has only one child
			if (node.left==null || node.right==null) {
				BinarySearchTreeNode replacement = node.left==null ? node.right : node.left; 
				replaceNode(parent, node, replacement);
				return;
			}
			
			// Node has two children
			replaceByInOrderSuccessor(node);
		}
		
		protected void replaceNode(BinarySearchTreeNode parent, BinarySearchTreeNode node, BinarySearchTreeNode replacement) {
			
			// Node being replaced is the root
			if (parent==null) {
				root = replacement;
				return;
			}
			if (parent.left==node) { 
				parent.left = replacement;
			} else {
				parent.right = replacement;
			}
		}
		
		protected void replaceByInOrderSuccessor(BinarySearchTreeNode node) {
			node.sizeRight--;
			replaceByInOrderSuccessor(node, node.right, node);
		}
		
		protected void replaceByInOrderSuccessor(BinarySearchTreeNode successorParent, BinarySearchTreeNode successor, BinarySearchTreeNode node) {
			// Successor is a leaf
			if (successor.isLeaf()) {
				replaceNode(successorParent, successor, null);
				node.value=successor.value;
				return;
			}
			// only searches right if left is null
			if (successor.left==null) {
				successor.sizeRight--;
				replaceByInOrderSuccessor(successor, successor.right, node);
			// otherwise search left
			} else {
				successor.sizeLeft--;
				replaceByInOrderSuccessor(successor, successor.left, node);
			}
		}
	}
	
	public static void main(String[] args) {
		P11 problem = new P11();
		BinarySearchTree tree = problem.new BinarySearchTree();
		tree.insert(10);
		tree.insert(5);
		tree.insert(20);
		tree.insert(1);
		tree.insert(15);
		tree.insert(30);
		tree.insert(12);
		tree.insert(19);
		tree.insert(29);
		tree.insert(13);
		tree.insert(17);
		
		tree.delete(10);
		tree.delete(29);
		tree.delete(15);
		tree.delete(5);
		
		for (int i=0; i<tree.size;i++) {
			System.out.println(tree.getRandomNode());
		}
	}

}
