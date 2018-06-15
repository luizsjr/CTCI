package com.ctci.problems.c2;

import com.ctci.util.ListNode;

public class P8<I> {
	
	ListNode<I> list;
	
	@SafeVarargs
	public P8(I...items) {
		list = new ListNode<I>(items);
	}
	
	protected ListNode<I> getLastNode() {
		ListNode<I> last = list;
		while(last.getNext()!=null) {
			last = last.getNext();
		}
		return last;
	}
	
	public ListNode<I> addCircularReference(int position) {
		ListNode<I> last = getLastNode();
		ListNode<I> node = list;
		while(--position>0 && node!=null) {
			node=node.getNext();
		}
		
		last.setNext(node);
		return node;
	}

	// O(n)
	public ListNode<I> findCircularReferenceHead() {
			if (list==null) { return null;}
			ListNode<I> slowRunner = list;
			ListNode<I> fastRunner = list;
			
			while( fastRunner!=null && fastRunner.getNext()!=null) {
				slowRunner=slowRunner.getNext();
				fastRunner=fastRunner.getNext().getNext();
				if (slowRunner==fastRunner) {
					break;
				}
			}
			
			if(fastRunner==null || fastRunner.getNext()==null) {
				//Not a circular list
				return null;
			}
			
			slowRunner=list;
			while(slowRunner!=fastRunner) {
				slowRunner=slowRunner.getNext();
				fastRunner=fastRunner.getNext();
			}
			return slowRunner;
	}
	
	public static void main(String[] args) {
		P8<Integer> problem = new P8<>(1,2,3,4,5,6,7,8,9);
		
		ListNode<Integer> node = problem.addCircularReference(2);
		System.out.println(node==null?null:node.getData());
		
		node = problem.findCircularReferenceHead();
		System.out.println(node==null?null:node.getData());
	}

}
