package rep;

public class Deal {
	// Players
	private Player north, south, east, west;
	private TeamCombinedHands n_s, e_w;
	private String dealer;
	
	// Auction details
	// TODO: Details of auction history, current state, and future goals
	Auction auction;
	
	public Deal(Hand n, Hand s, Hand e, Hand w) {
		north = new Player(n, 0);
		east = new Player(e, 1);
		south = new Player(s, 2);
		west = new Player(w, 3);
		n_s = null;
		e_w = null;
		completeHandReps();
	}
	
	public boolean completeHandReps() {
		if(north.getHand().isComplete() &&
				south.getHand().isComplete() &&
				east.getHand().isComplete() &&
				west.getHand().isComplete()) {
			n_s = new TeamCombinedHands(north.getHand(), south.getHand());
			e_w = new TeamCombinedHands(east.getHand(), west.getHand());
			return true;
		} else return false;
	}
	
	public static String getPosition(int posCode) {
		switch(posCode) {
		case 0:
			return "north";
		case 1:
			return "east";
		case 2:
			return "south";
		case 3:
			return "west";
		}
		return null;
	}
	
	public static int getPosition(String pos) {
		if(pos.equals("north")) return 0;
		else if(pos.equals("east")) return 1;
		else if(pos.equals("south")) return 2;
		else if(pos.equals("west")) return 3;
		return -1;
	}
	
	public Player getNorth() {
		return north;
	}
	
	public Player getSouth() {
		return south;
	}
	
	public Player getEast() {
		return east;
	}
	
	public Player getWest() {
		return west;
	}
	
	public TeamCombinedHands getNorthSouth() {
		return n_s;
	}
	
	public TeamCombinedHands getEastWest() {
		return e_w;
	}

	public String getDealer() {
		return this.dealer;
	}
	
	public Auction getAuction() {
		return this.auction;
	}
}
