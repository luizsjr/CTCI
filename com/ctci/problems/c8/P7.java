package com.ctci.problems.c8;

import java.util.ArrayList;
import java.util.List;

public class P7 {
	
	/*
	 * P(abc) = 'a' combined in every position of P(bc), which is 'b' combined in every position of P(c)
	 */
	public List<String> permutations(String str) {
		if (str == null) { return null; }
		
		List<String> result;
		if (str.length()<=1) { // Base Case
			result = new ArrayList<>();
			result.add(str);
		} else {
			char prefix = str.charAt(0);
			String suffix = str.substring(1);
			result = permutations(suffix);
			result = combineAll(prefix, result);
		}
		return result;
	}
	
	private List<String> combineAll(char c, List<String> words) {
		List<String> newWords = new ArrayList<>();
		for (String word : words) {
			newWords.addAll( combine(c, word) );
		}
		return newWords;
	}

	private List<String> combine(char c, String word) {
		List<String> newWords = new ArrayList<>();
		for (int i=0; i<=word.length(); i++) {
			StringBuilder newWord = new StringBuilder();
			newWord.append( word.substring(0, i) );
			newWord.append( c );
			newWord.append( word.substring(i) );
			newWords.add(newWord.toString());
		}
		return newWords;
	}
	
	/********************************************/
	
	/*
	 * P(abc) = 'a' in front of every result of P(bc) +
	 *          'b' in front of every result of P(ac) +
	 *          'c' in front of every result of P(ac)
	 */
	public List<String> permutations2(String str) {
		if (str == null) { return null; }
		
		List<String> result = new ArrayList<>();
		if (str.length()<=1) { // Base Case
			result.add(str);
		} else {
			for (int i=0; i<str.length(); i++) {
				// Remove charAt(i) from str and get the permutations
				StringBuilder sb = new StringBuilder();
				sb.append(str.substring(0, i));
				sb.append(str.substring(i+1));
				List<String> newWords = permutations2(sb.toString());
				
				// Append charAt(i) on the resulting permutations
				for (int j=0; j<newWords.size(); j++) {
					String newWord = str.charAt(i) + newWords.get(j);
					result.add(newWord);
				}
			}
		}
		return result;
	}
	
	/********************************************/
	/*
	 * Similar to 2, but result and prefix are pushed into the permutation method
	 */
	public List<String> permutations3(String str) {
		if (str == null) { return null; }
		List<String> result = new ArrayList<>();
		permutations3("", str, result);
		return result;
	}
	
	private void permutations3(String prefix, String suffix, List<String> permutations) {
		if (suffix.length()==0) {
			permutations.add(prefix);
		} else {
			for (int i=0; i<suffix.length(); i++) {
				StringBuilder newPrefix = new StringBuilder(prefix);
				newPrefix.append( suffix.charAt(i) );
				
				StringBuilder newSuffix = new StringBuilder();
				newSuffix.append(suffix.substring(0, i));
				newSuffix.append(suffix.substring(i+1));
				
				permutations3(newPrefix.toString(), newSuffix.toString(), permutations);
			}
		}
	}
	
	public static void main(String[] args) {
		P7 problem = new P7();
		System.out.println(problem.permutations3(null)); // null
		System.out.println(problem.permutations3("")); // []
		System.out.println(problem.permutations3("abc")); // [abc, acb, bac, bca, cab, cba]
	}

}
