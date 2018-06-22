package com.ctci.problems.c4;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P7GraphDFSolution {
	
	public static void main(String[] args) {
		P7GraphDFSolution problem = new P7GraphDFSolution();
		String[] projects = {"a","b","c","d","e","f","g","h"};
		String[][] dependencies = {{"f","c"},{"f","b"},{"f","a"},{"c","a"},{"b","a"},{"a","e"},{"b","e"},{"b","h"},{"d","g"}};
		try {
			Project[] buildOrder = problem.generateBuildOrder(projects,dependencies);
			for(Project p : buildOrder) {
				System.out.println(p);
			}
		} catch (Exception e) {	
			System.out.println(e.getMessage());
		}
	}
	
	public Project[] generateBuildOrder(String[] projects, String[][] dependencies) {
		// Parameters Validation
		if (projects==null || projects.length==0 || dependencies!=null && dependencies[0].length!=2 ) { return null; }
		
		//Build Graph
		Graph projGraph = generateProjectGraph(projects, dependencies);
		
		//Generate Order
		return generateBuildOrder(projGraph);
	}
	
	protected Graph generateProjectGraph(String[] projects, String[][] dependencies) {
		Graph projGraph = new Graph();
		// Add Projects
		for (String project : projects) {
			projGraph.getOrCreateNode(project);
		}
		
		//Add Edges
		if (dependencies!=null) {
			for(String[] dependency : dependencies) {
				if (dependency[0]!=null && dependency[1]!=null) {
					projGraph.addEdge(dependency[0], dependency[1]);
				}
			}
		}
		return projGraph;
	}
	
	protected Project[] generateBuildOrder(Graph projGraph) {
		Project[] buildOrder = null;
		if (projGraph!=null) {
			Collection<Project> projects = projGraph.getNodes();
			buildOrder = new Project[projects.size()];
			int buildOrderPointer = buildOrder.length-1; // Build Order is created backwards
			for (Project current : projects) {
				if (current.isNotVisited()) {
					buildOrderPointer = navigateDepthFirstFromNode(current, buildOrder, buildOrderPointer);
				}
			}
		}
		return buildOrder;
	}
	
	protected int navigateDepthFirstFromNode(Project project, Project[] buildOrder, int buildOrderPointer ) {
		
		if (project.isVisiting()) { throw new RuntimeException("Circular Reference Detected. Cannot generate Build Order"); }
		if (project.isVisited()) { return buildOrderPointer; } //Already visited, move on
		
		project.setVisiting();
		
		//DF each child
		for (Project child : project.getChildren()) {
			buildOrderPointer=navigateDepthFirstFromNode(child, buildOrder, buildOrderPointer);
		}
		
		//Add self to build list
		project.setVisited();
		buildOrder[buildOrderPointer]=project;
		return --buildOrderPointer;
		
	}
	
	protected class Graph {
		private Map<String, Project> nodes;
		
		protected Graph() {
			nodes = new HashMap<>();
		}
		
		protected Project getOrCreateNode(String projectName) {
			Project proj = nodes.get(projectName);
			if (proj==null) {
				proj = new Project(projectName);
				nodes.put(projectName, proj);
			}
			return proj;
		}
		
		protected void addEdge(String origin, String destiny) {
			Project parent = getOrCreateNode(origin);
			Project child = getOrCreateNode(destiny);
			parent.addChildren(child);
		}
		
		protected Collection<Project> getNodes() {
			return nodes.values();
		}
	}
	
	protected enum VisitStatus {
		NotVisited,
		Visiting,
		Visited
	}
	protected class Project {
		private String name;
		private Set<Project> children;
		private VisitStatus status;
		
		protected Project(String projectName) {
			name=projectName;
			status=VisitStatus.NotVisited;
			children=new HashSet<>();
		}
		
		public String getName() {
			return name;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		protected void addChildren(Project project) {
			children.add(project);
		}
		
		protected Set<Project> getChildren() {
			return children;
		}
		
		protected void setVisiting() {
			status = VisitStatus.Visiting;
		}
		
		protected void setVisited() {
			status = VisitStatus.Visited;
		}
		
		protected boolean isNotVisited() {
			return status==VisitStatus.NotVisited;
		}
		
		protected boolean isVisiting() {
			return status==VisitStatus.Visiting;
		}
		
		protected boolean isVisited() {
			return status==VisitStatus.Visited;
		}
		
		protected VisitStatus getStatus() {
			return status;
		}
	}
}
