package com.ctci.problems.c4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctci.util.BinaryTreeNode;

public class P3 {
	
	public Map<Integer, List<BinaryTreeNode<Integer>>> generateNodesByDepthDF(BinaryTreeNode<Integer> root) {
		if (root==null) { return null; }
		Map<Integer, List<BinaryTreeNode<Integer>>> result = new HashMap<>();
		populateMapDF(result, root, 0);
		return result;
	}
	
	// 
	protected void populateMapDF(Map<Integer, List<BinaryTreeNode<Integer>>> map, BinaryTreeNode<Integer> root, int level){
		if (map==null || root==null) return;
		
		List<BinaryTreeNode<Integer>> depthList = map.get(level);
		if(depthList==null) {
			depthList = new ArrayList<>();
			map.put(level, depthList);
		}
		
		depthList.add(root);
		populateMapDF(map, root.left, level+1);
		populateMapDF(map, root.right, level+1);
	}
	
	public Map<Integer, List<BinaryTreeNode<Integer>>> generateNodesByDepthBF(BinaryTreeNode<Integer> root) {
		if (root==null) return null;
		
		// Root
		Map<Integer, List<BinaryTreeNode<Integer>>> result = new HashMap<>();
		List<BinaryTreeNode<Integer>> current = new ArrayList<>();
		current.add(root);
		
		// Children
		int level = 0;
		while(current.size() > 0) {
			result.put(level++, current);
			List<BinaryTreeNode<Integer>> parents = current;
			current = new ArrayList<>();
			for(BinaryTreeNode<Integer> parent : parents) {
				if (parent.left!=null) { current.add(parent.left); }
				if (parent.right!=null) { current.add(parent.right); }
			}
		}
		return result;
	}
	
	public static void main(String[] args) {

		P3 problem = new P3();
		BinaryTreeNode<Integer> root = problem.buildTree();
		
		System.out.println("************************ Depth-First ************************");
		Map<Integer, List<BinaryTreeNode<Integer>>> map = problem.generateNodesByDepthDF(root);
		
		for(int i=0; i<map.size(); i++) {
			System.out.println(i + " - " + map.get(i));
		}

		System.out.println("*********************** Breadth-First ***********************");
		map = problem.generateNodesByDepthBF(root);
		
		for(int i=0; i<map.size(); i++) {
			System.out.println(i + " - " + map.get(i));
		}
		
	}
	
	protected BinaryTreeNode<Integer> buildTree() {
		@SuppressWarnings("unchecked")
		BinaryTreeNode<Integer>[] nodes = new BinaryTreeNode[8];
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
		
		return nodes[0];
	}
}
