package com.ctci.problems;

public class C1P4 {
	
	public static void main(String[] args) {
		String str = "ababcaa";
		
		C1P4 problem = new C1P4();
		System.out.println(problem.isPalindromePermutation(str));
		System.out.println(problem.isPalindromePermutation2(str));
	}
	
	// O(n) - Assuming non case sensitive and considering ASCII char set
	public boolean isPalindromePermutation(String str) {
		int[] charset = new int[128]; // Assuming charset is ASCII
		for (int i=0; i< str.length(); i++) {
			charset[str.charAt(i)]++;
		}
		
		int oddsCounter=0;
		for (int i=0; i < charset.length; i++) {
			if (charset[i] % 2 > 0 && ++oddsCounter > 1) return false;
		}
		return true;
	}

	// O(n) - same as previous solution but wihout looping the charset table. Not necessarily a faster solution 
	// as it runs additional tests on each interation
	public boolean isPalindromePermutation2(String str) {
		int[] charset = new int[128]; // Assuming charset is ASCII
		int oddCounter = 0;
		for (int i=0; i<str.length(); i++) {
			int c = str.charAt(i);
			charset[c]++;
			if (charset[c] % 2 == 0) {
				oddCounter--;
			} else {
				oddCounter++;
			};
		}
		return oddCounter <= 1;
	}
}
