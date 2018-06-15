package com.ctci.util;

public class StackNode<I> {
	private I data;
	private StackNode<I> next;
	
	public StackNode(I data) {
		this.data = data;
	}

	public I getData() {
		return data;
	}

	public void setData(I data) {
		this.data = data;
	}

	public StackNode<I> getNext() {
		return next;
	}

	public void setNext(StackNode<I> next) {
		this.next = next;
	}
}
