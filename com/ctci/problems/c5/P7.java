package com.ctci.problems.c5;

public class P7 {
	
	public int swapOddAndEvenBits(int n) {
		int oddsMask = 0b01010101010101010101010101010101; // same as 0xAAAAAAAA;
		int evenMask = 0b10101010101010101010101010101010; // same as 0x55555555;
		
		return ( (n & oddsMask) << 1 ) | ( (n & evenMask) >>> 1);
	}

	public static void main(String[] args) {
		P7 problem = new P7();
		System.out.println(problem.swapOddAndEvenBits(10)); // 5
		System.out.println(problem.swapOddAndEvenBits(108)); // 156
		System.out.println(problem.swapOddAndEvenBits(0)); // 0
		System.out.println(problem.swapOddAndEvenBits(1)); // 2
		System.out.println(problem.swapOddAndEvenBits(-1)); // -1
		System.out.println(problem.swapOddAndEvenBits(-2147483648)); // 1073741824
	}

}
