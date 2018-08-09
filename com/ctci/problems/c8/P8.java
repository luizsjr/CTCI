package com.ctci.problems.c8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P8 {

	public List<String> permutations(String str) {
		if (str == null) { return null; }
		List<String> result = new ArrayList<>();
		Map<Character, Integer> charCounterMap = countChars(str);
		permutations("", charCounterMap, str.length(), result);
		return result;
	}
	
	private Map<Character, Integer> countChars(String str) {
		HashMap<Character, Integer> map = new HashMap<>();
		for(char c : str.toCharArray()) {
			int count = map.getOrDefault(c, 0);
			map.put(c, count+1);
		}
		return map;
	}
	
	private void permutations(String prefix, Map<Character, Integer> charCounterMap, int charsRemaining, List<String> permutations) {
		if (charsRemaining==0) {
			permutations.add(prefix);
		} else {
			for (char c : charCounterMap.keySet()) {
				int count = charCounterMap.get(c);
				if (count > 0) {
					charCounterMap.put(c, count-1);
					permutations(prefix+c, charCounterMap, charsRemaining-1, permutations);
					charCounterMap.put(c, count);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		P8 problem = new P8();
		System.out.println(problem.permutations(null)); // null
		System.out.println(problem.permutations("")); // []
		System.out.println(problem.permutations("abbb")); // [abbb, babb, bbab, bbba]
	}

}
