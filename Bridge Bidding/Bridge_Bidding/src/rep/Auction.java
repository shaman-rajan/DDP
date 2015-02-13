package rep;

public class Auction {
	private Deal deal;
	private BidHistory bid_history;
	
	public Auction(Deal d) {
		deal = d;
		bid_history = new BidHistory(this.deal.getDealer());
	}
	
	public Deal getDeal() {
		return this.deal;
	}
	
	public BidHistory getBidHistory() {
		return this.bid_history;
	}
}
