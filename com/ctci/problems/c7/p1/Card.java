package com.ctci.problems.c7.p1;

import java.text.MessageFormat;

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
	
	@Override
	public String toString() {
		String value;
		switch (faceValue) {
			case 1 : value = "Ace";
			break;
			case 11: value = "J";
			break;
			case 12: value = "Q";
			break;
			case 13: value = "K";
			break;
			default: value = ""+faceValue;
			break;
		}
		return MessageFormat.format("{0} of {1}s", value, suitValue.toString());
	}
	
	public abstract int getValue();
}
