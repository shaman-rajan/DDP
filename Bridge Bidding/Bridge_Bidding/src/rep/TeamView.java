package rep;

import sml.Identifier;

public class TeamView {

	/*
	 * Features and their values that describe the hand
	 * Level of certainty or uncertainty of correctness of values
	 */
	
	public TeamView(Hand fullHand) {
		// TODO: Calculate features from 1 hand
	}
	
	public TeamView(TeamView hv) {
		//TODO: Copy into new view
	}
	
	public TeamView(HandView hv1, HandView hv2) {
		//TODO: Construct team view from 2 hand views
	}
	
	public TeamView() {
		// No info available to start with
		// TODO: Initialize feature values
	}
	
	public void addToSoarIdentifier(Identifier obj) {
		// TODO: Add details of the view to the SOAR identifier
	}
}
