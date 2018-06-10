package com.ctci.problems.c1;

public class P8 {

	private int[][] m;
	private boolean[] rowsToZero;
	private boolean[] colsToZero;
	
	public P8(int[][] m) {
		assert (m == null || m.length == 0 || m[0].length==0);
		this.m=m;
		rowsToZero = new boolean[m.length];
		colsToZero = new boolean[m[0].length];
	}
	
	public void printMatrix() {
		StringBuilder sb = new StringBuilder();
		for (int r=0; r < m.length; r++) {
			for (int c=0; c < m[0].length; c++) {	
				sb.append(m[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	protected void findZeros() {
		for (int r=0; r < m.length; r++) {
			for (int c=0; c < m[0].length; c++) {
				if(m[r][c] == 0) {
					rowsToZero[r] = true;
					colsToZero[c] = true;
				}
			}
		}
	}
	
	protected void zeroRows() {
		for (int r=0; r<rowsToZero.length; r++) {
			if(rowsToZero[r]) this.zeroRow(r);
		}
	}
	
	protected void zeroCols() {
		for (int c=0; c < colsToZero.length; c++) {
			if(colsToZero[c]) this.zeroColumn(c);
		}
	}
	
	protected void zeroRow(int r) {
		for (int c=0; c < m[0].length; c++) {
			m[r][c] = 0;
		}	
	}
	
	protected void zeroColumn(int c) {
		for (int r=0; r < m.length; r++) {
			m[r][c] = 0;
		}	
	}
	
	// O (N*M)
	public void zeroMatrix() {
		this.findZeros();
		this.zeroRows();
		this.zeroCols();
	}
	
	
	public static void main(String[] args) {
		int[][] m = {
				{1,0,1,1,1,0},
				{1,0,1,1,1,1},
				{1,1,1,1,1,1},
				{1,1,1,1,1,1}
		};
		
		P8 problem = new P8(m);
		problem.printMatrix();
		problem.zeroMatrix();
		problem.printMatrix();
	}

}
