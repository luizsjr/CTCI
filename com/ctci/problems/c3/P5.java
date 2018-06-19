package com.ctci.problems.c3;

import java.util.Stack;

public class P5 {
	
	// 0 (N2) time and 0 (N) space
	public static Stack<Integer> sort(Stack<Integer> origin) {
		if(origin == null) { return null; }
		
		Stack<Integer> sorted = new Stack<>();
		while (!origin.isEmpty()) {
			int buffer = origin.pop();
			while (!sorted.isEmpty() && buffer > sorted.peek()) {
				origin.push(sorted.pop());
			}
			sorted.push(buffer);
		}
		return sorted;
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack = P5.sort(stack);
		System.out.println(stack);
	}
}
