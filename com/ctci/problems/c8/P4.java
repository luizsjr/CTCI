package com.ctci.problems.c8;

import java.util.ArrayList;
import java.util.List;

public class P4 {
	
	public List<List<Integer>> getSubSets(List<Integer> set) {
		return getSubSets(set, 0);
	}
	
	private List<List<Integer>> getSubSets(List<Integer> set, int index) {
		List<List<Integer>> subSets;
		// Base Case - Add empty subset
		if (set.size() == index) {
			subSets = new ArrayList<List<Integer>>();
			subSets.add(new ArrayList<>());
			return subSets;
		}
		// Get previous subsets
		subSets = getSubSets(set, index+1);
		
		//Clone each subset adding set(index) item to them
		int subSetsSize = subSets.size();
		for (int i=0; i<subSetsSize; i++) {
			List<Integer> newSubSet = new ArrayList<>();
			newSubSet.addAll(subSets.get(i));
			newSubSet.add(set.get(index));
			subSets.add(newSubSet);
		}
		return subSets;
	}
	
	public static void main(String[] args) {
		List<Integer> set = new ArrayList<>();
		set.add(1);
		set.add(2);
		set.add(3);
		
		P4 problem = new P4();
		List<List<Integer>> subSets = problem.getSubSets(set);
		System.out.println(subSets);
	}

}
