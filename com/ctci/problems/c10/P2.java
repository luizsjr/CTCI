package com.ctci.problems.c10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class P2 {
	
	public static void groupAnagrams(String[] array) {
		if (array != null) {
			HashMap<String, List<String>> anagramMap = new HashMap<>();
			for (String s : array) {
				String key = getAnagramKey(s);
				List<String> anagrams = anagramMap.get(key);
				if(anagrams==null) {
					anagrams = new ArrayList<>();
					anagramMap.put(key, anagrams);
				}
				anagrams.add(s);
			}
			int i = 0;
			for (List<String> anagrams : anagramMap.values()) {
				for ( String s : anagrams) {
					array[i++] = s;
				}
			}
		}
	}
	
	protected static String getAnagramKey(String str) {
		char[] strChars = str.toLowerCase().toCharArray();
		int[] charCounter = new int['z'-'a'+1];
		for (char c : strChars) {
			charCounter[c-'a']++;
		}
		return Arrays.toString(charCounter);
	}
	
	public static void main(String[] args) {
		String[] array = {"z", "abc", "dfg", "ab", "cba", "ba", "a", "fdg"};
		groupAnagrams(array);
		System.out.println(Arrays.toString(array));
	}

}
