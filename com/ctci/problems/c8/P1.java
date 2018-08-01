package com.ctci.problems.c8;

import java.util.Arrays;

public class P1 {
	
	//O(3^N)
	public int countWays(int n) {
		if (n<0) { return 0; }
		if (n==0) { return 1; }
		return countWays(n-1) + countWays(n-2) + countWays(n-3);
	}
	
	public int countWaysCache(int n) {
		int[] cache = new int[n+1];
		Arrays.fill(cache, -1);
		return countWaysCache(n,cache);
	}
	
	private int countWaysCache(int n, int[] cache) {
		if (n<0) { return 0; }
		if (n==0) { return 1; }
		if(cache[n]>-1) {
			return cache[n];
		}
		return countWaysCache(n-1, cache) + countWaysCache(n-2, cache) + countWaysCache(n-3, cache);
		
	}

	public static void main(String[] args) {
		P1 solution = new P1();
		System.out.println(solution.countWays(4)); //7
		System.out.println(solution.countWaysCache(4)); //7
	}

}
