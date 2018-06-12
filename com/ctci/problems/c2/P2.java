package com.ctci.problems.c2;

import com.ctci.util.ListNode;

public class P2<I> {
	
	private ListNode<I> head;
	
	@SafeVarargs
	public P2(I...items) {
		this.head = new ListNode<I>(items);
	}
	
	// O(n)
	public I findKthToLast(int k) {
		if (head == null) return null;
		
		ListNode<I> runner=head;
		ListNode<I> kthNode=null;
		
		int counter=1;
		while(counter++<k) {
			runner=runner.getNext();
			if(runner==null) return null;
		}
		kthNode = head;
		while (runner.getNext()!=null) {
			runner=runner.getNext();
			kthNode=kthNode.getNext();
		}
		return kthNode.getData();
		
	}
	
	// O(n2) - but cannot return the value without a wrapper
	public void findKthToLast2(int k) {
		findKthToLast2(head, k);
	}
	
	public int findKthToLast2(ListNode<I> head, int k) {
		
		
		if (head==null) { return 0; }
		
		int index=findKthToLast2(head.getNext(), k) + 1;
		if (index==k) {
			System.out.println(head.getData());
		}
		return index;
	}
	
	public void printList() {
		System.out.println(head.getListNodeString());
	}

	public static void main(String[] args) {
		P2<Character> problem = new P2<>('a','b','c','d','e');
		problem.printList();
		System.out.println( problem.findKthToLast(3) );
		problem.findKthToLast2(3);
	}

}
