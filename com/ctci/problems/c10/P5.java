package com.ctci.problems.c10;

public class P5 {
	
	public static int searchIgnoringSpaces(String str, String[] l) {
		if (str != null && !str.isEmpty() && l!=null && l.length>0 ) {
			int left=0;
			int right=l.length-1;
			while(left<=right) {
				int mid = getNonEmptyMid(left, right, l);
				if (mid<0) {
					return mid;
				}
				int comp = l[mid].compareTo(str);
				if (comp==0) {
					return mid;
				} else if (comp<0) {
					left=mid+1;
				} else {
					right=mid-1;
				}
			}
		}
		return -1;
	}
	
	protected static int getNonEmptyMid(int left, int right, String[] l) {
		int mid = (left+right)/2;
		int midLeft = mid;
		int midRight = mid+1;
		while (midLeft>=left || midRight<=right) {
			if (midLeft>=left && !l[midLeft].isEmpty()) {
				return midLeft;
			} else if (midRight>=right && !l[midRight].isEmpty()) {
				return midRight;
			}
			midLeft--;
			midRight++;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String[] l = {"at","","","","ball","","","car","","","dad", "",""};
		System.out.println(searchIgnoringSpaces("ball" , l)); // 4
		System.out.println(searchIgnoringSpaces("at" , l)); // 0
		System.out.println(searchIgnoringSpaces("dad" , l)); // 10
		
		String[] l2 = {"", "", "at","","","","ball","","","car","","","dad", "","", "train"};
		System.out.println(searchIgnoringSpaces("ball" , l2)); // 6
		System.out.println(searchIgnoringSpaces("at" , l2)); // 2
		System.out.println(searchIgnoringSpaces("dad" , l2)); // 12
		System.out.println(searchIgnoringSpaces("train" , l2)); // 15
	}
}
