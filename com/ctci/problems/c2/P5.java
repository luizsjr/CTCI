package com.ctci.problems.c2;

import com.ctci.util.ListNode;

public class P5 {
	
	// O(n) - Assuming l1 and l2 have the same size
	public ListNode<Integer> sumLists(ListNode<Integer> l1, ListNode<Integer> l2) {
		int carryOver=0;
		ListNode<Integer> head = l1;
		ListNode<Integer> lastDigit = l1;
		while (l1!=null) {
			int sum = l1.getData() + l2.getData() + carryOver;
			carryOver = sum/10;
			l1.setData( sum%10 );
			lastDigit = l1;
			l1 = l1.getNext();
			l2 = l2.getNext();
		}
		if (carryOver>0) {
			ListNode<Integer> newDigit = new ListNode<>(carryOver);
			lastDigit.setNext(newDigit);
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode<Integer> l1 = new ListNode<>(7,1,6);
		ListNode<Integer> l2 = new ListNode<>(5,9,3);
		P5 problem = new P5();
		l1 = problem.sumLists(l1, l2);
		System.out.println(l1.getListNodeString());
		

	}

}
