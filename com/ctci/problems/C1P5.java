package com.ctci.problems;

public class C1P5 {

	public static void main(String[] args) {
		String strA = "pale";
		String strB = "pales";
		
		C1P5 problem = new C1P5();
		System.out.println(problem.isOneAway(strA, strB));
		System.out.println(problem.isOneAway2(strA, strB));
	}
	
	// O(n) where n is the shorter size - shorter but more complex
	public boolean isOneAway(String strA, String strB) {
		int diffSize = strA.length() - strB.length();
		if (diffSize < -1 || diffSize > 1) return false;
		
		String bigger = diffSize < 0 ? strB : strA;
		String smaller = diffSize < 0 ? strA : strB;
		
		int diffCounter = 0;
		int offSet = 0;
		
		for (int i=0; i<smaller.length() && (i+offSet)<bigger.length(); i++) {
			if (smaller.charAt(i) != bigger.charAt(i+offSet)) {
				if (++diffCounter > 1) return false;
				if (diffSize != 0) offSet++;
			}
		}
		return true;
	}
	
	// O(n) - longer, easier to understand
	public boolean isOneAway2(String strA, String strB) {
		int diffSize = strA.length() - strB.length();
		
		if (diffSize==0) 
			return isOneEditAway(strA, strB);
		
		if (diffSize==1) 
			return isOneInsertAway(strA, strB);
		
		if (diffSize==-1) 
			return isOneInsertAway(strB, strA);
		
		return false;
		
	}
	
	public boolean isOneEditAway(String strA, String strB) {
		int diffCounter = 0;
		for (int i=0; i<strA.length(); i++) {
			if(strA.charAt(i) != strB.charAt(i) && ++diffCounter > 1) return false;
		}
		return true;
	}
	
	public boolean isOneInsertAway(String bigger, String smaller) {
		int offSet = 0;
		for (int i=0; i<smaller.length(); i++) {
			if (smaller.charAt(i) != bigger.charAt(i+offSet) && ++offSet > 1) return false; 
		}
		return true;
	}
}
