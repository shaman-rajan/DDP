package rep;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards;
	
	public Hand(Card c) {
		cards = new ArrayList<>(13);
		cards.add(c);
	}
	
	public Hand(ArrayList<Card> cl) {
		cards = new ArrayList<>(13);
		if(cl.size() <= 13)
			cards.addAll(cl);
		if(cards.size() == 13)
			calculateFeatureValues();
	}
	
	public void addCard(Card c) {
		if(cards.size() < 13) {
			cards.add(c);
			if(cards.size() == 13)
				calculateFeatureValues();
		}
		else throw new ArrayIndexOutOfBoundsException(13);
	}
	
	public boolean isComplete() {
		if(cards.size() == 13) return true;
		else return false;
	}
	
	/* 
	 * Calculate features and set
	 */
	
	// Features
	private int points_hc;
	private int num_spade, num_heart, num_diamond, num_club;
	
	// Calculation function
	private void calculateFeatureValues() {
		
		/*
		 * Initialization
		 */
		points_hc = 0;
		num_spade = num_heart = num_diamond = num_club = 0;
		
		/*
		 *  HC Points
		 */
		for(Card c : cards) {
			switch (c.getValue()) {
			case ACE:
				points_hc += 4;
				break;
			case KING:
				points_hc += 3;
				break;
			case QUEEN:
				points_hc += 2;
				break;
			case JACK:
				points_hc += 1;
				break;
			default:
				break;
			}
		}
		
		/*
		 * Number of cards in each suit
		 */
		for(Card c : cards) {
			switch(c.getSuit()) {
			case SPADE:
				num_spade++;
				break;
			case HEART:
				num_heart++;
				break;
			case DIAMOND:
				num_diamond++;
				break;
			case CLUB:
				num_club++;
				break;
			}
		}
	}
	
	/*
	 *  Functions to get feature values
	 */
	public int getHCPoints() {
		return points_hc;
	}
	
	public int getNumSpades() {
		return num_spade;
	}
	
	public int getNumHearts() {
		return num_heart;
	}
	
	public int getNumDiamonds() {
		return num_diamond;
	}
	
	public int getNumClubs() {
		return num_club;
	}
}
