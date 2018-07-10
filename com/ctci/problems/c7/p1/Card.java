package com.ctci.problems.c7.p1;

public abstract class Card {
	protected int faceValue; // joker is 0, ace is 1, J is 11, Q is 12, K is 13
	protected Suit suitValue;
	protected boolean available;
	
	public Card(int face, Suit suit) {
		faceValue = face;
		suitValue = suit;
		available = true;
	}
	
	public int getFace() {
		return faceValue;
	}
	
	public Suit getSuit() {
		return suitValue;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean isAvailable) {
		available = isAvailable;
	}
	
	public abstract int getValue();
}
