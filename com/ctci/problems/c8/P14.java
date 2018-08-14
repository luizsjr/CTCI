package com.ctci.problems.c8;

import java.util.HashMap;
import java.util.Map;

public class P14 {
	
	public int countEval(String expression, boolean result) {
		Map<String, Integer> memo = new HashMap<>();
		return countEval(expression, result, memo);
	}
	
	private int countEval(String expression, boolean result, Map<String, Integer> memo) {
		if (expression.length()==0) { return 0; }
		if (expression.length()==1) { return result == str2bol(expression) ? 1 : 0 ; }
		
		Integer cachedResult = memo.get(result+expression);
		if (cachedResult!=null) { return cachedResult; }
		
		int ways = 0;
		for (int i=1; i<expression.length(); i+=2) {
			String leftExpression = expression.substring(0, i);
			String rightExpression = expression.substring(i+1);
			char operator = expression.charAt(i);
			
			int leftTrue=countEval(leftExpression, true, memo);
			int leftFalse=countEval(leftExpression, false, memo);
			int rightTrue=countEval(rightExpression, true, memo);
			int rightFalse=countEval(rightExpression, false, memo);
			
			int totalResults = (leftTrue+leftFalse) * (rightTrue+rightFalse);
			
			int totalTrue = 0;
			if (operator=='^') {
				totalTrue = leftTrue*rightFalse + leftFalse*rightTrue;
			} else if (operator=='&') {
				totalTrue = leftTrue*rightTrue;
			} else if (operator=='|') {
				totalTrue = leftTrue*rightFalse + leftFalse*rightTrue + leftTrue*rightTrue;
			}
			
			ways += result ? totalTrue : totalResults - totalTrue; 
		}
		memo.put(result+expression, ways);
		return ways;
	}
	
	private boolean str2bol(String str) {
		return "1".equals(str);
	}
	
	public static void main(String[] args) {
		P14 problem = new P14();
		System.out.println(problem.countEval("1^0|0|1", false)); // 2
		System.out.println(problem.countEval("0&0&0&1^1|0", true)); // 10
	}

}
