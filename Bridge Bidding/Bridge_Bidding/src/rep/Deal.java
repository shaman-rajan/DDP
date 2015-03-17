package rep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import rep.Card.Suit;
import rep.Card.Value;

public class Deal {
	
	// Players
	private Player north, south, east, west;
	private TeamCombinedHands n_s, e_w;
	private String dealer;
	
	// Auction details
	private Auction auction;
	
	public Deal(Hand n, Hand s, Hand e, Hand w, String dealer) {
		north = new Player(n, 0, this);
		east = new Player(e, 1, this);
		south = new Player(s, 2, this);
		west = new Player(w, 3, this);
		n_s = null;
		e_w = null;
		this.dealer = dealer;
		completeHandReps();
		auction = new Auction(this, dealer);
	}
	
	// Randomly generate a deal and hands
	public Deal() {
		List<Integer> card_nums = new LinkedList<>();
		for(int i=0; i<52; ++i) card_nums.add(i);
		Collections.shuffle(card_nums);
		
		ArrayList<Card> cards = new ArrayList<>();
		for(int i=0; i<card_nums.size(); ++i) {
			int suitint = card_nums.get(i) / 13, valueint = card_nums.get(i) % 13;
			Suit s;
			Value v;
			
			Suit[] suitvalues = Suit.values();
			s = suitvalues[suitint];
			Value[] cardvalues = Value.values();
			v = cardvalues[valueint];
			cards.add(new Card(s, v));
		}
		
		Hand n = new Hand(cards.subList(0, 13));
		Hand e = new Hand(cards.subList(13, 26));
		Hand s = new Hand(cards.subList(26, 39));
		Hand w = new Hand(cards.subList(39, 52));
		
		north = new Player(n, 0, this);
		east = new Player(e, 1, this);
		south = new Player(s, 2, this);
		west = new Player(w, 3, this);
		n_s = null;
		e_w = null;
		dealer = "north";
		
		completeHandReps();
		auction = new Auction(this, dealer);
		
		System.out.println(north);
		System.out.println(east);
		System.out.println(south);
		System.out.println(west);
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
	
	public void askForNextBid() {
		this.auction.askForNextBid();
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
	
	public Player getPlayer(int pos) {
		switch(pos) {
		case 0:
			return north;
		case 1:
			return east;
		case 2:
			return south;
		case 3:
			return west;
		default:
			return null;
		}
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
