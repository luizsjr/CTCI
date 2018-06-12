package com.ctci.problems.c2;

import com.ctci.util.ListNode;

public class P6 {
	
	// O(n) - cleaner solution using a separated inverted list with the first n/2 nodes
	public boolean isPalindrome(ListNode<Character> node) {
		if (node==null) { return false; }
		ListNode<Character> stack=new ListNode<>(node.getData());
		ListNode<Character> mid=node;
		
		// Put the first n/2 nodes in a inverted list 
		int listSize=1;
		while (node.getNext()!=null) {
			node = node.getNext();
			if (listSize++%2==0) {
				mid=mid.getNext();
				ListNode<Character> topNode=new ListNode<>(mid.getData());
				topNode.setNext(stack);
				stack = topNode;
			}
		}
		
		// Compare the inverted list with nodes starting on: 
		// mid - odd sized lists
		// mid.next - even sized lists
		if(listSize%2==0) mid=mid.getNext();
		while (mid!=null) {
			if (!mid.getData().equals(stack.getData())) {
				return false;
			}
			mid = mid.getNext();
			stack = stack.getNext();
		}
		return true;
	}
	
	// O(n) - more complex solution inverting the first n/2 nodes without a separate list. 
	// This solution changes the original list order
	public boolean isPalindrome2(ListNode<Character> node) {
		if (node==null) { return false; }
		ListNode<Character> previous=null;
		ListNode<Character> current=node;
		ListNode<Character> next=node.getNext();
		
		// Invert the next of the first n/2 nodes 
		int control=0;
		int listSize=1;
		node=node.getNext();
		current.setNext(null);
		while (node!=null) {
			listSize++;
			node = node.getNext();
			if (++control%2==0) {
				previous=current;
				current=next;
				next=next.getNext();
				current.setNext(previous);
			}
		}
		
		// Compare the list starting on current with the list starting on next
		if(listSize%2>0) current=current.getNext();
		while (next!=null) {
			if (!next.getData().equals(current.getData())) {
				return false;
			}
			next = next.getNext();
			current = current.getNext();
		}
		return true;
	}
	
	public static void main(String[] args) {
		P6 problem = new P6();
		Character[] items = {'a','b','c','b','a'};
		
		ListNode<Character> list = new ListNode<Character>(items);
		System.out.println( problem.isPalindrome(list) );
		
		list = new ListNode<Character>(items);
		System.out.println( problem.isPalindrome2(list) );
		
	}
}
