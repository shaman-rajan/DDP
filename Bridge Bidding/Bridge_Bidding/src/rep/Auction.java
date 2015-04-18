package rep;

public class Auction {
	private Deal deal;
	private BidHistory bid_history;
	
	public Auction(Deal d, String dealer) {
		deal = d;
		bid_history = new BidHistory(dealer);
	}
	
	public int getNextTurn() {
		return this.bid_history.getTurn();
	}

	public Bid askForNextBid() {
		int nextPos = this.getNextTurn();
		Player nextPlayer = this.deal.getPlayer(nextPos);
		Bid bid = nextPlayer.askForBid();
		this.makeBid(nextPos, bid);
		System.out.println(Deal.getPosition(nextPos) + " made bid " + bid);
		System.out.println();
		System.out.println();
		
		return bid;
	}

	public boolean makeBid(int player, Bid bid) {
		if(player == this.getNextTurn()) {
			this.bid_history.addBid(bid);
			return true;
		} else return false;
	}

	public Deal getDeal() {
		return this.deal;
	}
	
	public BidHistory getBidHistory() {
		return this.bid_history;
	}
}
