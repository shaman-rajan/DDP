package rep;

public class Auction {
	private Deal deal;
	private BidHistory bid_history;
	
	public Auction(Deal d) {
		deal = d;
		bid_history = new BidHistory(this.deal.getDealer());
	}
	
	public int getNextTurn() {
		return this.bid_history.getTurn();
	}

	public void askForNextBid() {
		int nextPos = this.getNextTurn();
		Player nextPlayer = this.deal.getPlayer(nextPos);
		Bid bid = nextPlayer.askForBid();
		this.makeBid(nextPos, bid);
		System.out.println(Deal.getPosition(nextPos) + " made bid " + bid);
	}

	// TODO: Think about who will call this function and from where
	public boolean makeBid(int player, Bid bid) {
		if(player == this.getNextTurn()) {
			this.addInfoFromBid(player, bid);
			this.bid_history.addBid(bid);
			return true;
		} else return false;
	}

	private void addInfoFromBid(int player, Bid bid) {
		// TODO: Infer information from newly made bid
	}
	
	public Deal getDeal() {
		return this.deal;
	}
	
	public BidHistory getBidHistory() {
		return this.bid_history;
	}
}
