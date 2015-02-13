package rep;


public class Player {
	private Deal deal;
	private Hand hand;
	private int posCode;
	private String position;
	
	// Information about all hands that this player has
	private HandView selfView, partnerView, leftOppView, rightOppView;
	private TeamView teamView, opponentsView;
	
	public Player(Hand h, int p) {
		hand = h;
		posCode = p;
		position = Deal.getPosition(posCode);
		
		initializeViews();
	}

	public Player(Hand h, String p) {
		hand = h;
		position = p;
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
