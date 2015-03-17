package rep;

public class Card implements Comparable<Card> {
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
	
	public String toString() {
		return this.suit.toString() + " " + this.value.toString();
	}

	@Override
	public int compareTo(Card other) {
		if(this.suit.ordinal() < other.suit.ordinal()) return -1;
		else if(this.suit.ordinal() > other.suit.ordinal()) return 1;
		else {
			if(this.value.ordinal() < other.value.ordinal()) return -1;
			else if(this.value.ordinal() > this.value.ordinal()) return 1;
			else return 0;
		}
	}
}
