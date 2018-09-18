package com.ctci.problems.c10;

import com.other.BitSet;

public class P8 {
	
	public static void printDuplicates(int[] nums) {
		BitSet bs = new BitSet(32000);
		for(int n : nums) {
			if (bs.isBitSet(n)) {
				System.out.print(n+ " ");
			} else {
				bs.setBit(n);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {1,15,20,56,15,32000,54,20};
		printDuplicates(nums); // 15 20
	}
}
