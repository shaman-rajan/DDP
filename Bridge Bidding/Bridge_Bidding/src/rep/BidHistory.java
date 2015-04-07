package rep;

import java.util.LinkedList;
import java.util.HashMap;

import sml.Identifier;

public class BidHistory {
	
	/*
	 * 0 - north
	 * 1 - east
	 * 2 - south
	 * 3 - west
	 */
	private int turn;
	private HashMap<String, Integer> player_map;

	/*
	 * Hashmap of bids made by each player
	 * Bids are added to the lists from the front,
	 * i.e. most recent bids are at the front of the list
	 */
	private HashMap<String,LinkedList<Bid>> bids;
	private String historyAsString;
	private int bids_made;
	
	private static String[] players = {"north", "east", "south", "west"};
	
	public BidHistory(String starter) {
		player_map = new HashMap<>();
		bids = new HashMap<>();

		player_map.put("north", 0);
		player_map.put("east", 1);
		player_map.put("south", 2);
		player_map.put("west", 3);
		
		turn = player_map.get(starter);
		
		bids = new HashMap<>(4);
		for(int i=0; i<4; ++i) bids.put(players[i], new LinkedList<Bid>());
		
		historyAsString = "";
		bids_made = 0;
	}
	
	private int nextTurn() {
		return (this.turn + 1) % 4;
	}
	
	private int prevTurn() {
		return (this.turn + 3) % 4;
	}
	
	public void addBid(Bid b) {
		LinkedList<Bid> player_bids = this.bids.get(players[this.turn]);
		player_bids.push(b);
		this.turn = this.nextTurn();
		
		this.historyAsString = b.toString() + " " + historyAsString;
		this.bids_made++;
	}
	
	public Bid removeLastBid() {
		LinkedList<Bid> player_bids = this.bids.get(players[this.turn]);
		this.turn = this.prevTurn();
		
		int i = 0;
		while(this.historyAsString.charAt(i) > 9) i++;
		this.historyAsString = this.historyAsString.substring(i);
		this.bids_made--;
		
		return player_bids.pop();
	}
	
	public void addToSoarIdentifier(Identifier history) {
		// TODO: Once data-type is decided, add everything to the identifier here
		if(bids_made > 0) history.CreateStringWME("last-bid", this.getBid(0).toString());
	}
	
	/*
	 * Functions to use this bid history and return useful information
	 */
	public Bid getLastBid() {
		LinkedList<Bid> player_bids = this.bids.get(players[this.prevTurn()]);
		return player_bids.get(0);
	}
	
	/*
	 *  Pattern match function
	 *  Inputs:
	 *  	1. Pattern to match auction against
	 *  	2. identifier to add matched suits to (referenced in caller)
	 */
	public boolean matchPattern(String pattern, Identifier iden) {
		String[] patternBids = pattern.split(" ");
		
		char M = 0, m = 0, OM = 0, om = 0;
		
		int i = 0;
		for(String pBid : patternBids) {
			if(pBid.charAt(0) == '*') {
				while(i < this.bids_made) {
					if(!this.getBid(i).toString().equals("PASS"))
						return false;
					i++;
				}
			} else {
				String bidString = this.getBid(i).toString();
				if(Character.isDigit(pBid.charAt(0))) {
					if(!(pBid.charAt(0) == bidString.charAt(0)))
						return false;
					else {
						switch(pBid.charAt(1)) {
						case 'M':
							if(M == 0 && (bidString.charAt(1) == 'S' || bidString.charAt(1) == 'H')) {
								M = bidString.charAt(1);
								OM = M == 'S' ? 'H' : 'S';
							}
							else if(M != 0 && bidString.charAt(1) == M)
								;
							else
								return false;
							break;
						case 'm':
							if(m == 0 && (bidString.charAt(1) == 'D' || bidString.charAt(1) == 'C')) {
								m = bidString.charAt(1);
								om = m == 'D' ? 'C' : 'D';
							}
							else if(m != 0 && bidString.charAt(1) == m)
								;
							else
								return false;
							break;
						case 'O':
							if(OM == 0 && (bidString.charAt(1) == 'S' || bidString.charAt(1) == 'H')) {
								OM = bidString.charAt(1);
								M = OM == 'S' ? 'H' : 'S';
							}
							else if(OM != 0 && bidString.charAt(1) == OM)
								;
							else
								return false;
							break;
						case 'o':
							if(om == 0 && (bidString.charAt(1) == 'D' || bidString.charAt(1) == 'C')) {
								om = bidString.charAt(1);
								m = om == 'D' ? 'C' : 'D';
							}
							else if(om == 1 && bidString.charAt(1) == om)
								;
							else
								return false;
							break;
						}
					}
				} else {
					if(!bidString.equals(pBid))
						return false;
				}
				
				i++;
			}
		}
		
		if(M != 0) iden.CreateStringWME("M", completeSuitName(M));
		else {
			iden.CreateStringWME("M", "spade");
			iden.CreateStringWME("M", "heart");
		}
		if(m != 0) iden.CreateStringWME("m", completeSuitName(m));
		else {
			iden.CreateStringWME("m", "dia");
			iden.CreateStringWME("m", "club");
		}
		if(OM != 0) iden.CreateStringWME("OM", completeSuitName(OM));
		if(om != 0) iden.CreateStringWME("om", completeSuitName(om));
		
		return true;
	}
	
	private Bid getBid(int seq_num) {
		if(seq_num >= bids_made) return null;
		
		int player_to_get_bid_from = this.turn + 40 - (seq_num + 1);
		int bid_index_in_player_bids = seq_num/4;
		
		return this.bids.get(players[player_to_get_bid_from]).get(bid_index_in_player_bids);
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	public int getNumBidsMade() {
		return this.bids_made;
	}
	
	private static String completeSuitName(char suit) {
		if(suit == 'S') return "spade";
		if( suit == 'H') return "heart";
		if( suit == 'D') return "dia";
		if( suit == 'C') return "club";
		return "null";
	}

}
