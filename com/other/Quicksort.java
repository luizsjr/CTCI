package com.other;

import java.util.Arrays;

public class Quicksort {
	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length-1);
	}
	
	protected static void quickSort(int[] arr, int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1) { // Sort left half
			quickSort(arr, left, index - 1);
		}
		if (index < right) { // Sort right half
			quickSort(arr, index, right);
		}
	}

	protected static int partition(int[] arr, int left, int right) {
		int pivot = arr[(left + right) / 2]; // Pick pivot point
		while (left <= right) {
		
			// Find element on left that should be on right
			while (arr[left] < pivot) {
				left++;
			}
		
			// Find element on right that should be on left
			while (arr[right] > pivot) {
				right--;
			}
		
			// Swap elements, and move left and right indices
			if (left <= right) {
				swap(arr, left, right); // swaps elements
				left++;
				right--;
			}
		}
		return left;
	}
	
	protected static void swap(int[] arr, int left, int right) {
		if (left!=right) {
			int buff = arr[left];
			arr[left] = arr[right];
			arr[right] = buff;
		}
	}
	
	public static void main(String[] args) {
		int[] a = {8,2,4,9,10,5,1};
//		int[] a = {0,1,2,3,4,5,6};
		System.out.println(Arrays.toString(a));
		quickSort(a);
		System.out.println(Arrays.toString(a));
	}
}
