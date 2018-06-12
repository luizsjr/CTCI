package com.ctci.problems.c2;

import com.ctci.util.ListNode;

public class P3<I> {

	private ListNode<I> head;
	private ListNode<I> current;
	
	@SafeVarargs
	public P3(I...items) {
		head = new ListNode<I>(items);
		current = head;
	}
	
	public boolean hasNext() {
		return current!=null;
	}
	
	public ListNode<I> next() {
		ListNode<I> node = current;
		if (current!=null) current = current.getNext();
		return node;
	}
	
	public void printList() {
		System.out.println(head.getListNodeString());
	}
	
	public boolean deleteNode(ListNode<I> n) {
		ListNode<I> n1 = n.getNext();
		if(n1==null) {
			// Can't delete the last node
			return false;
		}
		
		n.setData(n1.getData());
		n.setNext(n1.getNext());
		return true;
		
	}
	
	public static void main(String[] args) {
		P3<Character> problem = new P3<>('a','b','c','d','e');
		problem.printList();
		problem.next(); // a
		problem.next(); // b
		ListNode<Character> node = problem.next(); // c
		problem.deleteNode(node);
		problem.printList();
		

	}

}
