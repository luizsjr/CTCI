package com.ctci.problems.c4;

import java.util.ArrayList;
import java.util.List;

import com.ctci.util.BinaryTreeNode;

public class P9 {
	
	public List<List<BinaryTreeNode<Integer>>> findArrays(BinaryTreeNode<Integer> root) {
		List<List<BinaryTreeNode<Integer>>> possibilities = new ArrayList<>();
		return findArrays(possibilities, null, root);
	}
			
	protected List<List<BinaryTreeNode<Integer>>> findArrays(
			List<List<BinaryTreeNode<Integer>>> possibilities, 
			BinaryTreeNode<Integer> parent, 
			BinaryTreeNode<Integer> root) {
		
		if (root==null) { return possibilities; }
		possibilities=blendUntilNode(possibilities, parent, root);
		possibilities = findArrays(possibilities, root, root.left);
		possibilities = findArrays(possibilities, root, root.right);
		return possibilities;
	}
	
	protected List<List<BinaryTreeNode<Integer>>> blendUntilNode( 
			List<List<BinaryTreeNode<Integer>>> listOfLists, 
			BinaryTreeNode<Integer> limit, 
			BinaryTreeNode<Integer> newNode ) {
		
		List<List<BinaryTreeNode<Integer>>> result = new ArrayList<>();
		for (List<BinaryTreeNode<Integer>> nodeList : listOfLists) {
			
			if(nodeList.size()<1) {
				// 0 or 1 elements, add the new element in the end
				List<BinaryTreeNode<Integer>> newCombination = new ArrayList<>();
				newCombination.addAll(nodeList);
				newCombination.add(newNode);
				result.add(newCombination);
			}
			
			// Check if nodeUntil belongs to the list
			int blendIndex = nodeList.lastIndexOf(limit);
			if (blendIndex < 0) {
				throw new RuntimeException("untilNode parameter not in the list: "+limit);
			}
			
			// blend
			while (blendIndex < nodeList.size()) {
				List<BinaryTreeNode<Integer>> newCombination = new ArrayList<>();
				newCombination.addAll(nodeList);
				newCombination.add(++blendIndex, newNode);
				result.add(newCombination);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		P9 problem = new P9();
		
		List<List<BinaryTreeNode<Integer>>> listOfLists = new ArrayList<>();
		List<BinaryTreeNode<Integer>> nodesList = new ArrayList<>();
		listOfLists.add(nodesList);
		
		BinaryTreeNode<Integer> node = new BinaryTreeNode<>(10);
		nodesList.add(node);
		
		BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(5);
		nodesList.add(node2);
		
		BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(20);
		nodesList.add(node3);
		 
		nodesList = new ArrayList<>();
		nodesList.add(node);
		nodesList.add(node3);
		nodesList.add(node2);
		listOfLists.add(nodesList);
		
		 System.out.println(listOfLists);
		
		BinaryTreeNode<Integer> newNode = new BinaryTreeNode<>(3);
		List<List<BinaryTreeNode<Integer>>> result = problem.blendUntilNode(listOfLists, node, newNode);
		
		System.out.println(result);
	}

}
