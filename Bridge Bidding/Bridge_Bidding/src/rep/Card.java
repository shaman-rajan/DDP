package rep;

public class Card implements Comparable<Card> {
	public enum Suit {
		CLUB, DIAMOND, HEART, SPADE
	}
	
	public enum Value {
		TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'),
		EIGHT('8'), NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING('K'), ACE('A');
		
		private final char rank;
		Value(char r) {
			this.rank = r;
		}
		
		public char getRank() {
			return this.rank;
		}
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
