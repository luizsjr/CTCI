package com.ctci.problems.c5;

public class P3 {
	public static void main(String[] args) {
		P3 problem = new P3();
		System.out.println(problem.flipBitToWin(-142606282)); // ‭11110111100001010100011111000110‬ -> 9
		System.out.println(problem.flipBitToWin(-8388554)); //‭11111111100000000000000000110110‬ -> 10
		System.out.println(problem.flipBitToWin(-69205962)); //‭11111011111000000000000000110110 -> 11‬
		System.out.println(problem.flipBitToWin(1772)); //‭ ‭011011101100‬ -> 6‬
		System.out.println(problem.flipBitToWin(-1)); //‭ ‭11111111111111111111111111111111‬ -> 32‬
		System.out.println(problem.flipBitToWin(-1073741824)); //‭ ‭11000000000000000000000000000000‬ -> 3‬
	}
	
	public int flipBitToWin(int n) {
		if (n==-1) return Integer.BYTES * 8;
		int current=0;
		int previous=0;
		int max=0;
		while(n!=0) {
			// first bit of n is enabled, increment the current counter
			if ((n&1)==1) {  
				current++;
			// otherwise sum with previous sequence + 1 (the bit being flipped) and compare with the max
			} else {
				max=Math.max(previous+1+current, max);
				previous=current;
				current=0;
			}
			n>>>=1;
		}
		return Math.max(previous+1+current, max);
	}
}
