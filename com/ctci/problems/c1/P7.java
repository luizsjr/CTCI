package com.ctci.problems.c1;

public class P7 {

	private int[][] m;
	
	public P7(int mSize) {
		initMatrix(mSize);
	}
	
	public void initMatrix(int mSize) {
		
		//Assume max size = 9
		if (mSize > 9) mSize=9;
		
		m = new int[mSize][mSize];
		for(int r=0; r < mSize; r++) {
			for(int c=0; c < mSize; c++) {
				m[r][c] = (r+1)*10 + (c+1);
			}
		}
	}
	
	public void printMatrix() {
		StringBuilder aux = new StringBuilder();
		for (int r=0; r < m.length; r++) {
			for (int c=0; c < m[0].length; c++) {
				aux.append(m[r][c]);
				aux.append(" ");
			}
			aux.append("\n");
		}
		System.out.println(aux);
	}
	
	// O( n^2 )
	public boolean rotateMatrixClockWise() {
		
		if(m.length == 0 || m.length != m[0].length) return false;
		
		for (int layer=0; layer < m.length/2; layer++) {
			rotateLayerClockWise(layer);
		}
		return true;
	}
	
	private void rotateLayerClockWise(int layer) {
		int j = m.length-layer-1;
		for (int i=layer; i<j; i++) {
			int aux = m[layer][i];
			aux = setPositionValue(aux, i, j);
			aux = setPositionValue(aux, j, j-i+layer);
			aux = setPositionValue(aux, j-i+layer, layer);
			aux = setPositionValue(aux, layer, i);
		}	
	}
	
	private int setPositionValue(int newValue, int r, int c) {
		int savedValue = m[r][c];
		m[r][c] = newValue;
		return savedValue;
	}
	
	public static void main(String[] args) {
		int mSize = 6;
		P7 problem = new P7(mSize);
		problem.printMatrix();
		if ( problem.rotateMatrixClockWise() ) {
			problem.printMatrix();
		} else {
			System.out.println("cannot rotate m");
		}
	}
}
