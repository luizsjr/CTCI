package com.ctci.util;

public class DoublyLinkedList<I> {
	
	private ListNode<I> first;
	private ListNode<I> last;
	private ListNode<I> current;
	
	public void add(I value) {
		ListNode<I> node = new ListNode<>(value);
		if (first == null) {
			first = node;
			last = node;
			current = node;
		} else {
			node.setPrevious(last);
			last.setNext(node);
			last = node;
		}
	}
	
	public boolean hasNext() {
		return current != null;
	}
	
	public I next() {
		I value = null;
		if(current != null) {
			value = current.getData();
			current = current.getNext();
		}
		return value;			
	}
	
	public void resetCurrent() {
		current = first;
	}
	
	public boolean remove(I value) {
		ListNode<I> node = find(value);
		if (node != null) {
			ListNode<I> previous = node.getPrevious();
			ListNode<I> next = node.getNext();
			
			if (node==first) first = next;
			if (node==last) last = previous; 
			if (node==current) current = next;
			if(previous!=null) previous.setNext(node.getNext());
			if(next!=null) next.setPrevious(node.getPrevious());
			return true;
		}
		return false;
	}
	
	protected ListNode<I> find(I value) {
		ListNode<I> node = first;
		while( node!=null) {
			if(node.getData()!=null && node.getData().equals(value)) return node;
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
