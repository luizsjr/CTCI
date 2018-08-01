package com.leetcode;

public class IntersectionOfTwoLinkedLists {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
	    }
	 }
	
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        if (headA==null || headB==null) { return null;}
        
        int sizeA = 1;
        int sizeB = 1;
        ListNode tailA = headA;
        ListNode tailB = headB;
        while (tailA.next!=null || tailB.next!=null) {
            if (tailA.next!=null) { sizeA++; tailA=tailA.next; }
            if (tailB.next!=null) { sizeB++; tailB=tailB.next; }
        }
        
        if (tailA!=tailB) {return null; }
        
        ListNode bigger;
        ListNode smaller;
        int offset = sizeA-sizeB;
        if (offset>0) {
            bigger = headA;
            smaller= headB;
        } else {
            bigger =headB;
            smaller=headA;
        }
        
        while (offset-- > 0) {
            bigger=bigger.next;
        }
        
        while (bigger!=smaller) {
            bigger=bigger.next;
            smaller=smaller.next;
        }
        return bigger;    
    }
	
	public static void main(String[] args) {
		IntersectionOfTwoLinkedLists solution = new IntersectionOfTwoLinkedLists();
		ListNode l1 = solution.new ListNode(3);
		ListNode l2 = solution.new ListNode(2);
		l2.next=l1;
		
		solution.getIntersectionNode(l1, l2);
	}

}
