package com.ctci.problems.c5;

public class P4 {
	public void getClosestWithSameBits(int n) {
		if (n==0) {
			System.out.println("0\n0");
		}
		int smaller=n; // in case of number with all bits set there is no smaller number
		int bigger=n;
		int mask = ~(-1 << 2);
		int copy=n;
		int bitCounter=0;
		while (copy>0) {
			int masked = copy & mask; 
			System.out.println("masked: "+masked);
			if (bigger==n && masked==1) { // found 01 - XOR with the mask to get the bigger
				int b = n^(mask<<bitCounter);
				//Check for overflow
				if(b>0) {
					bigger=b;
				}
			}
			if(smaller==n && masked==2) { // found 10 - XOR with the mask to get the smaller
				smaller = n^(mask<<bitCounter);
			}
			copy >>= 1;
			bitCounter++;
		}
		System.out.println(smaller);
		System.out.println(bigger);
	}
	
	public static void main(String[] args) {
		P4 problem = new P4();
//		problem.getClosestWithSameBits(536870912);
		problem.getClosestWithSameBits(1073741824);
	}
}
