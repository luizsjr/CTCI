package com.ctci.problems.c10;

public class P3 {
	
	public static int findInRotated(int item, int[] array) {
		if (array==null || array.length==0) { return -1; }
		return findInRotated(item, array, 0, array.length-1);
	}
	
	public static int findInRotated(int item, int[] array, int left, int right) {
		if (left > right) { return -1; }

		int mid=(left+right)/2;
		if (array[mid]==item) {
			return mid; // found item!
		}
		
		if (array[mid]==array[left]) {
			if (array[mid]<array[right]) {
				// left part is all equal and right half is ordered; search right 
				return findInRotated(item, array, mid+1, right);
			} else {
				// cannot tell if array is all equal or which side is ordered, search both sides
				int search = findInRotated(item, array, left, mid-1);
				if (search==-1) {
					search = findInRotated(item, array, mid+1, right);
				}
				return search;
			}
		} else if ( (array[mid]>array[left] && item < array[mid]) || // (left half is ordered AND item is there) OR
					(array[mid]<array[right] && item > array[right])) // (right half is ordered and item is not there) 
		{
			return findInRotated(item, array, left, mid-1); // search in left half
		} else {	
			return findInRotated(item, array, mid+1, right); //search in right half
		}
	}
	
	public static void main(String[] args) {
		int[] array = {15,16,19,20,25,1,3,4,5,7,10,14};
		System.out.println(findInRotated(5, array)); //8
		System.out.println(findInRotated(19, array)); //2
		System.out.println(findInRotated(17, array)); //-1
		int[] array2 = {2,2,2,3,4,5};
		System.out.println(findInRotated(3, array2)); //3
		int[] array3 = {2,3,2,2,2,2};
		System.out.println(findInRotated(3, array3)); //1
	}
}
