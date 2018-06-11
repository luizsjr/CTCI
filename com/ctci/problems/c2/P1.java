package com.ctci.problems.c2;

import java.util.HashSet;

import com.ctci.util.ListNode;

public class P1<I> {
	
	private ListNode<I> head;
	
	@SafeVarargs
	public P1(I...items) {
		this.head = new ListNode<I>(items);
	}
	
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
			ListNode<I> nPlus1 = n.getNext();
			while (nPlus1!=null) {
				if (n.getData().equals(nPlus1.getData())) { // null values are not allowed
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
		System.out.println(head.getListNodeString());
	}
	
	public static void main(String[] args) {
		Character[] list = {'a','a','b','b','a','c','b'};
		P1<Character> problem = new P1<>(list);
		
		problem.printList();
		problem.removeDups();
		problem.printList();
		
		System.out.println("============================");
		
		problem = new P1<>(list);
		problem.printList();
		problem.removeDups2();
		problem.printList();
	}
}
