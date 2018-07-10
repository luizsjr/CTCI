package com.ctci.problems.c7.p1;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJackGame {
	
	Deck<BlackJackCard> deck;
	Hand<BlackJackCard> hand;
	
	public BlackJackGame() {
		initDeck();
	}
	
	protected final void initDeck() {
		List<BlackJackCard> cards = new ArrayList<>(52);
		for(Suit s : Suit.values()) {
			for(int v=1; v<=13; v++) {
				BlackJackCard c = new BlackJackCard(v, s);
				cards.add(c);
			}
		}
		deck=new Deck<>(cards);
		deck.shuffleDeck();
	}
	
	public void play() {
		Scanner sc=new Scanner(System.in);
		do {
			reShuffleIf(2);
			hand = new BlackJackHand( deck.dealHand(2) );
			while (!isGameOver() && confirm("Continue?", sc)) {
				reShuffleIf(1);
				hand.addCard(deck.dealCard());
			}
		} while(confirm("Keep Playing?",sc));
		System.out.println("bye...");
		sc.close();
	}
	
	protected boolean confirm(String message, Scanner sc) {
		String input=null;
		while (!"Y".equals(input) && !"N".equals(input)) {
			System.out.print(message+" (Y/N)");
			input = sc.next().toUpperCase();
			System.out.println("");
		}
		return "Y".equals(input);
	}
	
	protected void reShuffleIf(int minCards) {
		if(deck.getCardsAvailable()<minCards) {
			System.out.println("Not enough Cards in Deck. Re-shuffling...");
			deck.shuffleDeck();
		}
	}
	
	protected boolean isGameOver() {
		System.out.println(hand);
		int score = hand.score();
		System.out.println( MessageFormat.format("score: {0}", score) );
		if (score==BlackJackHand.BLACKJACK) {
			System.out.println("Blackjack! You Win!");
			return true;
		}
		if (score>BlackJackHand.BLACKJACK) {
			System.out.println("You Burst! Game Over!");
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		BlackJackGame bj = new BlackJackGame();
		bj.play();
	}

}
