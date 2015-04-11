package rep;

import sconn.BiddingAgent;
import sml.Identifier;
import sml.WMElement;
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
		this.selfView = new HandView(hand, this);
		this.partnerView = new HandView(this);
		this.leftOppView = new HandView(this);
		this.rightOppView = new HandView(this);
		
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
		
		boolean is_output_a_bid = false;
		Bid bid_returned = null;
		while(!is_output_a_bid) {
			
			// Run till next output (either the pattern or the bid)
			agent.getAgent().RunSelfTilOutput();
			
			// System.out.println(agent.getAgent().ExecuteCommandLine("print s1 --depth 4"));
			
			int num_out = agent.getAgent().GetNumberCommands(); System.out.println("commands" + num_out);

			for(int i=0; i<num_out; ++i) {
				Identifier command = agent.getAgent().GetCommand(i);
				
				// Output is a bid
				if(command.GetAttribute().equals("bid")) {
					int children = command.GetNumberChildren();
					for(int j=0; j<children; ++j) {
						WMElement child = command.GetChild(j);
						if(child.GetAttribute().equals("short-form")) {
							String bidString = child.GetValueAsString();
							// System.out.println("Bid string returned by SOAR: " + bidString);
							bid_returned = new Bid(bidString);
						} else {
							this.deal.updateViews(child.ConvertToIdentifier(), this.position);
						}
					}
					is_output_a_bid = true;
					command.AddStatusComplete();
				}
				
				// Output is a pattern that needs to be checked
				else if(command.GetAttribute().equals("pattern-check")) {
					if(command.GetParameterValue("status") == null || 
							!command.GetParameterValue("status").equals("complete")) {
						System.out.println("Checking pattern for " + command.GetParameterValue("node-name"));
						
						Identifier link_for_next_input = agent.getInputLink();
						int children = command.GetNumberChildren();
						for(int j=0; j<children; ++j) {
							WMElement child = command.GetChild(j);
							if(child.GetAttribute().equals("pattern")) {
								if(this.deal.getAuction().getBidHistory().matchPattern(child.GetValueAsString(), link_for_next_input))
									link_for_next_input.CreateStringWME("result", "true");
								else 
									link_for_next_input.CreateStringWME("result", "false");
							}
						}
						
						command.AddStatusComplete();
					}
				}
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
