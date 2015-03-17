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
	}
	
	public Bid removeLastBid() {
		LinkedList<Bid> player_bids = this.bids.get(players[this.turn]);
		this.turn = this.prevTurn();
		return player_bids.pop();
	}
	
	public void addToSoarIdentifier(Identifier history) {
		// TODO: Add the auction history to the Identifier for SOAR input
	}
	
	/*
	 * Functions to use this bid history and return useful information
	 */
	
	public Bid getLastBid() {
		LinkedList<Bid> player_bids = this.bids.get(players[this.prevTurn()]);
		return player_bids.get(0);
	}
	
	// TODO: Implement pattern match function, datatype of pattern?
	public boolean matchPattern(String pattern) {
		return true;
	}
	
	public int getTurn() {
		return this.turn;
	}

}
