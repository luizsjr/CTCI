package com.ctci.util;

public class ListNode<I> {
	
	private ListNode<I> previous;
	private ListNode<I> next;
	private I data;
	
	public ListNode(I data) {
		this.data = data;
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
}