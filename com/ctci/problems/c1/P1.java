package com.ctci.problems.c1;

import java.util.Arrays;
import java.util.HashSet;

public class P1 {
	
	public static void main(String[] args) {
		String str = "abc .d";
		
		System.out.println(P1.hasRepeatedChars(str));
		System.out.println(P1.hasRepeatedChars2(str));
		System.out.println(P1.hasRepeatedChars3(str.toCharArray()));
	}
	
	// O(n) Using a Set
	public static boolean hasRepeatedChars(String str) {
		HashSet<Character> control = new HashSet<>();
		for(int i = 0; i < str.length(); i++) {
			if (control.contains(str.charAt(i))) {
				return true;
			}
			control.add(str.charAt(i));
		}
		
		return false;
	}
	
	//O(n) using an array
	public static boolean hasRepeatedChars2(String str) {
		boolean[] chars = new boolean[128]; // Assuming the char set is ASCII
		for(int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			if(chars[c]) {
				return true;
			}
			chars[c] = true;
		}
		return false;
	}
	
	// O( n log n) - Assuming String as char[] to avoid using of extra space
	public static boolean hasRepeatedChars3(char[] str) {
		Arrays.sort(str);
		char previous = str[0];
		for(int i = 1; i < str.length; i++) {
			if(previous == str[i]) {
				return true;
			}
			previous = str[i];
		}
		return false;
	}
}
