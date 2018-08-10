package com.ctci.problems.c8;

import java.text.MessageFormat;

public class P11 {
	
	private static int[] ValidCoins = {1,5,10,25}; //needs to be ordered
	
    public int centsCombinations(int amount) {
    	int[][] cache = new int[amount+1][ValidCoins.length];
        return centsCombinations(amount, ValidCoins.length-1, cache);
    }
	
	private int centsCombinations(int amount, int lastUsedCoinIndex, int[][] cache) {
		if (cache[amount][lastUsedCoinIndex]>0) { return cache[amount][lastUsedCoinIndex]; }
		if (amount==0) { return 1; }
        int combinations = 0;    
        for(int i=lastUsedCoinIndex; i>=0; i--) {
            if (amount>=ValidCoins[i]) {
                int newAmount = amount-ValidCoins[i];
                combinations += centsCombinations(newAmount, i, cache);
            }
        }
        cache[amount][lastUsedCoinIndex]=combinations;
        return combinations;
	}
	
	/***************************************************/
	
	public int centsCombinationsIterative(int amount) {
       int[] memo = new int[amount + 1];
        memo[0] = 1; //There is only 1 combination that sum to 0
        for (int coin : ValidCoins) {
            for (int midAmount = coin; midAmount <= amount; midAmount++) {
                memo[midAmount] += memo[midAmount - coin];
            }
        }
        return memo[amount];
	}
	
	public static void main(String[] args) {
		P11 s = new P11();
		for (int i=0; i<=5000; i++) {
			int rec = s.centsCombinations(i);
			int iter = s.centsCombinationsIterative(i);
			if (rec!=iter) {
				System.out.println(MessageFormat.format("i= {0}; rec={1}; iter={2}", i, rec, iter));
			}
		}
		System.out.println(s.centsCombinations(8)); // 4
		System.out.println(s.centsCombinationsIterative(8)); // 4
	}
}