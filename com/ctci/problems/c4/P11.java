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
		
		public boolean delete(int value) {
			return delete(null, root, value);
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
			// Node is a leaf
			if (node.left==null && node.right==null) {
				deleteLeaf(parent, node);
				return;
			}
			
			// Node has only one child
			if (node.left==null || node.right==null) {
				skipNode(parent, node);
				return;
			}
			
			// Node has two children
			replaceByInOrderSucessor(node);
		}
		
		protected void deleteLeaf(BinarySearchTreeNode parent, BinarySearchTreeNode node) {
			// The leaf is the root itself
			if(parent==null) {
				root=null;
				return;
			}
			
			// Check left or right
			if(parent.left==node) {
				parent.left=null;
				parent.sizeLeft--;
				return;
			}
			
			parent.right=null;
			parent.sizeRight--;
		}
		
		protected void skipNode(BinarySearchTreeNode parent, BinarySearchTreeNode node) {
			
			BinarySearchTreeNode newNode = node.left==null ? node.right : node.left; 
			
			// Node being skipped is the root
			if (parent==null) {
				root = newNode;
				return;
			}
			
			if (parent.left==node) { 
				parent.left = newNode;
				parent.sizeLeft--;
			} else {
				parent.right = newNode;
				parent.sizeRight--;
			}
		}
		
		protected void replaceByInOrderSucessor(BinarySearchTreeNode node) {
			replaceByInOrderSucessor(node, node.right, node);
			node.sizeRight--;
		}
		
		protected void replaceByInOrderSucessor(BinarySearchTreeNode replacementParent, BinarySearchTreeNode replacement, BinarySearchTreeNode node) {
			// replacement is a leaf - use it
			if (replacement.left==null && replacement.right==null) {
				if (replacementParent.left==replacement) { 
					replacementParent.left=null;	
					replacementParent.sizeLeft--;
				}
				if (replacementParent.right==replacement) {	
					replacementParent.right=null; 
					replacementParent.sizeRight--;
				}
				node.value=replacement.value;
				return;
			}
			// only goes right if left is null
			if (replacement.left==null) {
				replaceByInOrderSucessor(replacement, replacement.right, node);
				replacementParent.sizeRight--;
			// otherwise go left
			} else {
				replaceByInOrderSucessor(replacement, replacement.left, node);
				replacementParent.sizeLeft--;
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
	}

}
