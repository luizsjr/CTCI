package com.ctci.problems.c2;

import com.ctci.util.ListNode;

public class P7 {

	public ListNode<Integer> getIntersection(ListNode<Integer> listA, ListNode<Integer> listB) {
		
		if (listA==null && listB==null) {
			return null;
		}
		
		Result resultA = getSizeAndTail(listA);
		Result resultB = getSizeAndTail(listB);
		
		if (resultA.getTail()==resultB.getTail()) {
		
			// Last Items are the same, normalize size and find intersection
			int diffSize=resultA.getSize()-resultB.getSize();
			if(diffSize>0) {
				listA=skipNodes(listA, diffSize);
			} else if(diffSize<0) {
				listB=skipNodes(listB, diffSize*-1);
			}
			return findIntersection(listA, listB);
		}
		return null;
	}
	
	protected class Result {
		private int size;
		private ListNode<Integer> tail;
		
		public Result(int size, ListNode<Integer> tail) {
			super();
			this.size = size;
			this.tail = tail;
		}

		public int getSize() {
			return size;
		}

		public ListNode<Integer> getTail() {
			return tail;
		}
	}
	
	protected Result getSizeAndTail(ListNode<Integer> list) {
		int size = 1;
		while (list.getNext()!=null) {
			size++;
			list=list.getNext();
		}
		return new Result(size, list);
	}
	
	protected ListNode<Integer> skipNodes(ListNode<Integer> list, int skipNum) {
		while (list!=null && skipNum-->0) {
			list=list.getNext();
		}
		return list;
	}
	
	protected ListNode<Integer> findIntersection(ListNode<Integer> listA, ListNode<Integer> listB) {
		while (listA!=null) {
			if (listA==listB) {
				return listA;
			}
			listA = listA.getNext();
			listB = listB.getNext();
		}
		return null;
	}
	
	public static void main(String[] args) {
		ListNode<Integer> listA = new ListNode<Integer>(1,2,3,4,5); // 1,2,3,4,5
		ListNode<Integer> listB = listA.getNext().getNext().getNext(); // 4,5
		ListNode<Integer> listC = new ListNode<Integer>(1,2); //1,2 
		listC.getNext().setNext(listA.getNext().getNext()); //1,2,3,4,5 (different 1,2)
		ListNode<Integer> listD = new ListNode<Integer>(1,2,3,4,5); // 1,2,3,4,5 different list
		
		System.out.println("A: "+listA.getListNodeString());
		System.out.println("B: "+listB.getListNodeString());
		System.out.println("C: "+listC.getListNodeString());
		System.out.println("D: "+listD.getListNodeString());
		
		P7 problem = new P7();
		ListNode<Integer> intersect = problem.getIntersection(listA, listB);
		System.out.print("A-B: ");
		if(intersect==null) {
			System.out.println("null");
		} else {
			System.out.println(intersect.getData());
		}
		
		System.out.print("A-C: ");
		intersect = problem.getIntersection(listA, listC);
		if(intersect==null) {
			System.out.println("null");
		} else {
			System.out.println(intersect.getData());
		}
		
		System.out.print("A-D: ");
		intersect = problem.getIntersection(listA, listD);
		if(intersect==null) {
			System.out.println("null");
		} else {
			System.out.println(intersect.getData());
		}
	}

		
}
