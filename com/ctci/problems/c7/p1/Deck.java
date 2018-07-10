package com.ctci.problems.c7.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck<T extends Card> {
	
	protected List<T> cards;
	protected int nextCard; // next card to be dealt
	
	public Deck(List<T> cards) {
		this.cards = cards;
		nextCard = 0;
	}
	
	public void shuffleDeck() {
		List<T> shuffledDeck = new ArrayList<>(cards.size());
		Random rand = new Random();
		for(int i=cards.size(); i>0; i--) {
			int cardInd = rand.nextInt(i);
			T card = cards.remove(cardInd);
			card.setAvailable(true);
			shuffledDeck.add(card);
		}
		cards=shuffledDeck;
		nextCard=0;
	}
	
	public Hand<T> dealHand(int numCards) {
		List<T> handCards = new ArrayList<>(numCards);
		for(int i=0; i<numCards; i++) {
			handCards.add(dealCard());
		}
		return new Hand<T>(handCards);
	}
	
	public T dealCard() {
		if (getCardAvailable()==0) {
			return null;
		}
		T card = cards.get(nextCard++);
		card.setAvailable(false);
		return card;
	}
	
	public int getCardAvailable() {
		return cards.size()-nextCard;
	}
}
