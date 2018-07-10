package com.ctci.problems.c7.p1;

public class BlackJackCard extends Card {

	public BlackJackCard(int face, Suit suit) {
		super(face, suit);
	}
	
	@Override
	public int getValue() {
		if (this.faceValue > 10 && faceValue <= 13) {
			return 10;
		}
		return faceValue;
	}
}
