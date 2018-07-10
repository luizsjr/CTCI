package com.ctci.problems.c7.p1;

import java.util.List;

public class BlackJackHand extends Hand<BlackJackCard> {

	public static final int BLACKJACK = 21;
	
	public BlackJackHand(List<BlackJackCard> cards) {
		super(cards);
	}
	
	@Override
	public int score() {
		boolean hasAces=false;
		int score = 0;
		for (BlackJackCard card : cards) {
			if (card.getValue()==1) { hasAces=true; }
			score += card.getValue();
		}
		// In case Hand has Aces, check if it can be used as 11
		if (hasAces && score+10<=BLACKJACK) {
			score+=10;
		}
		return score;
	}
}
