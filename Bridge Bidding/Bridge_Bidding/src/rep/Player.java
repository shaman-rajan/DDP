package rep;

import java.util.List;

import rep.Card.Suit;
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
		
		this.teamView = new TeamView(selfView, partnerView, this);
		this.opponentsView = new TeamView(leftOppView, rightOppView, this);
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
							bid_returned = new Bid(bidString);
							BidHistory bh = this.deal.getAuction().getBidHistory();
							if((bh.getNumBidsMade() > 0 && !(bid_returned.compareTo(bh.getBid(0)) == 1)) ||
									(bh.getNumBidsMade() > 1 && !(bid_returned.compareTo(bh.getBid(1)) == 1)) ||
									(bh.getNumBidsMade() > 2 && !(bid_returned.compareTo(bh.getBid(2)) == 1)))
								bid_returned = new Bid("PASS");
						} else {
							Identifier meaning = child.ConvertToIdentifier();
							// Update others' views
							this.deal.updateViews(meaning, this.position);
							// Update my own team view (with trump, GF, etc.)
							int numUpdates = meaning.GetNumberChildren();
							for(int k=0; k<numUpdates; ++k)
								if(meaning.GetChild(k).GetAttribute().equals("team")) {
									Identifier teamUpdates = meaning.GetChild(k).ConvertToIdentifier();
									int numTeamUpdates = teamUpdates.GetNumberChildren();
									for(int l=0; l<numTeamUpdates; ++l) {
										WMElement update = teamUpdates.GetChild(l);
										System.out.println("team." + update.GetAttribute() + ": " + update.GetValueAsString());
										if(update.GetValueType().equals("int"))
											this.teamView.updateFeature(update.GetAttribute(), update.ConvertToIntElement().GetValue());
										else if(update.GetValueType().equals("string"))
											this.teamView.updateFeature(update.GetAttribute(), update.ConvertToStringElement().GetValue());
									}
								}
						}
					}
					is_output_a_bid = true;
					command.AddStatusComplete();
				}
				
				// Output is a pattern that needs to be checked
				else if(command.GetAttribute().equals("pattern-check")) {
					if(command.GetParameterValue("status") == null || 
							!command.GetParameterValue("status").equals("complete")) {
						
						WMElement child_pattern = command.GetChild(0);
						WMElement child_node = command.GetChild(1);
						
						if(child_pattern.GetAttribute().equals("node")) {
							WMElement temp = child_node;
							child_node = child_pattern;
							child_pattern = temp;
						}
						
						Identifier node = child_node.ConvertToIdentifier();
						System.out.println("Checking pattern for " + node.GetParameterValue("name"));
						
						if(this.deal.getAuction().getBidHistory().matchPattern(child_pattern.GetValueAsString(), node))
							node.CreateStringWME("pattern-checked", "true");
						else
							node.CreateStringWME("pattern-checked", "false");
						
						command.AddStatusComplete();
					}
				}
			}
		}
		
		return bid_returned;
	}
	

	public boolean updateViews(Identifier iden, int bidder) {
		HandView toUpdate;
		TeamView teamToUpdate;
		if(this.posCode == (bidder+2) % 4) {
			toUpdate = partnerView;
			teamToUpdate = teamView;
		}
		else if(this.posCode == (bidder+1) % 4) {
			toUpdate = rightOppView;
			teamToUpdate = opponentsView;
		}
		else if(this.posCode == (bidder+3) % 4) {
			toUpdate = leftOppView;
			teamToUpdate = opponentsView;
		}
		else return false;
		
		if(toUpdate.updateView(iden, teamToUpdate))
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

	public String createStringForGUI(Suit suit) {
		return convertSuitListToString(this.getHand().getSuit(suit));
	}

	private String convertSuitListToString(List<Card> cards) {
		String ret = "";
		
		for(Card c : cards) {
			switch (c.getValue()) {
			case ACE:
				ret += "A";
				break;
			case KING:
				ret += "K";
				break;
			case QUEEN:
				ret += "Q";
				break;
			case JACK:
				ret += "J";
				break;
			case TEN:
				ret += "10";
				break;
			case NINE:
				ret += "9";
				break;
			case EIGHT:
				ret += "8";
				break;
			case SEVEN:
				ret += "7";
				break;
			case SIX:
				ret += "6";
				break;
			case FIVE:
				ret += "5";
				break;
			case FOUR:
				ret += "4";
				break;
			case THREE:
				ret += "3";
				break;
			case TWO:
				ret += "2";
				break;
			default:
				break;
			}
			
			ret += " ";
		}
		
		return ret;
	}
}
