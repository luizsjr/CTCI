package com.ctci.problems.c5;

public class P3 {
	public static void main(String[] args) {
		P3 problem = new P3();
		System.out.println(problem.isBitEnable(5, 4));
	}
	
	protected boolean isBitEnable(int n, int bitNumber) {
		int mask = 1 << bitNumber-1;
		System.out.println(mask);
		return (n & mask) != 0;
	}

}
