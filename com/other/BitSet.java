package com.other;

public class BitSet {
	
	private static final int INT_SIZE = 32;
	
	private int size;
	private int[] values;
	
	public BitSet(int size) {
		this.size=size;
		values = new int[size/INT_SIZE + 1]; // an integer has 32 bits, so we need size/32 positions
	}
	
	public void setBit(int bit) {
		validateBit(bit);
		int w = getWordNumber(bit);
		int b = getBitNumber(bit);
		values[w] |= 1 << b;
	}
	
	public void unsetBit(int bit) {
		validateBit(bit);
		int w = getWordNumber(bit);
		int b = getBitNumber(bit);
		values[w] ^= 1 << b;
	}
	
	public boolean isBitSet(int bit) {
		validateBit(bit);
		int w = getWordNumber(bit);
		int b = getBitNumber(bit);
		return (values[w] & 1<<b) != 0;
	}
	
	private int getWordNumber(int bit) {
		return (bit-1) / INT_SIZE; // bits start index is 1, array is 0
	}
	
	private int getBitNumber(int bit) {
		return (bit-1) % INT_SIZE;
	}
	
	private void validateBit(int bit) {
		if (bit <=0 || bit>this.size) {
			throw new IndexOutOfBoundsException("Index out of Bounds: "+bit);
		}
	}
	
	public static void main(String[] args) {
		BitSet bs = new BitSet(32);
		System.out.println(bs.isBitSet(1)); // false
		bs.setBit(1);
		System.out.println(bs.isBitSet(1)); // true
		bs.unsetBit(1);
		System.out.println(bs.isBitSet(1)); // false
		System.out.println(bs.isBitSet(32)); // false
		bs.setBit(32);
		System.out.println(bs.isBitSet(32)); // true
		bs.unsetBit(32);
		System.out.println(bs.isBitSet(32)); // false
		System.out.println(bs.isBitSet(33)); // Exception
	}

}
