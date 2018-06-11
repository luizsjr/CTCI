package com.ctci.util;

public class ListNode<I> {
	
	private ListNode<I> previous;
	private ListNode<I> next;
	private I data;
	
	public ListNode(I data) {
		this.data = data;
	}
	
	@SafeVarargs
	public ListNode(I...items){
		if (items.length > 0) {
			this.data = items[0];
		}
		ListNode<I> n = this;
		for (int i=1; i<items.length; i++) {
			ListNode<I> n1 = new ListNode<>(items[i]);
			n.setNext(n1);
			n1.setPrevious(n);
			n = n1;
		}
	}
	
	public ListNode<I> getPrevious() {
		return previous;
	}
	
	public void setPrevious(ListNode<I> previous) {
		this.previous = previous;
	}
	
	public ListNode<I> getNext() {
		return next;
	}
	
	public void setNext(ListNode<I> next) {
		this.next = next;
	}
	
	public I getData() {
		return data;
	}
	
	public void setData(I data) {
		this.data = data;
	}
	
	public String getListNodeString() {
		ListNode<I> n = this;
		StringBuilder sb = new StringBuilder();
		while (n!=null) {
			sb.append(n.getData());
			n = n.getNext();
			if (n != null) sb.append(", ");
		}
		return sb.toString();
	}
}