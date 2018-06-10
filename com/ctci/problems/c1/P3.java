package com.ctci.problems.c1;

public class P3 {

	public static void main(String[] args) {
		P3 problem = new P3();
		
		char[] str = "Mr John Smith    ".toCharArray();
		int realSize = 13;
		System.out.println(problem.urlify(str, realSize));

	}
	
	public char[] urlify(char[] str, int realSize) {
		int bufferSize = str.length - realSize;
		
		if (bufferSize==0) return str;
		
		for (int i=str.length-bufferSize-1; i>=0; i--) {
			if (str[i] != ' ') {
				str[i+bufferSize] = str[i];
			} else {
				str[i+bufferSize--] = '0';
				str[i+bufferSize--] = '2';
				str[i+bufferSize] = '%';
				if (bufferSize==0) break;
			}
		}
		return str;
	}

}
