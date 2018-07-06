package com.ctci.problems.c5;

public class P8 {
	
	//Assuming x1, x2 and y starts at 0; x1 and x2 are points included in the line
	public void drawLine(byte[] screen, int w, int x1, int x2, int y) {
		int ix1 = getXIndex(w, y, x1);
		int ix2 = getXIndex(w, y, x2);
		
		screen[ix1]= (byte) ~(-1 << Byte.SIZE - x1%Byte.SIZE);
		byte ix2Value= (byte) (-1 << Byte.SIZE - x2%Byte.SIZE - 1);

		//x1 and x2 are in the same byte
		if (ix1==ix2) {
			screen[ix1]&=ix2Value;
			return;
		}
		
		//x1 and x2 are in different bytes
		for(int i=ix1+1; i<ix2; i++) {
			screen[i]=-1;
		}
		screen[ix2]=ix2Value;
	}
	
	protected int getLineStartIndex(int w, int y) {
		return y * getLineSizeInBytes(w);
	}
	
	protected int getLineSizeInBytes(int w) {
		return w/Byte.SIZE;
	}
	
	protected int getXIndex(int w, int y, int x) {
		return getLineStartIndex(w, y) + x/Byte.SIZE;
	}

	public static void main(String[] args) {
		P8 problem = new P8();
		byte[] screen = problem.generateScreen(32, 20);
		problem.drawLine(screen, 32, 2, 30, 5);
		problem.printLine(screen, 32, 5);
	}
	
	public byte[] generateScreen(int w, int h) {
		return new byte[ getLineSizeInBytes(w)*h ];
	}
	
	public void printLine(byte[] screen, int w, int y) {
		int lineStartIndex = getLineStartIndex(w, y);
		int lineSizeInBytes = getLineSizeInBytes(w);
		for(int i=lineStartIndex; i < lineStartIndex+lineSizeInBytes; i++) {
			System.out.print(screen[i] + " ");
		}
	}

}
