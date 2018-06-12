package com.ctci.problems.c2;

import com.ctci.util.ListNode;

public class P5 {
	
	// O(n) - Assumes l1 and l2 have the same size
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
	
	// O(n) - recursive method
	public ListNode<Integer> sumLists(ListNode<Integer> l1, ListNode<Integer> l2, int carryOver) {
		// Base Case
		if(l1==null && l2==null && carryOver==0) { return null; }
		
		int sum = carryOver;
		if(l1!=null) sum += l1.getData();
		if(l2!=null) sum += l2.getData();
		
		ListNode<Integer> result = new ListNode<Integer>(sum%10);
		
		ListNode<Integer> next = sumLists( l1 == null ? null : l1.getNext(),
											l2 == null ? null : l2.getNext(),
											sum / 10 );
		
		result.setNext(next);
		
		return result;
	}
	
	// O(n) - Follow up problem. A simpler solution would be inverting the lists and summing using the first solution
	public ListNode<Integer> sumListsInverseOrder(ListNode<Integer> l1, ListNode<Integer> l2) {
		
		int l1Length = l1.length();
		int l2Length = l2.length();
		
		if (l1Length > l2Length) {
			l2 = addZerosToLeft(l2, l1Length-l2Length);
		} else if( l2Length > l1Length) {
			l1 = addZerosToLeft(l1, l2Length-l1Length);
		}
		
		ListNode<Integer> result = new ListNode<>(0);
		int carryOver = sumListsInverseOrder(l1, l2, result);
		if (carryOver>0) {
			ListNode<Integer> node = new ListNode<Integer>(carryOver);
			node.setNext( result );
			result = node;
		}
		return result;
	}

	
	protected ListNode<Integer> addZerosToLeft(ListNode<Integer> node, int num) {
		if(node!=null) {
			for (int i=0; i<num; i++) {
				ListNode<Integer> zeroNode = new ListNode<>(0);
				zeroNode.setNext(node);
				node=zeroNode;
			}
		}
		return node;
	}
	
	protected int sumListsInverseOrder(ListNode<Integer> l1, ListNode<Integer> l2, ListNode<Integer> result) {
		// Base Case
		if (l1==null && l2==null) { return -1; }
		
		
		// Recurse until end of the list
		ListNode<Integer> next = new ListNode<>(0);
		int sum = sumListsInverseOrder( l1==null ? null : l1.getNext(), 
										l2==null ? null : l2.getNext(),
										next);
		
		if (sum < 0) {
			sum=0;
		} else {
			result.setNext(next);
		}
		
		// Sum items
		if (l1!=null) { sum += l1.getData(); }
		if (l2!=null) { sum += l2.getData(); }
		
		result.setData(sum%10);
		return sum/10; // Carry Over
	}

	public static void main(String[] args) {
		P5 problem = new P5();
		ListNode<Integer> l1 = new ListNode<>(7,1,6,1);
		ListNode<Integer> l2 = new ListNode<>(5,9,3);
		l1 = problem.sumLists(l1, l2, 0);
		System.out.println(l1.getListNodeString());
		
		l1 = new ListNode<>(7,1,6);
		l2 = new ListNode<>(5,9,3);
		l1 = problem.sumLists(l1, l2);
		System.out.println(l1.getListNodeString());

		l1 = new ListNode<>(1,6,1,7);
		l2 = new ListNode<>(3,9,5);
		l1 = problem.sumListsInverseOrder(l1, l2);
		System.out.println(l1.getListNodeString());

	}

}
