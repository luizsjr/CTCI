package com.ctci.problems.c3;

import java.util.Stack;

public class P4<T> {
	private Stack<T> poll;
	private Stack<T> add;
	
	public P4() {
		poll = new Stack<>();
		add = new Stack<>();
	}
	
	public boolean isEmpty() {
		return poll.isEmpty() && add.isEmpty();
	}
	
	public int size() {
		return poll.size() + add.size();
	}
	
	public void add(T value) {
		add.push(value);
	}
	
	public T poll() { 
		return retrieve(true);
	}
	
	public T peak() { 
		return retrieve(false);
	}
	
	protected T retrieve(boolean isPoll) {
		if (poll.isEmpty() && !add.isEmpty()) {
			shiftStacks();
		}
		
		if(isPoll) {
			return poll.pop();
		}
		return poll.peek();
	}
	
	protected void shiftStacks() {
		while (!add.isEmpty()) {
			poll.push(add.pop());
		}
	}
	
	public static void main(String[] args) {
		P4<Integer> queue = new P4<>();
		System.out.println(queue.isEmpty()); // true
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		System.out.println(queue.isEmpty()); // false
		System.out.println(queue.size()); // 4
		System.out.println(queue.poll()); // 1
		System.out.println(queue.poll()); // 2
		System.out.println(queue.poll()); // 3
		queue.add(5);
		System.out.println(queue.poll()); // 4
		queue.add(6);
		System.out.println(queue.poll()); // 5
		queue.add(7);
		queue.add(8);
		System.out.println(queue.poll()); // 6
		System.out.println(queue.poll()); // 7
		System.out.println(queue.poll()); // 8
		System.out.println(queue.size()); // 0
		try {
			System.out.println(queue.poll()); 
		} catch (Exception e) {
			System.out.println(e.toString()); // java.util.EmptyStackException
		}
	}
}
