package com.ctci.problems.c3;

import com.ctci.util.StackNode;

public class P2MinOnNode {
	
	protected class StackNodeMin extends StackNode<Integer> {
		private int min;
		
		public StackNodeMin(int data, int min) {
			super(data);
			this.min=min;
		}
		
		public int getMin() {
			return min;
		}
		
		public void setMin(int min) {
			this.min = min;
		}
	}
	
	protected StackNodeMin top;
	
	public void push(int data) {
		int min = ( isEmpty() || data<top.getMin() ) ? data : top.getMin();
		StackNodeMin newTop = new StackNodeMin(data, min);
		newTop.setNext(top);
		top=newTop;
	}
	
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		
		StackNodeMin result=top;
		top=(StackNodeMin)top.getNext();
		result.setNext(null);
		return result.getData();
	}
	
	public int peek() {
		if(isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		return top.getData();
	}
	
	public int min(){
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		return top.getMin();
	}
	
	public boolean isEmpty() {
		return top==null;
	}
	
	public static void main(String[] args) {
		P2MinOnNode problem = new P2MinOnNode(); 
		problem.push(50);
		System.out.println(problem.peek()); // 50
		System.out.println(problem.min()); // 50
		
		problem.push(75);
		System.out.println(problem.peek()); // 75
		System.out.println(problem.min()); // 50
		
		problem.push(40);
		System.out.println(problem.peek()); // 40
		System.out.println(problem.min()); // 40
		
		System.out.println(problem.pop()); // 40
		System.out.println(problem.min()); // 50

		System.out.println(problem.peek()); // 75
		System.out.println(problem.min()); // 50
		
		System.out.println(problem.pop()); // 75
		System.out.println(problem.pop()); // 50
		
		try {
			System.out.println(problem.peek());
		} catch (Exception e) {
			System.out.println(e.getMessage());  // Stack is empty
		}
		try {
			System.out.println(problem.min());
		} catch (Exception e) {
			System.out.println(e.getMessage());  // Stack is empty
		}
	}
}
