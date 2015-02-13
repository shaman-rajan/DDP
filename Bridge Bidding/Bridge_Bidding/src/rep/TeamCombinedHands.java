package rep;

public class TeamCombinedHands {
	private Hand hand1, hand2;
	
	/*
	 * Features of combined hand
	 */
	private int points_hc;
	
	/*
	 * Construct and initialize with impossible value
	 */
	public TeamCombinedHands(Hand h1, Hand h2) {
		hand1 = h1;
		hand2 = h2;
		
		points_hc = -1;
	}
	
	/*
	 * Function to return features
	 * Calculate values within these functions (if uncalculated)
	 */
	public int getHCPoins() throws Exception {
		if(points_hc < 0) {
			if(hand1.isComplete() && hand2.isComplete())
				points_hc = hand1.getHCPoints() + hand2.getHCPoints();
			else
				throw new Exception("Hands not complete");
		}
		
		return points_hc;
	}
}
