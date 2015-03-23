package rep;

import rep.Bid.BidSuit;
import rep.Bid.BidValue;
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
	
	public Player(Hand h, int pos, Deal d) {
		hand = h;
		posCode = pos;
		position = Deal.getPosition(posCode);
		deal = d;
		
		initializeViews();
	}

	public Player(Hand h, String pos, Deal d) {
		hand = h;
		position = pos;
		posCode = Deal.getPosition(position);
		deal = d;
		
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
		System.out.println("Asking " + this.position + " for bid");
		
		BiddingAgent agent = new BiddingAgent();
		Identifier inLink = agent.getInputLink();
		
		Identifier myView = inLink.CreateIdWME("myview");
		this.selfView.addToSoarIdentifier(myView);
		Identifier partnerView = inLink.CreateIdWME("pview");
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
		
		// Retrieve bid and what can be inferred from it and send to auction
		Bid bid_returned = null;
		int num_out = agent.getAgent().GetNumberCommands();
		for(int i=0; i<num_out; ++i) {
			Identifier iden = agent.getAgent().GetCommand(i);
			
			if(iden.GetAttribute().equals("bid")) {
				String bidString = iden.GetValueAsString();
				int bidval = bidString.charAt(0) - '1';
				char bidsuit = bidString.charAt(1);
				BidValue valueArray[] = BidValue.values();
				
				BidSuit bs = bidsuit == 'S' ? BidSuit.SPADE : 
							(bidsuit == 'H' ? BidSuit.HEART : 
							(bidsuit == 'D' ? BidSuit.DIAMOND :
							(bidsuit == 'C' ? BidSuit.CLUB : 
						     BidSuit.NOTRUMP)));
				BidValue bv = valueArray[bidval];
				bid_returned = new Bid(bs, bv);
			} else {
				this.deal.updateViews(iden, this.position);
			}
		}
		
		return bid_returned;
	}
	

	public boolean updateViews(Identifier iden, int bidder) {
		HandView toUpdate;
		if(this.posCode == (bidder+2) % 4)
			toUpdate = partnerView;
		else if(this.posCode == (bidder+1) % 4)
			toUpdate = rightOppView;
		else if(this.posCode == (bidder+3) % 4)
			toUpdate = leftOppView;
		else return false;
		
		if(toUpdate.updateView(iden))
			return true;
		else return false;
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
	
	public String toString() {
		return this.position + ": " + this.hand.toString();
	}
}
