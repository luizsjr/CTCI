package com.ctci.problems.c8;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P2 {
	
	public class Position {
		public int row;
		public int column;
		
		public Position(int r, int c) {
			row=r;
			column=c;
		}
	}
	
	public void printPath(List<Position> path) {
		if (path == null || path.isEmpty()) {
			System.out.println("No Path Found");
			return;
		}
		for(Position p : path) {
			System.out.println(MessageFormat.format("({0},{1})", p.row, p.column));
		}
	}
	public List<Position> findPath(boolean[][] map) {
		if (map==null || map.length==0 || map[0].length==0) { return null; }
		ArrayList<Position> path = new ArrayList<>();
		findPath(map, map.length-1, map[0].length-1, path, new HashSet<Integer>());
		return path;
	}
	
	private boolean findPath(boolean[][]map, int r, int c, List<Position> path, Set<Integer> failedPoints) {
		if (r==0 && c==0) { return true; }
		
		Position p = new Position(r,c);
		int coordinate = r*10+c;
		if (r<0 || c<0 || !map[r][c] || failedPoints.contains(coordinate)) { return false; }
		if(findPath(map, r-1, c, path, failedPoints) || findPath(map, r, c-1, path, failedPoints)) {
			path.add(p);
			return true;
		}
		failedPoints.add(coordinate);
		return false;
	}
	
	public static void main(String[] args) {
		boolean[][] map = new boolean[5][7];
		for(boolean[] row : map) {
			Arrays.fill(row, true);
		}
		
		// true
		map[0][4]=false;
		map[1][1]=false;
		map[3][0]=false;
		map[4][2]=false;
		
		// false
//		map[2][6]=false;
//		map[3][5]=false;
//		map[4][4]=false;
		
		P2 problem = new P2();
		List<Position> path = problem.findPath(map);
		problem.printPath(path);
	}
}
