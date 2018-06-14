package com.ctci.problems.c3;

import java.text.MessageFormat;

public class P1 {

	int[] stackStorage;
	int[] stackLen;
	int stackSize;
	
	public P1(int stacksToStore, int stackSize){
		stackLen = new int[stacksToStore];
		stackStorage = new int[stacksToStore*stackSize];
		this.stackSize = stackSize;
	}
	
	public boolean isEmpty(int stackId) {
		return stackLen[stackId]==0;
	}
	
	public boolean isFull(int stackId) {
		return stackLen[stackId] >= stackSize;
	}
	
	protected int getTopIndex(int stackId, boolean newTop) {
		int index = stackId*stackSize+stackLen[stackId];
		if (!newTop) { index--; }
		return index;
	}
	
	public void put(int stackId, int value) {
		if ( isFull(stackId) ) {
			throw new RuntimeException(MessageFormat.format("Stack {0} reached max capactity", stackId));
		}
		int index = getTopIndex(stackId, true);
		stackStorage[index]=value;
		stackLen[stackId]++;
	}
	
	public int peek(int stackId) {
		return retrieve(stackId, false);
	}
	
	public int pop(int stackId) {
		return retrieve(stackId, true);
	}
	
	protected int retrieve(int stackId, boolean pop) {
		if (isEmpty(stackId)) {
			throw new RuntimeException(MessageFormat.format("Stack {0} is empty", stackId));
		}
		int index = getTopIndex(stackId, false);
		int topValue = stackStorage[index];
		if(pop) { 
			stackStorage[index]=0;
			stackLen[stackId]--; 
		}
		return topValue;
	}
	
	public static void main(String[] args) {
		P1 arrayStack = new P1(3,3);
		
		System.out.println(arrayStack.isEmpty(0)); //true
		
		try {
			arrayStack.peek(0);
		} catch (Exception e) {
			System.out.println(e.getMessage()); // stack 0 is empty
		}
		
		try {
			arrayStack.pop(0);
		} catch (Exception e) {
			System.out.println(e.getMessage()); // stack 0 is empty
		}
		
		arrayStack.put(0,0);
		System.out.println(arrayStack.isEmpty(0)); // false
		System.out.println(arrayStack.peek(0)); // 0
		System.out.println(arrayStack.pop(0)); // 0
		System.out.println(arrayStack.isEmpty(0)); // true
		arrayStack.put(0,0);
		arrayStack.put(0,1);
		arrayStack.put(0,2);
		try {
			arrayStack.put(0,3);
		} catch (Exception e) {
			System.out.println(e.getMessage()); // stack 0 reached max capacity
		}
		System.out.println(arrayStack.pop(0)); // 2
		arrayStack.put(0,2);
		System.out.println(arrayStack.peek(0)); // 2
		try {
			arrayStack.put(0,3);
		} catch (Exception e) {
			System.out.println(e.getMessage()); // stack 0 reached max capacity
		}
		
		arrayStack.put(1,3);
		arrayStack.put(1,4);
		arrayStack.put(1,5);
		try {
			arrayStack.put(1,6);
		} catch (Exception e) {
			System.out.println(e.getMessage()); // stack 1 reached max capacity
		}
		System.out.println(arrayStack.pop(1)); // 5
	}
}
