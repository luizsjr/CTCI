package com.ctci.problems.c8;

public class P5 {
	
	public int multiply(int a, int b) {
		return a<b ? multiplyHelper(a, b) : multiplyHelper(b, a); 
	}
	
	// O (smaller)
	public int multiplyOptimized(int a, int b) {
		return a<b ? multiplyHelperOptimzed(a, b) : multiplyHelperOptimzed(b, a); 
	}
	
	private int multiplyHelper(int smaller, int bigger) {
		if (smaller==0) { return 0; }
		if (smaller==1) { return bigger; }
		return bigger+multiplyHelper(smaller-1, bigger);
	}
	
	// O (log smaller)
	private int multiplyHelperOptimzed(int smaller, int bigger) {
		if (smaller==0) { return 0; }
		if (smaller==1) { return bigger; }
		int half = multiplyHelperOptimzed(smaller>>1, bigger);
		return smaller%2==0? half+half : half+half+bigger;
	}

	public static void main(String[] args) {
		P5 problem = new P5();
		System.out.println(problem.multiply(3, 5)); // 15
		System.out.println(problem.multiplyOptimized(3, 5)); // 15
	}
}
