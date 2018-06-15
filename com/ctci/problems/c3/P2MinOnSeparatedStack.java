package com.ctci.problems.c3;

import com.ctci.util.StackNode;

public class P2MinOnSeparatedStack {
	
	protected StackNode<Integer> top;
	protected StackNode<Integer> minNode;
	
	public void push(int data) {
		if ( isEmpty() || data<=minNode.getData() ) {
			//Add data to min stack 
			StackNode<Integer> newMin = new StackNode<>(data);
			newMin.setNext(minNode);
			minNode = newMin;
		}
		//Add data to values stack
		StackNode<Integer> newTop = new StackNode<>(data);
		newTop.setNext(top);
		top=newTop;
	}
	
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		
		StackNode<Integer> result=top;
		top=top.getNext();
		result.setNext(null);
		
		if (result.getData()==minNode.getData()) {
			minNode=minNode.getNext();
		}
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
		return minNode.getData();
	}
	
	public boolean isEmpty() {
		return top==null;
	}
	
	public static void main(String[] args) {
		P2MinOnSeparatedStack problem = new P2MinOnSeparatedStack(); 
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
