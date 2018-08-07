package com.ctci.problems.c8;

import java.text.MessageFormat;
import java.util.Stack;

public class P6 {
	
	private int disks;
	private int moves;
	private Stack<Integer> t1;
	private Stack<Integer> t2;
	private Stack<Integer> t3;
	
	public P6(int d) {
		moves=0;
		disks = d;
		t1 = new Stack<>();
		t2 = new Stack<>();
		t3 = new Stack<>();
		
		while ( d>0 ) {
			add(t1, d--);
		}
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("moves={0} | t1={1} t2={2} t3={3}", moves, t1, t2, t3);
	}
	
	public void solve() {
		moveDisks(disks, t1, t3, t2);
	}
	
	private void moveDisks(int disks, Stack<Integer> origin, Stack<Integer> destination, Stack<Integer> buffer) {
		if(disks==0) { return; }
		// moves disks-1 disks from origin to buffer
		moveDisks(disks-1, origin, buffer, destination);
		
		// move origin top to destination
		moveTop(origin, destination);
		
		// moves disks on buffer to destination
		moveDisks(disks-1, buffer, destination, origin);
	}
	
	private void add(Stack<Integer> tower, int disk) {
		if(!tower.isEmpty() && tower.peek()<=disk) {
			System.out.println(disk+" is bigger than the tower top");
			return;
		}
		tower.push(disk);
	}
	
	private void moveTop(Stack<Integer> origin, Stack<Integer> destination) {
		if(origin.isEmpty()) {
			return;
		}
		int disk = origin.pop();
		add(destination, disk);
		moves++;
	}
	
	public static void main(String[] args) {
		P6 problem = new P6(6);
		System.out.println(problem);
		problem.solve();
		System.out.println(problem);
	}
}
