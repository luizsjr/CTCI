package com.ctci.problems.c5;

public class P2 {
	
	public String decimalToBinaryFraction(double dec) {
		StringBuilder result = new StringBuilder("0");
		if (dec > 0 && dec < 1) {
			result.append('.');
			int precision=32;
			while (precision-- > 0 && dec > 0) {
				dec *= 2;
				if (dec < 1) {
					result.append('0');
				} else {
					result.append('1');
					dec -= 1;
				}
			}
		}
		if (dec != 0) { return "ERROR"; }
		
		return result.toString();
		
	}

	public static void main(String[] args) {
		P2 problem = new P2();
		System.out.println(problem.decimalToBinaryFraction(0));
		System.out.println(problem.decimalToBinaryFraction(0.015625*5));
		System.out.println(problem.decimalToBinaryFraction(0.25));
		System.out.println(problem.decimalToBinaryFraction(0.1));
		System.out.println(problem.decimalToBinaryFraction(1));
		System.out.println(problem.decimalToBinaryFraction(-0.1));
	}

}
