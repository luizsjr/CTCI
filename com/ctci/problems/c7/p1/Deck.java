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
	
	public List<T> dealHand(int numCards) {
		List<T> hand = new ArrayList<>(numCards);
		for(int i=0; i<numCards; i++) {
			T c = dealCard();
			if(c!=null) { hand.add(c); }
		}
		return hand;
	}
	
	public T dealCard() {
		if (getCardsAvailable()==0) {
			return null;
		}
		T card = cards.get(nextCard++);
		card.setAvailable(false);
		return card;
	}
	
	public int getCardsAvailable() {
		return cards.size()-nextCard;
	}
}
