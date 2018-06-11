package com.ctci.problems.c2;

import com.ctci.util.ListNode;

public class P4 {
	
	public ListNode<Integer> partitionList(ListNode<Integer> node, int value) {
		ListNode<Integer> left = node;
		ListNode<Integer> pivot = node;
		ListNode<Integer> right = node;
		
		while (node!=null) {
			ListNode<Integer> next = node.getNext();
			if (node.getData() < value) {
				node.setNext(left);
				left = node;
			} else if (node.getData() > value) {
				right.setNext(node);
				right = node;
			} else { // This solution will keep the pivot elements in the middle of the list
				node.setNext(pivot.getNext());
				pivot.setNext(node);
				if(right==pivot) right=node;
				pivot = node;
			}
			node = next;
		}
		right.setNext(null);
		
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
