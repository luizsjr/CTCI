package com.ctci.problems.c10;

public class P4 {
	
	public static class Listy {
		int[] values;
		
		public Listy(int...vals) {
			values = vals;
		}
		
		public int elementAt(int x) {
			if(x >= values.length) {
				return -1;
			} else {
				return values[x];
			}
		}
	}
	
	public static int listySearch(int x, Listy l) {
		int right=0;
		int left=0;
		do {
			int value = l.elementAt(right);
			if (value==x) {
				return right;
			} else if (value==-1 || value>=x) {
				right = (left+right)/2;
			} else {
				int aux=right;
				right=right==0?1:right*2;
				left=aux;
			}
		} while (left<right);
		return -1;
	}
	
	public static void main(String[] args) {
		Listy l = new Listy(1,2,3,6,7);
		System.out.println(listySearch(1, l)); // 0
		System.out.println(listySearch(7, l)); // 4
		System.out.println(listySearch(6, l)); // 3
		System.out.println(listySearch(2, l)); // 1
		System.out.println(listySearch(3, l)); // 2
		System.out.println(listySearch(0, l)); // -1
		System.out.println(listySearch(8, l)); // -1
		System.out.println(listySearch(4, l)); // -1
	}

}
