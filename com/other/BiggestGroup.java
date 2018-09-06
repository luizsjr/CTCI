package com.other;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Similar to Flood Fill, but will count the biggest group of same value cells in an 2D array
 */
public class BiggestGroup {
    public static int biggestSameValueGroup(int[][] m) {
    	int biggest=1;
    	HashSet<String> visitedMap = new HashSet<>();
    	for (int r=0; r<m.length; r++){
    		for (int c=0; c<m[0].length; c++){
    			String hash = hash(r, c, m[r][c]);
    			if (!visitedMap.contains(hash)) {
//    				int size = groupSize(m, r, c, m[r][c], visitedMap);
    				int size = groupSizeIterative(m, r, c, visitedMap);
    				if (size>biggest) { biggest=size;}
    			} 	
    		}
    	}
    	return biggest;
    }
    
	protected static int groupSize(int[][] m, int r, int c, int value, HashSet<String> visitedMap) {
		String hash = hash(r, c, value);
        if (!isValid(m, r, c) || visitedMap.contains(hash) || m[r][c]!=value) {
        	return 0;
        }
        int size=1;
        visitedMap.add(hash); // mark cell as visited to avoid infinite back and forth
        size+=groupSize(m, r-1, c, value, visitedMap); // up
        size+=groupSize(m, r+1, c, value, visitedMap); // down
        size+=groupSize(m, r, c-1, value, visitedMap); // left
        size+=groupSize(m, r, c+1, value, visitedMap); // right
        return size;
	}
		
	protected static int groupSizeIterative(int[][] m, int r, int c, HashSet<String> visitedMap) {
		
		Queue<RowColumn> executionQueue = new LinkedList<>(); 
		executionQueue.add(new RowColumn(r, c));
		
		int size = 0;
		while(!executionQueue.isEmpty()) {
			RowColumn data = executionQueue.poll();
			if (isValid(m, data.r, data.c)) {
				String hash = hash(data.r, data.c, m[data.r][data.c]);
				if (!visitedMap.contains(hash) && m[r][c]==m[data.r][data.c]) {
					size++;
					visitedMap.add(hash);
					executionQueue.add(new RowColumn(data.r-1, data.c));
					executionQueue.add(new RowColumn(data.r+1, data.c));
					executionQueue.add(new RowColumn(data.r, data.c-1));
					executionQueue.add(new RowColumn(data.r, data.c+1));
				}
			}
		}
		return size;
	}
	
	static class RowColumn {
		public int r;
		public int c;
		
		public RowColumn(int row, int column) {
			r = row;
			c = column;
		}
	}
	
	protected static String hash(int r, int c, int value) {
		return r+"|"+c+"|"+value;
	}
	
	protected static boolean isValid(int[][] m, int r, int c) {
		return r>=0 && r<m.length && c>=0 && c<m[0].length;
	}
	
	public static void main(String[] args) {
		int[][] m = {
				{1,1,1,1},
				{9,1,9,9},
				{9,9,9,3},
				{4,4,4,4}
			};
		
		System.out.println(biggestSameValueGroup(m)); // 6
		
		int[][] m2 = {
				{1,1,9,1},
				{9,1,9,9},
				{9,9,9,3},
				{9,4,4,4}
		};
		
		System.out.println(biggestSameValueGroup(m2)); // 8
	}
}