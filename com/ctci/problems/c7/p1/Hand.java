package com.ctci.problems.c7.p1;

import java.util.List;

public class Hand<T extends Card> {
	protected List<T> cards;
	
	public Hand(List<T> cards) {
		this.cards = cards;
	}
	
	public List<T> getHand() {
		return cards;
	}
	
	public int score() {
		int score = 0;
		for (T card : cards) {
			score += card.getValue();
		}
		return score;
	}
	
	public void addCard(T card) {
		cards.add(card);
	}
	
	@Override
	public String toString() {
		if(cards==null||cards.isEmpty()) {
			return "Empty Hand";
		}
		StringBuilder sb = new StringBuilder();
		for(Card c : cards) {
			sb.append(c).append('\n');
		}
		return sb.toString();
	}
}
