package com.ctci.problems.c4;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P7GraphDependencyNumberCheckSolution {
	
	public Project[] findBuildOrder(String[] projects, String[][] dependencies) {
		Graph projGraph = generateGraph(projects, dependencies);
		return generateBuildOrder(projGraph);
	}
	
	protected Project[] generateBuildOrder(Graph projGraph) {
		Collection<Project> projects = projGraph.getNodes();
		Project[] buildOrder = new Project[projects.size()];
		int buildPointer = addNonDependent(projects, buildOrder, 0);
		int beingProcessed=0;
		while (beingProcessed < buildOrder.length) {
			Project current = buildOrder[beingProcessed++];
			if(current==null) { return null; } // Circular reference, cannot build
			Set<Project> children = current.updateDependenciesAndReturn();
			buildPointer = addNonDependent(children, buildOrder, buildPointer);
		}
		return buildOrder;
	}
	
	protected int addNonDependent(Collection<Project> projects, Project[] buildOrder, int buildPointer) {
		for (Project proj : projects) {
			if(proj.getNumberOfDependencies()==0) {
				buildOrder[buildPointer++]=proj;
			}
		}
		return buildPointer;
	}
	
	protected Graph generateGraph(String[] projects, String[][] dependencies) {
		Graph projGraph = new Graph();
		for (String project : projects) {
			projGraph.getOrCreateNode(project);
		}
		for (String[] dependency : dependencies) {
			projGraph.addEdge(dependency[0], dependency[1]);
		}
		return projGraph;
	}
	
	protected class Graph {
		private Map<String,Project> nodes;
		
		protected Graph() {
			nodes = new HashMap<>();
		}
		
		protected Project getOrCreateNode(String projectName) {
			Project project = nodes.get(projectName);
			if(project==null) {
				project=new Project(projectName);
				nodes.put(projectName,project);
			}
			return project;
		}
		
		protected Collection<Project> getNodes() {
			return nodes.values();
		}
		
		protected int size() {
			return nodes.size();
		}
		
		protected void addEdge(String start, String end) {
			Project parent = getOrCreateNode(start);
			Project child  = getOrCreateNode(end);
			parent.addChildren(child);
		}
	}
	
	protected class Project {
		private String name;
		private int dependencies;
		private Set<Project> children;
		
		protected Project(String projectName) {
			name=projectName;
			dependencies=0;
			children=new HashSet<>();
		}
		
		@Override
		public String toString() {
			return name;
		}
		
		protected int getNumberOfDependencies() {
			return dependencies;
		}
		
		protected int incrementDependencies() {
			return ++dependencies;
		}
		
		protected int decrementDependencies() {
			return --dependencies;
		}
		
		protected void addChildren(Project project) {
			if (children.add(project)) {
				project.incrementDependencies();
			}
		}
		
		protected Set<Project> updateDependenciesAndReturn() {
			for(Project children : children) {
				children.decrementDependencies();
			}
			return children;
		}
	}
	
	public static void main(String[] args) {
		P7GraphDependencyNumberCheckSolution problem = new P7GraphDependencyNumberCheckSolution();
		String[] projects = {"a","b","c","d","e","f","g"};
		String[][] dependencies = {{"f","c"},{"f","b"},{"f","a"},{"c","a"},{"b","a"},{"a","e"},{"b","e"},{"d","g"}};
		Project[] buildOrder = problem.findBuildOrder(projects,dependencies);
		
		if(buildOrder==null) {
			System.out.println("Circular reference found, cannot build");
			return;
		}
		
		for(Project p : buildOrder) {
			System.out.println(p);
		}
	}
}
