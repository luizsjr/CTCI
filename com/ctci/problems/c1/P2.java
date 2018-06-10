package com.ctci.problems.c1;

import java.util.Arrays;
import java.util.HashMap;

public class P2 {

	public static void main(String[] args) {
		P2 problem = new P2();
		
		String strA = "abcc";
		String strB = "cbca";
		
		System.out.println( problem.arePermutations(strA, strB) );
		System.out.println( problem.arePermutations2(strA, strB) );
		System.out.println( problem.arePermutations3(strA, strB) );

	}
	
	//O(n log n) - not as optimal, but cleaner (preferable)
	public boolean arePermutations(String strA, String strB) {
		
		if(strA.length() != strB.length()) {
			return false;
		}
		
		char[] charsA = strA.toCharArray();
		char[] charsB = strB.toCharArray();
		
		Arrays.sort(charsA);
		Arrays.sort(charsB);
		
		for(int i=0; i<charsA.length; i++) {
			if (charsA[i] != charsB[i]) return false;
		}
		return true;
	}
	
	// O(n) - optimal but not cleaner - HashMap implementation
	public boolean arePermutations2(String strA, String strB) {
		
		if(strA.length() != strB.length()) {
			return false;
		}
		
		HashMap<Character, Integer> control = new HashMap<>();
		
		for (int i=0; i < strA.length(); i++) {
			char c = strA.charAt(i);
			//c = Character.toLowerCase(c); // if not case sensitive
			Integer counter = control.get(c);
			if(counter == null) counter = 0;
			control.put(c, counter+1);
		}
		
		for (int i=0; i < strB.length(); i++) {
			char c = strB.charAt(i);
			//c = Character.toLowerCase(c); // if not case sensitive
			Integer counter = control.get(c);
			if(counter == null || counter == 0) {
				return false;
			}
			control.put(c, counter-1);
		}
		
		//Not needed as lengths are equal
//		for(Integer counter : control.values()) {
//			if(counter > 0) {
//				return false;
//			}
//		}
		
		return true;
	}
	
	// O(n) - optimal but not cleaner - array implementation (better than 2, but 1 is still preferable)
	public boolean arePermutations3(String strA, String strB) {
		
		if(strA.length() != strB.length()) {
			return false;
		}
		
		int[] letters = new int[128]; // Assuming the char set is ASCII
		
		for (int i=0; i < strA.length(); i++) {
			char c = strA.charAt(i);
			letters[c]++;
		}
		
		for (int i=0; i < strB.length(); i++) {
			char c = strB.charAt(i);
			letters[c]--;
			if (letters[c]<0) return false;
		}
		return true;
	}
}
