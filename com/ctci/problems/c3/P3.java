package com.ctci.problems.c3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P3<I> {
	
	int maxStackSize;
	List<Stack<I>> stackSet;
	
	public P3(int maxStackSize) {
		this.maxStackSize = maxStackSize;
		stackSet = new ArrayList<>(); 
	}
	
	public boolean isEmpty() {
		return stackSet.isEmpty();
	}
	
	protected int getTopStackId() {
		return stackSet.size()-1;
	}
	
	protected Stack<I> getTopStack() {
		return getStack( getTopStackId() );
	}
	
	protected Stack<I> getStack(int stackId) {
		if (!isEmpty() && stackId < stackSet.size()) {
			return stackSet.get( stackId );
		}
		return null;
	}
	
	public void push(I value) {
		Stack<I> stack = getTopStack();
		if (stack==null || stack.size()==maxStackSize) {
			stack = new Stack<I>();
			stackSet.add(stack);
		}
		stack.push(value);
	}
	
	public I peek() {
		return retrieve(getTopStackId(), true);
	}
	
	public I peekAt(int stackId) {
		return retrieve(stackId, true);
	}
	
	public I pop() {
		return retrieve(getTopStackId(), false);
	}
	
	public I popAt(int stackId) {
		return retrieve(stackId, false);
	}
	
	// Assuming it's ok having stacks with different sizes if pop was executed in a middle stack
	// Otherwise a shifting elements from the top stacks would be needed
	protected I retrieve(int stackId, boolean peek) {
		Stack<I> stack = getStack(stackId);
		if (stack==null) {
			throw new RuntimeException("Stack is empty");
		}
		
		if(peek) {
			return stack.peek();
		}
		
		I value = stack.pop();
		if (stack.empty()) {
			stackSet.remove(stack);
		}
		return value;
	}
	
	public static void main(String[] args) {
		P3<Integer> problem = new P3<>(3);
		problem.push(1);
		problem.push(2);
		problem.push(3);
		problem.push(4);
		problem.push(5);
		problem.push(6);
		problem.push(7);
		System.out.println(problem.peek()); // 7
		System.out.println(problem.peekAt(0)); // 3
		System.out.println(problem.peekAt(1)); // 6
		System.out.println(problem.peekAt(2)); // 7
		System.out.println(problem.popAt(0)); // 3
		System.out.println(problem.popAt(1)); // 6
		System.out.println(problem.popAt(2)); // 7
		System.out.println(problem.pop()); // 5
		System.out.println(problem.pop()); // 4
		System.out.println(problem.pop()); // 2
		System.out.println(problem.pop()); // 1
		problem.push(1);
		System.out.println(problem.pop()); // 1
		System.out.println(problem.pop()); // RuntimeException: Stack is Empty
		

	}

}
