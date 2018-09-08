package com.ctci.problems.c10;

import java.util.Arrays;

public class P1 {
	// A has buffer to fit B
	public static void mergeArrays(Integer[] a, Integer[] b) {
		int indM = a.length-1;
		int indA = a.length-1;
		int indB = b.length-1;
		
		// Discover last item in a
		while (a[indA]==null) { indA--; }
		
		// Merge backwards
		while (indA >=0 && indB >=0) {
			if (a[indA] > b[indB]) {
				a[indM--] = a[indA--];
			} else {
				a[indM--] = b[indB--];
			}
		}
		
		//Copy rest of b in case A ends first
		while(indB>=0) {
			a[indM--] = b[indB--];	
		}
	}
	
	public static void main(String[] args) {
		Integer[] a = {0,3,4,9,10,null,null,null,null};
		Integer[] b = {1,2,9,20};
		mergeArrays(a, b);
		System.out.println(Arrays.toString(a));
	}
}
