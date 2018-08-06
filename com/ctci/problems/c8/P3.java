package com.ctci.problems.c8;

public class P3 {
	
	public int findMagicIndex(int[] a) {
		return findMagicIndex(a, 0, a.length-1);
	}
	
	private int findMagicIndex(int[] a, int start, int end) {
		if (start>end) { return -1; }
		int mid = (start+end)/2;
		if (mid == a[mid]) { return mid; }
		if (mid > a[mid]) {
			return findMagicIndex(a, start+1, end);
		}
		return findMagicIndex(a, start, mid-1);
	}

	/*
	 * Follow Up
	 */
	public int findMagicIndexNonDistinct(int[] a) {
		return findMagicIndexNonDistinct(a, 0, a.length-1);
	}
	
	private int findMagicIndexNonDistinct(int[] a, int start, int end) {
		if (start>end) { return -1; }
		int mid = (start+end)/2;
		if (mid == a[mid]) { return mid; }
		//Search Left
		int magicIndex = findMagicIndexNonDistinct(a, start, Math.min(mid-1, a[mid]));
		if (magicIndex != -1) { return magicIndex; }
		
		//Search Right
		return findMagicIndexNonDistinct(a, Math.max(mid+1, a[mid]), end);
	}

	public static void main(String[] args) {
		P3 problem = new P3();
		int[] a = {-1,0,1,2,4,7};
		System.out.println(problem.findMagicIndex(a)); //4
		
		int[] b = {-1,0,1,4,5,5};
		System.out.println(problem.findMagicIndexNonDistinct(b)); //4
		
	}

}
