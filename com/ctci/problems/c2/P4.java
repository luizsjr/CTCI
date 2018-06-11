package com.ctci.problems.c2;

import com.ctci.util.ListNode;

public class P4 {
	
	public ListNode<Integer> partitionList(ListNode<Integer> head, int value) {
		ListNode<Integer> left = head;
		if(left!=null) {
			ListNode<Integer> next = head.getNext();
			left.setNext(null);
			ListNode<Integer> pivot = head;
			ListNode<Integer> right = head;
			
			while (next!=null) {
				ListNode<Integer> n = next;
				next = n.getNext();
				if (n.getData() < value) {
					n.setNext(left);
					left = n;
				} else if (n.getData() > value) {
					right.setNext(n);
					right = n;
				} else { // This solution will keep the pivot elements in the middle of the list
					n.setNext(pivot.getNext());
					pivot.setNext(n);
					if(right==pivot) right=n;
					pivot = n;
				}
			}
			right.setNext(null);
		}
		return left;
	}
	
	public static void main(String[] args) {
		P4 problem = new P4();
		ListNode<Integer> head = new ListNode<>(3,5,8,5,10,2,1);
		System.out.println(head.getListNodeString());
		head = problem.partitionList(head, 5);
		System.out.println(head.getListNodeString());

	}

}
