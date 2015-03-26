package rep;

public class Bid implements Comparable<Bid> {
	
	public boolean pass;
	public boolean dbl;
	public boolean redbl;
	
	public enum BidSuit {
		CLUB, DIAMOND, HEART, SPADE, NOTRUMP, NONE
	}
	
	public enum BidValue {
		ONE (1), TWO (2), THREE (3), FOUR (4), FIVE (5), SIX (6), SEVEN (7), NONE(0);
		
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
	
	/*
	 * Send string in uppercase letters
	 */
	public Bid(String bid) {
		this.pass = false;
		this.dbl = false;
		this.redbl = false;
		if(bid.length() == 2) {
			int bidval = bid.charAt(0) - '1';
			char bidsuit = bid.charAt(1);
			BidValue valueArray[] = BidValue.values();
			this.bidSuit = bidsuit == 'S' ? BidSuit.SPADE : 
						(bidsuit == 'H' ? BidSuit.HEART : 
						(bidsuit == 'D' ? BidSuit.DIAMOND :
						(bidsuit == 'C' ? BidSuit.CLUB : 
					     BidSuit.NOTRUMP)));
			this.bidValue = valueArray[bidval];
		} else {
			this.bidSuit = BidSuit.NONE;
			this.bidValue = BidValue.NONE;
			if(bid.equals("PASS")) this.pass = true;
			else if(bid.equals("DBL")) this.dbl = true;
			else if(bid.equals("redbl")) this.redbl = true;
		}
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
		if(this.pass) return "PASS";
		if(this.dbl) return "DBL";
		if(this.redbl) return "REDBL";
		char s_BidSuit = this.bidSuit.toString().charAt(0);
		return this.bidValue.getLevel() + "" + s_BidSuit;
	}
	
}
