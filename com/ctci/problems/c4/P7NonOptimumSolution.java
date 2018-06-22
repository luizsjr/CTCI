package com.ctci.problems.c4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P7NonOptimumSolution {

	public List<Character> buildOrder(char[] projects, char[][] dependencies) {
		
		if (projects==null||projects.length==0) return null;
		
		//Result & already built pointer
		List<Character> buildOrder = new ArrayList<>(projects.length);
		int buildPointer=0;
		
		//Control Map
		Map<Character, List<Character>> projMap = createProjMap(projects);
		
		//Build Dependencies List
		populateDepList(dependencies, projMap);
		
		int buildCounter;
		do {
			//Find Projects cleared to build
			buildCounter = updateBuildList(buildOrder, projMap);
			//Remove newly built projects from projMap
			while (buildPointer<buildOrder.size()) {
				projMap.remove(buildOrder.get(buildPointer++));
			}
		} while(!projMap.isEmpty() && buildCounter>0);
		return projMap.isEmpty() ? buildOrder : null;
	}

	protected int updateBuildList(List<Character> buildOrder, Map<Character, List<Character>> projMap) {
		int buildCounter=0;
		for(Map.Entry<Character, List<Character>> proj : projMap.entrySet()) {
			//Clear dependencies list
			List<Character> depList = clearDepList(proj.getValue(), buildOrder);
			if (depList.isEmpty()) {
				buildOrder.add(proj.getKey());
				buildCounter++;
			}
		}
		return buildCounter;
	}

	protected Map<Character, List<Character>> createProjMap(char[] projects) {
		if (projects==null || projects.length==0) {
			throw new RuntimeException("Invalid projects list");
		}
		Map<Character, List<Character>> projMap = new HashMap<>();
		for(char id : projects) {
			projMap.put(id, new ArrayList<>());
		}
		return projMap;
	}
	
	protected void populateDepList(char[][] dependencies, Map<Character, List<Character>> projMap) {
		if (dependencies==null || dependencies.length==0 || dependencies[0].length!=2) {
			throw new RuntimeException("Invalid dependency list");
		}
		for(char[] d : dependencies) {
			List<Character> depList = projMap.get(d[1]);
			if(depList==null) {
				throw new RuntimeException("Incompatible projects and dependencies list received");
			}
			depList.add(d[0]);
		}
	}
	
	protected List<Character> clearDepList(List<Character> depList, List<Character> buildOrder) {
		if(depList.isEmpty()) { return depList; }
		List<Character> newDepList = new ArrayList<>();
		for(Character dep : depList) {
			if(!buildOrder.contains(dep)) {
				newDepList.add(dep);
			}
		}
		return newDepList;
	}
	
	
	public static void main(String[] args) {
		P7NonOptimumSolution problem = new P7NonOptimumSolution();
		char[] projects = {'a','b','c','d','e','f','g'};
		char[][] dependencies = {{'f','c'},{'f','b'},{'f','a'},{'c','a'},{'b','a'},{'a','e'},{'b','e'},{'d','g'}};
		System.out.println( problem.buildOrder(projects,dependencies) );

	}

}
