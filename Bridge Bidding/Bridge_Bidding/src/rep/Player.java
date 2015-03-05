package rep;

import sconn.BiddingAgent;
import sml.Identifier;
import sml.smlRunStepSize;


public class Player {
	private Deal deal;
	private Hand hand;
	private int posCode;
	private String position;
	
	// Information about all hands that this player has
	private HandView selfView, partnerView, leftOppView, rightOppView;
	private TeamView teamView, opponentsView;
	
	public Player(Hand h, int pos) {
		hand = h;
		posCode = pos;
		position = Deal.getPosition(posCode);
		
		initializeViews();
	}

	public Player(Hand h, String pos) {
		hand = h;
		position = pos;
		posCode = Deal.getPosition(position);
		
		initializeViews();
	}
	
	private void initializeViews() {
		this.selfView = new HandView(hand);
		this.partnerView = new HandView();
		this.leftOppView = new HandView();
		this.rightOppView = new HandView();
		
		this.teamView = new TeamView(hand);
		this.opponentsView = new TeamView();
	}
	
	public Bid askForBid() {
		BiddingAgent agent = new BiddingAgent();
		Identifier inLink = agent.getInputLink();
		
		Identifier myView = inLink.CreateIdWME("myview");
		this.selfView.addToSoarIdentifier(myView);
		Identifier partnerView = inLink.CreateIdWME("pviwew");
		this.partnerView.addToSoarIdentifier(partnerView);
		Identifier leftView = inLink.CreateIdWME("lview");
		this.leftOppView.addToSoarIdentifier(leftView);
		Identifier rightView = inLink.CreateIdWME("rview");
		this.rightOppView.addToSoarIdentifier(rightView);
		
		Identifier tView = inLink.CreateIdWME("tview");
		this.teamView.addToSoarIdentifier(tView);
		Identifier oView = inLink.CreateIdWME("oview");
		this.opponentsView.addToSoarIdentifier(oView);
		
		Identifier history = inLink.CreateIdWME("bidhistory");
		this.deal.getAuction().getBidHistory().addToSoarIdentifier(history);
		
		// Proceed past the input phase
		agent.getAgent().RunSelf(1, smlRunStepSize.sml_PHASE);
		
		agent.getAgent().RunSelfTilOutput();
		
		// TODO: Retrieve bid and what can be inferred from it and send to auction
		
		// TODO: In the auction, add the inferred part to others' views
		
		return null;
	}
	
	public Hand getHand() {
		return this.hand;
	}
	
	public String getposition() {
		return position;
	}
	
	public HandView getSelfView() {
		return this.selfView;
	}
	
	public HandView getPartnerView() {
		return this.partnerView;
	}
	
	public HandView getLeftOppView() {
		return this.leftOppView;
	}
	
	public HandView getRightOppView() {
		return this.rightOppView;
	}
	
	public TeamView getTeamView() {
		return this.teamView;
	}
	
	public TeamView getOpponentsView() {
		return this.opponentsView;
	}
	
	public Deal getDeal() {
		return this.deal;
	}
}
