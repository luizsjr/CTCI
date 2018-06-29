package com.ctci.problems.c4;

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
		
		@Override
		public String toString() {
			return Integer.toString(value);
		}
	}
	
	public class BinarySearchTree {
		public BinarySearchTreeNode root;
		public int size;
		
		public BinarySearchTree(int rootValue) {
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
			} else {
				node.sizeLeft++;
				newNode = insert(node.left, value);
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
		
		protected boolean delete(BinarySearchTreeNode parent, BinarySearchTreeNode node, int value) {
			if (node==null) {
				return false;
			}
			if (value==node.value) {
				// delete node
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
			// Delete when node is a leaf or has only right side under it
			if(node.left==null) {
				parent.right=node.right;
				return;
			}
			// Delete when node has left side under it
			BinarySearchTreeNode swapNode = findSwap(node.left);
			parent.left.value=swapNode.value;
			
		}
	}

}
