package com.ctci.problems;

public class C1P6 {

	public static void main(String[] args) {
		C1P6 problem = new C1P6();
		
//		String str = "aaabbccccccdde";
		String str = "aabbcccddee";
		System.out.println(problem.compressString2(str));
	}
	
	public String compressString2(String str) {
		
		if (str.length() < 3) return str;

		StringBuilder compressed = new StringBuilder();
		int charCounter=0;
		for (int i=0; i<str.length(); i++) {
			charCounter++;
			if (i+1 == str.length() || str.charAt(i) != str.charAt(i+1)) {
				compressed.append(str.charAt(i)).append(charCounter);
				charCounter = 0;
			}
		}
		
		return compressed.length() < str.length() ? compressed.toString() : str;
	}
}
