package com.ctci.problems.c4;

import java.util.ArrayList;
import java.util.List;

import com.ctci.util.BinaryTreeNode;

public class P12 {
	
	public int countPaths(int targetSum, BinaryTreeNode<Integer> root) {
		if (root==null) { return 0; }
		return countPaths(targetSum, root, new ArrayList<Integer>());
	}
	
	private int countPaths(int targetSum, BinaryTreeNode<Integer> node, List<Integer> currentSums) {
		int totalPaths=0;
		for(int i=0; i<currentSums.size(); i++) {
			int currentSum=currentSums.get(i)+node.data;
			if (currentSum==targetSum) { totalPaths++; }
			currentSums.set(i, currentSum);
		}
		if (node.data==targetSum) { totalPaths++; }
		currentSums.add(node.data);
		
		if(node.left!=null) {
			totalPaths += countPaths(targetSum, node.left, currentSums);
		}
		
		if(node.right!=null) {
			totalPaths += countPaths(targetSum, node.right, currentSums);
		}
		
		rollbackList(currentSums, node.data);
		
		return totalPaths;
	}
	
	private void rollbackList(List<Integer> currentSums, int value) {
		currentSums.remove(currentSums.size()-1);
		for(int i=0; i<currentSums.size(); i++) {
			int currentSum=currentSums.get(i)-value;
			currentSums.set(i, currentSum);
		}
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> right = new BinaryTreeNode<Integer>(6);
		BinaryTreeNode<Integer> left = new BinaryTreeNode<Integer>(3, null, right);
		left = new BinaryTreeNode<Integer>(2, left, null);
		right = new BinaryTreeNode<Integer>(4, 1, 2);
		right.right.right = new BinaryTreeNode<Integer>(-1);
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1,left,right);
		
		P12 problem = new P12();
		System.out.println(problem.countPaths(6, root));

	}

}
