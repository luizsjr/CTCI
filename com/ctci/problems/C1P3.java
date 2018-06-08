package com.ctci.problems;

public class C1P3 {

	public static void main(String[] args) {
		C1P3 problem = new C1P3();
		
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
