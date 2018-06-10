package com.ctci.problems.c2;

import java.util.HashSet;

import com.ctci.util.ListNode;

public class P1<I> {
	
	private ListNode<I> head;
	
	// O(n) - with buffer
	public void removeDups() {
		HashSet<I> buffer = new HashSet<>();
		ListNode<I> n = head;
		while (n!=null) {
			I value = n.getData();
			if (buffer.contains(value)) {
				ListNode<I> next = n.getNext();
				ListNode<I> previous = n.getPrevious();
				if (previous != null) previous.setNext(next);
				if (next != null) next.setPrevious(previous);
			} else {
				buffer.add(value);
			}
			n = n.getNext();
		}
	}
	
	// O(n^2) - no buffer
	public void removeDups2() {
		ListNode<I> n = head;
		while (n!=null) {
			I valueN = n.getData();
			ListNode<I> nPlus1 = n.getNext();
			while (nPlus1!=null) {
				if (valueN.equals(nPlus1.getData())) { // null values are not allowed
					ListNode<I> next = nPlus1.getNext();
					ListNode<I> previous = nPlus1.getPrevious();
					if (previous != null) previous.setNext(next);
					if (next != null) next.setPrevious(previous);
				}
				nPlus1 = nPlus1.getNext();
			}
			n = n.getNext();
		}
	}
	
	public void printList() {
		ListNode<I> n = head;
		StringBuilder sb = new StringBuilder();
		while (n!=null) {
			sb.append(n.getData() + " ");
			n = n.getNext();
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) {
		P1<Character> problem = new P1<>();
		
		Character[] list = {'a','a','b','b','a','c','b'};
		
		problem.generateTestList(list);
		problem.printList();
		problem.removeDups();
		problem.printList();
		
		System.out.println("============================");
		
		problem.generateTestList(list);
		problem.printList();
		problem.removeDups2();
		problem.printList();
	}
	
	@SafeVarargs
	public final void generateTestList(I...items){
		if (items.length > 0) {
			head = new ListNode<I>(items[0]);
		}
		ListNode<I> n = head;
		for (int i=1; i<items.length; i++) {
			ListNode<I> n1 = new ListNode<>(items[i]);
			n.setNext(n1);
			n1.setPrevious(n);
			n = n1;
		}
	}
}
