package rep;

public class Bid implements Comparable<Bid> {
	
	public enum BidSuit {
		CLUB, DIAMOND, HEART, SPADE, NOTRUMP
	}
	
	private BidSuit BidSuit;
	private int value;
	
	public Bid(BidSuit s, int v) {
		BidSuit = s;
		if(v <= 7) value = v;
	}
	
	@Override
	public int compareTo(Bid bid) {
		int temp_BidSuit = this.BidSuit.compareTo(bid.BidSuit);
		if(this.value < bid.value)
			return -1;
		else if(this.value == bid.value && temp_BidSuit == -1)
			return -1;
		else if(this.value == bid.value && temp_BidSuit == 0)
			return 0;
		else return 1;
	}
	
	public BidSuit getBidSuit() {
		return this.BidSuit;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String toString() {
		char s_BidSuit = this.BidSuit.toString().charAt(0);
		return s_BidSuit + "" + this.value;
	}
	
}
