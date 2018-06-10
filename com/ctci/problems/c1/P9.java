package com.ctci.problems.c1;

public class P9 {

	public static void main(String[] args) {
		
		String s1 = "waterwaster";
		String s2 = "wasterwater";
		P9 problem = new P9();
		System.out.println(problem.isRotation(s1, s2));
	}
	
	// Assuming case sensitive
	// O(n)
	public boolean isRotation(String s1, String s2) {
		if (s1 != null && s2 != null && s1.length() == s2.length() && s1.length() > 0) {
			String s1s1 = s1 + s1;
			return s1s1.indexOf(s2) > 0; // equivalent to the isSubstring
		}
		return false;
	}
}
