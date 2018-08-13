package com.ctci.problems.c8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P12 {

	public List<Integer[]> placeQueens(int size) {
		List<Integer[]> results = new ArrayList<>();
		Integer[] columns = new Integer[size];
		Arrays.fill(columns, -1);
		placeQueens(0, columns, results);
		return results;
	}

	private void placeQueens(int row, Integer[] columns, List<Integer[]> results) {
		if (columns.length==row) { // Found valid placement
			results.add(columns.clone());
		} else {
			for (int col=0; col<columns.length; col++) {
				if (checkValid(columns, row, col)) {
					columns[row]=col; // Place queen
					placeQueens(row+1, columns, results); // Work on next row
				}
			}
		}
	}

	/*
	 * Check if (row1, column1) is a valid spot for a queen by checking if there is
	 * a queen in the same column or diagonal. We don't need to check it for queens
	 * in the same row because the calling placeQueen only attempts to place one
	 * queen at a time. We know this row is empty.
	 */
	private boolean checkValid(Integer[] columns, int row1, int column1) {
		for (int row2 = 0; row2 < row1; row2++) {
			int column2 = columns[row2];
			/* Check if (row2, column2) invalidates (row1, column1) as a queen spot. */

			/* Check if rows have a queen in the same column */
			if (column1 == column2) {
				return false;
			}

			/*
			 * Check diagonals: if the distance between the columns equals the distance
			 * between the rows, then they’re in the same diagonal.
			 */
			int columnDistance = Math.abs(column2 - column1);
			int rowDistance = row1 - row2; // row1 > row2, so no need to use absolute value
			if (columnDistance == rowDistance) {
				return false;
			}
		}
		return true;
	}

	public static void clear(Integer[] columns) {
		for (int i = 0; i < columns.length; i++) {
			columns[i] = -1;
		}
	}
	
	public static void printBoards(List<Integer[]> boards) {
		for (int i = 0; i < boards.size(); i++) {
			Integer[] board = boards.get(i);
			printBoard(board);
		}
	}

	public static void printBoard(Integer[] columns) {
		drawLine(columns.length);
		for (int i = 0; i < columns.length; i++) {
			System.out.print("|");
			for (int j = 0; j < columns.length; j++) {
				if (columns[i] == j) {
					System.out.print("Q|");
				} else {
					System.out.print(" |");
				}
			}
			System.out.print("\n");
			drawLine(columns.length);
		}
		System.out.println("");
	}

	private static void drawLine(int size) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < size * 2 + 1; i++)
			line.append('-');
		System.out.println(line.toString());
	}
	
	public static void main(String[] args) {
		P12 problem = new P12();
		List<Integer[]> results = problem.placeQueens(8);
		printBoards(results);
		System.out.println(results.size());
	}
}
