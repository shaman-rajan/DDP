package rep;

public class Bid implements Comparable<Bid> {
	
	public enum BidSuit {
		CLUB, DIAMOND, HEART, SPADE, NOTRUMP
	}
	
	public enum BidValue {
		ONE (1), TWO (2), THREE (3), FOUR (4), FIVE (5), SIX (6), SEVEN (7);
		
		private final int level;
		BidValue(int lev) {
				this.level = lev;
		}
		
		public int getLevel() {
			return this.level;
		}
	}
	
	private BidSuit bidSuit;
	private BidValue bidValue;
	
	public Bid(BidSuit s, BidValue v) {
		bidSuit = s;
		bidValue = v;
	}
	
	@Override
	public int compareTo(Bid bid) {
		int temp_BidSuit = this.bidSuit.compareTo(bid.bidSuit);
		if(this.bidValue.level < bid.bidValue.level)
			return -1;
		else if(this.bidValue == bid.bidValue && temp_BidSuit == -1)
			return -1;
		else if(this.bidValue == bid.bidValue && temp_BidSuit == 0)
			return 0;
		else return 1;
	}
	
	public BidSuit getBidSuit() {
		return this.bidSuit;
	}
	
	public BidValue getValue() {
		return this.bidValue;
	}
	
	public String toString() {
		char s_BidSuit = this.bidSuit.toString().charAt(0);
		return s_BidSuit + "" + this.bidValue;
	}
	
}
