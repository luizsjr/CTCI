package com.ctci.problems.c8;

public class P10 {
	
	public boolean paintFill(int[][] display, int r, int c, int newColor) {
		if (display==null || display.length==0 || display[0].length==0 || r<0 || r>=display.length || c<0 || c>=display[0].length || newColor<0) {
			throw new RuntimeException("Invalid Arguments");
		}
		if (display[r][c]==newColor) {
			return false;
		}
		paintFill(display, r, c, display[r][c], newColor);
		return true;
	}
	
	private void paintFill(int[][] display, int r, int c, int originalColor, int newColor) {
		//Check if coordinate is valid and point has the original color
		if (r>=0 && r<display.length && c>=0 && c<display[0].length && display[r][c]==originalColor) {
			display[r][c]=newColor;
			
			//recurse adjacent points
			paintFill(display, r-1, c, originalColor, newColor); //up
			paintFill(display, r, c-1, originalColor, newColor); //left
			paintFill(display, r, c+1, originalColor, newColor); //right
			paintFill(display, r+1, c, originalColor, newColor); //down
		}
	}
	
	public void printScreen(int[][] screen) {
		for (int r = 0; r < screen.length; r++) {
			for (int c = 0; c < screen[0].length; c++) {
				System.out.print(screen[r][c]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		P10 problem = new P10();
		
		int[][] display = new int[10][10];
		display[2][3] = 1;
		display[2][4] = 1;
		display[7][8] = 1;
		display[8][8] = 1;
		display[9][8] = 1;
		
		problem.printScreen(display);
		System.out.println();
		problem.paintFill(display, 0, 0, 8);
		problem.printScreen(display);
		
		System.out.println();
		problem.paintFill(display, 8, 8, 0);
		problem.printScreen(display);
		
	}
}
