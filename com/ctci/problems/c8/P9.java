package com.ctci.problems.c8;

import java.util.ArrayList;
import java.util.List;

public class P9 {
	
	public List<String> parentesisCombinations(int n) {
		List<String> combinations = new ArrayList<>();
		if (n>0) {
			parentesisCombinations("(", n-1, n, combinations);
		}
		return combinations;
	}

	private void parentesisCombinations(String prefix, int open, int close, List<String> combinations) {
		if (open+close==0) {
			combinations.add(prefix);
			return;
		}
		if(open>0) {
			parentesisCombinations(prefix+'(', open-1, close, combinations);
		}
		if(close>open) {
			parentesisCombinations(prefix+')', open, close-1, combinations);
		}
	}
	
	public static void main(String[] args) {
		P9 problem = new P9();
		System.out.println(problem.parentesisCombinations(4)); // [((())), (()()), (())(), ()(()), ()()()]
	}
}
