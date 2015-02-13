package rep;

public class Card {
	public enum Suit {
		CLUB, DIAMOND, HEART, SPADE
	}
	
	public enum Value {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, 
		EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
	}
	
	private Suit suit;
	private Value value;
	
	public Card(Suit s, Value v) {
		this.suit = s;
		this.value = v;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
	public Value getValue() {
		return this.value;
	}
}
