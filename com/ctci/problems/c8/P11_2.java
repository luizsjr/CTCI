package com.ctci.problems.c8;

import java.util.ArrayList;
import java.util.List;

public class P11_2 {
	
	private static int[] ValidCoins = {1,5,10,25}; //needs to be ordered
	
	public List<List<Integer>> centsCombinations(int amount) {
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> combination = new ArrayList<>();
		if(amount>0) {
			centsCombinations(combination, ValidCoins.length-1, amount, results);
		}
		return results;
	}
	
	private void centsCombinations(List<Integer> combination, int lastCoinIndex, int amountMissing, List<List<Integer>> combinations) {
		if (amountMissing==0) {
			List<Integer> newResult = new ArrayList<>();
			newResult.addAll(combination);
			combinations.add(newResult);
		} else {
			for(int i=lastCoinIndex; i>=0; i--) {
				if (amountMissing>=ValidCoins[i]) {
					combination.add(ValidCoins[i]);
					centsCombinations(combination, i, amountMissing-ValidCoins[i], combinations);
					combination.remove(combination.size()-1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		P11_2 problem = new P11_2();
		System.out.println(problem.centsCombinations(10)); // [[10], [5, 5], [5, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]]
	}

}
