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
			calculate();
	}
	
	public void addCard(Card c) {
		if(cards.size() < 13) {
			cards.add(c);
			if(cards.size() == 13)
				calculate();
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
	
	// Calculation function
	private void calculate() {
		
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
	}
	
	/*
	 *  Functions to get feature values
	 */
	public int getHCPoints() {
		return points_hc;
	}
}
