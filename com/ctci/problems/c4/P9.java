package com.ctci.problems.c4;

import java.util.ArrayList;
import java.util.List;

import com.ctci.util.BinaryTreeNode;

public class P9 {
	
	public List<List<BinaryTreeNode<Integer>>> findArrays(BinaryTreeNode<Integer> root) {
		List<List<BinaryTreeNode<Integer>>> possibilities = new ArrayList<>();
		findArrays(possibilities, null, root);
		return possibilities;
	}
			
	protected void findArrays(
			List<List<BinaryTreeNode<Integer>>> possibilities, 
			BinaryTreeNode<Integer> parent, 
			BinaryTreeNode<Integer> root) {
		
		if (root==null) { return; }
		blendUntilNode(possibilities, parent, root);
		findArrays(possibilities, root, root.left);
		findArrays(possibilities, root, root.right);
	}
	
	protected void blendUntilNode( 
			List<List<BinaryTreeNode<Integer>>> combinations, 
			BinaryTreeNode<Integer> start, 
			BinaryTreeNode<Integer> newNode ) {
		
		// Combinations list is empty, create combination to include the newNode
		if(combinations.isEmpty()) {
			List<BinaryTreeNode<Integer>> newCombination = new ArrayList<>();
			combinations.add(newCombination);
		}
		
		// The original combinations size need to be used otherwise we'll end up in an infinite loop 
		int combineLimitIndex=combinations.size();
		int currentIndex=0;
		// For each existing combination, generate new combinations blending newNode in each combination
		while (currentIndex < combineLimitIndex) {
			List<BinaryTreeNode<Integer>> nodeList = combinations.get(currentIndex++);
			// get the start node index (we cannot blend newNode before the parent node being included)
			int blendIndex = nodeList.lastIndexOf(start);
			// blend the newNode in the current combination
			// we need to stop at end node - 1 as we are going to include in newNode in the end of the current combination
			while (blendIndex < nodeList.size()-1) {
				List<BinaryTreeNode<Integer>> newCombination = new ArrayList<>();
				newCombination.addAll(nodeList);
				newCombination.add(++blendIndex, newNode);
				combinations.add(newCombination);
			}
			// Add the newNode in the current combination. (That's why we stopped in size-1 while generating new combinations )
			nodeList.add(newNode);
		}
	}
	
	public static void main(String[] args) {
		P9 problem = new P9();
		
		BinaryTreeNode<Integer> root = new BinaryTreeNode<>(10);
		root.left = new BinaryTreeNode<>(5);
		root.right = new BinaryTreeNode<>(20);
		
		root.left.left= new BinaryTreeNode<>(3);
		root.left.right= new BinaryTreeNode<>(6);
		
		System.out.println(problem.findArrays(root));
	}

}
