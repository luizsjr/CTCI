package com.ctci.problems.c5;

public class P1 {
	
	public int insertMintoN(int m, int n, int i, int j) {
		int mask = (int) Math.pow(2, j-i+1)-1;
		mask = ~(mask << i);
		n = n & mask;
		m = m << i;
		return m | n;
	}
	
	public int insertMintoN2(int m, int n, int i, int j) {
		int maskShift=i;
		int clearMask=0;
		while(maskShift<=j) {
			clearMask = clearMask | 1 << maskShift;
			maskShift++;
		}
		clearMask=~clearMask;
		n = n & clearMask;
		m = m << i;
		return m | n;
	}
	
	public int insertMintoN3(int m, int n, int i, int j) {
		int clearMask=-1; // all ones
		clearMask=clearMask << j+1; // left part of the mask: all 0s until j (inclusive) then all 1s. Ex: j=4: 1..11100000
		clearMask=clearMask|((1 << i) - 1); //left and right part of the mask merged - right part is: all 1s until i (inclusive) than all 0s. Ex: i=2 0..0000011
		n = n & clearMask; // clear N with the mask
		m = m << i; // shift M i positions
		return m | n; // merge N and M
	}
	
	public static void main(String[] args) {
		P1 problem = new P1();
		System.out.println(problem.insertMintoN(5, 42, 2, 4));
		System.out.println("================");
		System.out.println(problem.insertMintoN2(5, 42, 2, 4));
		System.out.println("================");
		System.out.println(problem.insertMintoN3(5, 42, 2, 4));
	}

}
