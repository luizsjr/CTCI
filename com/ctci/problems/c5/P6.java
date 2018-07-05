package com.ctci.problems.c5;

public class P6 {
	
	public int bitsDiff(int a, int b) {
		int axorb = a^b;
		int result=0;
		while (axorb !=0) {
			if ((axorb&1)==1) { result++; }
			axorb>>>=1;
		}
		return result;
	}
	
	public int bitsDiffOptimized(int a, int b) {
		int counter=0;
		for(int i=a^b;i!=0;i=i&i-1) { // i & i-1 always clear the last bit set to 1
			counter++;
		}
		return counter;
	}

	public static void main(String[] args) {
		P6 problem = new P6();
		System.out.println(problem.bitsDiff(29, 15)); //2
		System.out.println(problem.bitsDiff(-1, 0)); //32
		System.out.println(problem.bitsDiff(-1, 1)); //31
		System.out.println(problem.bitsDiff(-2, 7)); //30
		System.out.println("============================");
		System.out.println(problem.bitsDiffOptimized(29, 15)); //2
		System.out.println(problem.bitsDiffOptimized(-1, 0)); //32
		System.out.println(problem.bitsDiffOptimized(-1, 1)); //31
		System.out.println(problem.bitsDiffOptimized(-2, 7)); //30
	}

}
