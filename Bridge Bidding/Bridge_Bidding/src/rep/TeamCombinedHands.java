package rep;

import rep.Bid.BidSuit;

public class TeamCombinedHands {
	private Hand hand1, hand2;
	
	/*
	 * Features of combined hand
	 */
	public int points_hc;
	
	public BidSuit trumpDecided;
	
	public boolean gameForced;
	
	/*
	 * Construct and initialize with impossible value
	 */
	public TeamCombinedHands(Hand h1, Hand h2) {
		hand1 = h1;
		hand2 = h2;
		
		points_hc = -1;
		
		this.calculateFeatureValues();
	}
	
	public boolean calculateFeatureValues() {
		if(this.hand1.isComplete() && this.hand2.isComplete()) {
			
			
			return true;
		} else return false;
	}
	
	/*
	 * Function to return features
	 * Calculate values within these functions (if uncalculated)
	 */
	public int getHCPoins() throws Exception {
		if(points_hc < 0) {
			if(hand1.isComplete() && hand2.isComplete())
				points_hc = hand1.points_hc + hand2.points_hc;
			else
				throw new Exception("Hands not complete");
		}
		
		return points_hc;
	}
}
