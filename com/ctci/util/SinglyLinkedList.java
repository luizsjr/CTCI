package com.ctci.util;

public class SinglyLinkedList<I> {
	
	private ListNode<I> first;
	private ListNode<I> last;
	private ListNode<I> current;
	
	public SinglyLinkedList(){
		first = null;
		last = null;
		current = null;
	}
	
	public void add(I value) {
		ListNode<I> node = new ListNode<I>(value);
		if (first == null) {
			first = node;
			last = node;
			current = node;
		} else {
			last.setNext(node);
			last = node;
		}
	}
	
	public boolean hasNext() {
		return current != null;
	}
	
	public I next() {
		if (current!=null) {
			I value = current.getData();
			current = current.getNext();
			return value;
		}
		return null;
	}
	
	public void resetCurrent() {
		current = first;
	}
	
	public boolean remove(I value) {
		if(first != null) {
			// Check if I is the first item
			if(first.getData().equals(value)) {
				if (first == current) current = current.getNext();
				if (first == last) last = last.getNext();
				first = first.getNext();
				return true;
			}
			// Find the item
			ListNode<I> previous = findPrevious(value);
			if(previous != null) {
				ListNode<I> node = previous.getNext();
				if (last == node) last = previous;
				if (current == node) current = node.getNext();
				previous.setNext(node.getNext());
				return true;
			}
		}
		// List is Empty or Value Not Found
		return false;
	}
	
	protected ListNode<I> findPrevious(I value) {
		ListNode<I> node = first;
		while (node!=null) {
			ListNode<I> nodeNext = node.getNext();
			if ( nodeNext!=null && 
				 nodeNext.getData()!=null && 
				 nodeNext.getData().equals(value) ) return node;
			node = node.getNext();
		}
		return node;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ListNode<I> node = first;
		while (node!=null) {
			sb.append(node.getData() + " ");
			node = node.getNext();
		}
		return sb.length() == 0 ? null : sb.toString();
	}
	
	public static void main(String[] args) {
		SinglyLinkedList<Character> list = new SinglyLinkedList<>();
		System.out.println(list.toString()); //null
		list.add('a');
		list.add('b');
		list.add('c');
		list.add('d');
		list.add('e');
		System.out.println(list.toString()); // a b c d e

		while(list.hasNext()) {
			System.out.println(list.next()); // a to e
		}
		System.out.println(list.next()); // null
		
		list.resetCurrent();
		System.out.println(list.next()); //a
		list.resetCurrent();
		System.out.println(list.remove('x')); //false;
		list.remove('a');
		System.out.println(list.next()); //b
		list.remove('c');
		while(list.hasNext()) {
			System.out.println(list.next()); // d, e
		}
		System.out.println(list.toString()); // b d e
		list.remove('e');
		System.out.println(list.next()); //null
		System.out.println(list.toString()); //b d
		list.remove('d');
		System.out.println(list.toString()); //b	
		list.remove('b');
		System.out.println(list.toString()); //null	
	}
}
