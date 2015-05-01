package rep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rep.Card.Suit;
import rep.Card.Value;

public class Hand implements Cloneable {
	private ArrayList<Card> cards;
	
	public Hand() {
		cards = new ArrayList<>(13);
	}
	
	public Hand(Card c) {
		cards = new ArrayList<>(13);
		cards.add(c);
	}
	
	public Hand(List<Card> cl) {
		cards = new ArrayList<>(13);
		if(cl.size() <= 13)
			cards.addAll(cl);
		if(cards.size() == 13)
			calculateFeatureValues();
	}
	
	/*
	 * Input: 4 comma separated suits, 
	 * with each suit containing a list of cards.
	 * Each card is a number (2-9) or a letter (T,J,Q,K,A)
	 */
	public Hand(String allCards) {
		String[] suitSplit= allCards.split(",");
		if(suitSplit.length != 4) return;
		else {
			this.cards = new ArrayList<>(13);
			
			// Spade cards
			for(int i=0; i<suitSplit[0].length(); ++i) {
				Value[] values = Value.values();
				Value v = null;
				for(int j=0; j<values.length; ++j)
					if(values[j].getRank() == suitSplit[0].charAt(i)) {
						v = values[j];
						break;
					}
				if(v != null) this.cards.add(new Card(Suit.SPADE, v));
				else return;
			}
			
			// Heart cards
			for(int i=0; i<suitSplit[1].length(); ++i) {
				Value[] values = Value.values();
				Value v = null;
				for(int j=0; j<values.length; ++j)
					if(values[j].getRank() == suitSplit[1].charAt(i)) {
						v = values[j];
						break;
					}
				if(v != null) this.cards.add(new Card(Suit.HEART, v));
				else return;
			}
						
			// Diamond cards
			for(int i=0; i<suitSplit[2].length(); ++i) {
				Value[] values = Value.values();
				Value v = null;
				for(int j=0; j<values.length; ++j)
					if(values[j].getRank() == suitSplit[2].charAt(i)) {
						v = values[j];
						break;
					}
				if(v != null) this.cards.add(new Card(Suit.DIAMOND, v));
				else return;
			}
			
			// Club cards
			for(int i=0; i<suitSplit[3].length(); ++i) {
				Value[] values = Value.values();
				Value v = null;
				for(int j=0; j<values.length; ++j)
					if(values[j].getRank() == suitSplit[3].charAt(i)) {
						v = values[j];
						break;
					}
				if(v != null) this.cards.add(new Card(Suit.CLUB, v));
				else return;
			}
			
			if(isComplete()) calculateFeatureValues();
			else System.out.println("Cannot continue. Number of cards in hand is " + this.cards.size());
		}
	}
	
	public void addCard(Card c) {
		if(cards.size() < 13) {
			cards.add(c);
			if(cards.size() == 13)
				calculateFeatureValues();
		}
		else throw new ArrayIndexOutOfBoundsException(13);
	}
	
	public boolean isComplete() {
		if(this.cards.size() == 13) return true;
		else return false;
	}
	
	public boolean contains(Suit suit, Value val) {
		for(Card c : cards)
			if(c.getSuit() == suit && c.getValue() == val)
				return true;
		return false;
	}
	
	/* 
	 * Calculate features and set
	 */
	
	/*
	 *  High card features 
	 */
	public int points_hc, points_spade, points_heart, points_dia, points_club;
	public int controls, controls_spade, controls_heart, controls_dia, controls_club;
	public int highCards, highCards_spade, highCards_heart, highCards_dia, highCards_club;
	public int honors, honors_spade, honors_heart, honors_dia, honors_club;
	public int aces, ace_spade, ace_heart, ace_dia, ace_club;
	public int kings, king_spade, king_heart, king_dia, king_club;
	public int queens, queen_spade, queen_heart, queen_dia, queen_club;
	public int jacks, jack_spade, jack_heart, jack_dia, jack_club;
	public int tens, ten_spade, ten_heart, ten_dia, ten_club;
	public int rkcb_spade, rkcb_heart, rkcb_dia, rkcb_club;
	public int num_spade, num_heart, num_dia, num_club;
	
	/*
	 *  Distribution
	 */
	
	/*
	 * 0 = 4333
	 * 1 = 5332 / 4432
	 * 2 = 5422
	 * 3 = 6322 / 7222
	 * 4 = 4441
	 * 5 = unbalanced
	 * 6 = at least 11 cards in the longest two suits.
	 */
	public int balanced;
	
	public int dp_sp_he, dp_sp_di, dp_sp_cl, dp_sp_ha,
				dp_he_sp, dp_he_di, dp_he_cl, dp_he_ha,
				dp_di_sp, dp_di_he, dp_di_cl, dp_di_ha,
				dp_cl_sp, dp_cl_he, dp_cl_di, dp_cl_ha;
	public int num_suits;
	public int lmaj, lmin;
	public int longest_sp, longest_he, longest_di, longest_cl;
	public int shortest_sp, shortest_he, shortest_di, shortest_cl;
	
	/*
	 * Suit quality
	 */
	
	/*
	 *  0 = not biddable 
	 *  1 = biddble suit 
	 *  2 = rebiddable 
	 *  3 = twice rebiddable 
	 *  4+ = every card beyond twice rebiddable
	 */
	public int biddable_sp, biddable_he, biddable_di, biddable_cl;
	
	public float intermediate_sp, intermediate_he, intermediate_di, intermediate_cl, intermediate_ha;
	
	/*
	 * 4 = length + hcp > 8 
	 * 3 = A, qjx or 5+ hcp 
	 * 2 = Kx or Qxx.
	 * 1 = Qx or Jxx.
	 * 0 = no other values
	 */
	public int stopper_sp, stopper_he, stopper_di, stopper_cl;
	/*
	 * 4 = first and second round control [ AK or AQ ]
	 * 3 = first round control  [ A or (void) ]
	 * 2 = second round control [ K or (x) ]
	 * 1 = third round control  [ Q or (xx) ]
	 * 0 = xxx
	 */
	public int tr_stopper_sp_sp, tr_stopper_sp_he, tr_stopper_sp_di, tr_stopper_sp_cl,
				tr_stopper_he_sp, tr_stopper_he_he, tr_stopper_he_di, tr_stopper_he_cl,
				tr_stopper_di_sp, tr_stopper_di_he, tr_stopper_di_di, tr_stopper_di_cl,
				tr_stopper_cl_sp, tr_stopper_cl_he, tr_stopper_cl_di, tr_stopper_cl_cl;
	
	public int losers_sp_he, losers_sp_di, losers_sp_cl, losers_sp_ha,
				losers_he_sp, losers_he_di, losers_he_cl, losers_he_ha,
				losers_di_sp, losers_di_he, losers_di_cl, losers_di_ha,
				losers_cl_sp, losers_cl_he, losers_cl_di, losers_cl_ha;
	
	/*
	 * 9 = AKxxxxxxx or AKJxxxxx
	 * 8 = AKQxxxx
	 * 7 = AKQTxx
	 * 6 = type 5, also 8+ hcp or 4/5 honors
	 * 5 = would be type 4 if smallest card removed (rerebiddable)
	 * 4 = would be type 3 if smallest card removed (rebiddable)
	 * 3 = 4 card, 3/5 honors or 4+ hcp, or any 5+ card (biddble)
	 * 2 = 4 cards.
	 * 1 = 3 cards.
	 * 0 = no other values
	 */
	public int quality_sp, quality_he, quality_di, quality_cl;
	
	/*
	 * Hand quality
	 */
	public double ratio;
	
	/*
	 * Estimated number of losers in the hand without partner support for the longest suit
	 */
	public double unlos_sp, unlos_he, unlos_di, unlos_cl;
	
	public int total_points_sp, total_points_he, total_points_di, total_points_cl;
	
	// Calculation function
	private void calculateFeatureValues() {
		
		// Sort hand based on suit then value
		Collections.sort(cards, Collections.reverseOrder());
		
		/*
		 * Initialization
		 */
		points_hc = points_club = points_dia = points_heart = points_spade = 0;
		controls = controls_club = controls_dia = controls_heart = controls_spade = 0;
		highCards = highCards_club = highCards_dia = highCards_heart = highCards_spade = 0;
		honors = honors_club = honors_dia = honors_heart = honors_spade = 0;
		aces = ace_club = ace_dia = ace_heart = ace_spade = 0;
		kings = king_club = king_dia = king_heart = king_spade = 0;
		queens = queen_club = queen_dia = queen_heart = queen_spade = 0;
		jacks = jack_club = jack_dia = jack_heart = jack_spade = 0;
		tens = ten_club = ten_dia = ten_heart = ten_spade = 0;
		rkcb_club = rkcb_dia = rkcb_heart = rkcb_spade = 0;
		num_spade = num_heart = num_dia = num_club = 0;
		
		balanced = 0;
		dp_sp_he = dp_sp_di = dp_sp_cl = dp_sp_ha = 0;
		dp_he_sp = dp_he_di = dp_he_cl = dp_he_ha = 0;
		dp_di_sp = dp_di_he = dp_di_cl = dp_di_ha = 0;
		dp_cl_sp = dp_cl_he = dp_cl_di = dp_cl_ha = 0;
		num_suits = 0;
		lmaj = lmin = 0;
		longest_sp = longest_he = longest_di = longest_cl = 0;
		shortest_sp = shortest_he = shortest_di = shortest_cl = 0;
		
		intermediate_sp = intermediate_he = intermediate_di = intermediate_cl = intermediate_ha = 0;

		stopper_sp = stopper_he = stopper_di = stopper_cl = 0;
		
		tr_stopper_sp_sp = tr_stopper_sp_he = tr_stopper_sp_di = tr_stopper_sp_cl =
		tr_stopper_he_sp = tr_stopper_he_he = tr_stopper_he_di = tr_stopper_he_cl =
		tr_stopper_di_sp = tr_stopper_di_he = tr_stopper_di_di = tr_stopper_di_cl =
		tr_stopper_cl_sp = tr_stopper_cl_he = tr_stopper_cl_di = tr_stopper_cl_cl = 0;
		
		quality_sp = quality_he = quality_di = quality_cl = 0;
		ratio = 0;
		
		unlos_sp = unlos_he = unlos_di = unlos_cl = 0;
		
		total_points_sp = total_points_he = total_points_di = total_points_cl = 0;
		
		/*
		 * HC Cards and Points (full hand)
		 */
		for(Card c : cards) {
			switch (c.getValue()) {
			case ACE:
				points_hc += 4;
				controls += 2;
				highCards++;
				honors++;
				aces++;
				rkcb_club++;
				rkcb_dia++;
				rkcb_heart++;
				rkcb_spade++;
				break;
			case KING:
				points_hc += 3;
				controls += 1;
				highCards++;
				honors++;
				kings++;
				rkcb_club++;
				rkcb_dia++;
				rkcb_heart++;
				rkcb_spade++;
				break;
			case QUEEN:
				points_hc += 2;
				highCards++;
				honors++;
				queens++;
				rkcb_club++;
				rkcb_dia++;
				rkcb_heart++;
				rkcb_spade++;
				break;
			case JACK:
				points_hc += 1;
				honors++;
				jacks++;
				rkcb_club++;
				rkcb_dia++;
				rkcb_heart++;
				rkcb_spade++;
				break;
			case TEN:
				honors++;
				tens++;
				rkcb_club++;
				rkcb_dia++;
				rkcb_heart++;
				rkcb_spade++;
			default:
				break;
			}
		}
		
		/*
		 * HC Cards and Points (suit specific)
		 */
		for(Card c : cards) {
			switch(c.getSuit()) {
			case SPADE:
				num_spade++;
				switch (c.getValue()) {
				case ACE:
					points_spade += 4;
					controls_spade += 2;
					highCards_spade++;
					honors_spade++;
					ace_spade++;
					break;
				case KING:
					points_spade += 3;
					controls_spade += 1;
					highCards_spade++;
					honors_spade++;
					king_spade++;
					rkcb_spade++;
					break;
				case QUEEN:
					points_spade += 2;
					highCards_spade++;
					honors_spade++;
					queen_spade++;
					break;
				case JACK:
					points_spade += 1;
					honors_spade++;
					jack_spade++;
					break;
				case TEN:
					honors_spade++;
					ten_spade++;
					intermediate_sp += 2;
					break;
				case NINE:
					intermediate_sp += 1;
					break;
				case EIGHT:
					intermediate_sp += 0.5;
					break;
				default:
					break;
				}
				break;
			case HEART:
				num_heart++;
				switch (c.getValue()) {
				case ACE:
					points_heart += 4;
					controls_heart += 2;
					highCards_heart++;
					honors_heart++;
					ace_heart++;
					break;
				case KING:
					points_heart += 3;
					controls_heart += 1;
					highCards_heart++;
					honors_heart++;
					king_heart++;
					rkcb_heart++;
					break;
				case QUEEN:
					points_heart += 2;
					highCards_heart++;
					honors_heart++;
					queen_heart++;
					break;
				case JACK:
					points_heart += 1;
					honors_heart++;
					jack_heart++;
					break;
				case TEN:
					honors_heart++;
					ten_heart++;
					intermediate_he += 2;
					break;
				case NINE:
					intermediate_he += 1;
					break;
				case EIGHT:
					intermediate_he += 0.5;
					break;
				default:
					break;
				}
				break;
			case DIAMOND:
				num_dia++;
				switch (c.getValue()) {
				case ACE:
					points_dia += 4;
					controls_dia += 2;
					highCards_dia++;
					honors_dia++;
					ace_dia++;
					break;
				case KING:
					points_dia += 3;
					controls_dia += 1;
					highCards_dia++;
					honors_dia++;
					king_dia++;
					rkcb_dia++;
					break;
				case QUEEN:
					points_dia += 2;
					highCards_dia++;
					honors_dia++;
					queen_dia++;
					break;
				case JACK:
					points_dia += 1;
					honors_dia++;
					jack_dia++;
					break;
				case TEN:
					honors_dia++;
					ten_dia++;
					intermediate_di += 2;
					break;
				case NINE:
					intermediate_di += 1;
					break;
				case EIGHT:
					intermediate_di += 0.5;
					break;
				default:
					break;
				}
				break;
			case CLUB:
				num_club++;
				switch (c.getValue()) {
				case ACE:
					points_club += 4;
					controls_club += 2;
					highCards_club++;
					honors_club++;
					ace_club++;
					break;
				case KING:
					points_club += 3;
					controls_club += 1;
					highCards_club++;
					honors_club++;
					king_club++;
					rkcb_club++;
					break;
				case QUEEN:
					points_club += 2;
					highCards_club++;
					honors_club++;
					queen_club++;
					break;
				case JACK:
					points_club += 1;
					honors_club++;
					jack_club++;
					break;
				case TEN:
					honors_club++;
					ten_club++;
					intermediate_cl += 2;
					break;
				case NINE:
					intermediate_cl += 1;
					break;
				case EIGHT:
					intermediate_cl += 0.5;
					break;
				default:
					break;
				}
				break;
			}
		}
		
		int temp;
		if(num_spade == 0) temp = 3;
		else if(num_spade == 1) temp = 2;
		else if(num_spade == 2) temp = 1;
		else temp = 0;
		dp_he_sp += temp;
		dp_di_sp += temp;
		dp_cl_sp += temp;
		
		if(num_heart == 0) temp = 3;
		else if(num_heart == 1) temp = 2;
		else if(num_heart == 2) temp = 1;
		else temp = 0;
		dp_sp_he += temp;
		dp_di_he += temp;
		dp_cl_he += temp;
		
		if(num_dia == 0) temp = 3;
		else if(num_dia == 1) temp = 2;
		else if(num_dia == 2) temp = 1;
		else temp = 0;
		dp_sp_di += temp;
		dp_he_di += temp;
		dp_cl_di += temp;
		
		if(num_club == 0) temp = 3;
		else if(num_club == 1) temp = 2;
		else if(num_club == 2) temp = 1;
		else temp = 0;
		dp_sp_cl += temp;
		dp_he_cl += temp;
		dp_di_cl += temp;
		
		dp_sp_ha = dp_sp_he + dp_sp_di + dp_sp_cl;
		dp_he_ha = dp_he_sp + dp_he_di + dp_he_cl;
		dp_di_ha = dp_di_sp + dp_di_he + dp_di_cl;
		dp_cl_ha = dp_cl_sp + dp_cl_he + dp_cl_di;
		
		num_suits = 0;
		if(num_spade > 3) num_suits++;
		if(num_heart > 3) num_suits++;
		if(num_dia > 3) num_suits++;
		if(num_club > 3) num_suits++;
		
		lmaj = num_spade > num_heart ? num_spade : num_heart;
		lmin = num_dia > num_club ? num_dia : num_club;
		
		int max = num_spade, min = num_spade;
		if(num_heart > max) max = num_heart;
		if(num_dia > max) max = num_dia;
		if(num_club > max) max = num_club;
		if(num_heart < min) min= num_heart;
		if(num_dia < min) min= num_dia;
		if(num_club < min) min= num_club;
		if(num_spade == max) longest_sp = 1;
		if(num_heart == max) longest_he = 1;
		if(num_dia == max) longest_di = 1;
		if(num_club == max) longest_cl = 1;
		if(num_spade == min) shortest_sp = 1;
		if(num_heart == min) shortest_he = 1;
		if(num_dia == min) shortest_di = 1;
		if(num_club == min) shortest_cl = 1;
		
		List<Integer> lenarr = new ArrayList<>(4);
		lenarr.add(num_spade);
		lenarr.add(num_heart);
		lenarr.add(num_dia);
		lenarr.add(num_club);
		Collections.sort(lenarr, Collections.reverseOrder());
		
		if(lenarr.get(0) == 4 && lenarr.get(1) == 3 && lenarr.get(2) == 3 && lenarr.get(3) == 3)
			balanced = 0;
		else if((lenarr.get(0) == 5 && lenarr.get(1) == 3 && lenarr.get(2) == 3 && lenarr.get(3) == 2) ||
				 lenarr.get(0) == 4 && lenarr.get(1) == 4 && lenarr.get(2) == 3 && lenarr.get(3) == 2)
			balanced = 1;
		else if((lenarr.get(0) == 5 && lenarr.get(1) == 4 && lenarr.get(2) == 2 && lenarr.get(3) == 2))
			balanced = 2;
		else if((lenarr.get(0) == 6 && lenarr.get(1) == 3 && lenarr.get(2) == 2 && lenarr.get(3) == 2) ||
				 lenarr.get(0) == 7 && lenarr.get(1) == 2 && lenarr.get(2) == 2 && lenarr.get(3) == 2)
			balanced = 3;
		else if((lenarr.get(0) == 4 && lenarr.get(1) == 4 && lenarr.get(2) == 4 && lenarr.get(3) == 1))
			balanced = 4;
		else if(lenarr.get(3) < 2 && (lenarr.get(2) + lenarr.get(3) > 2))
			balanced = 5;
		else balanced = 6;

		// Biddable (each suit)
		if(honors_spade > 2 || points_spade > 4)
			biddable_sp = num_spade - 3;
		else biddable_sp = num_spade - 4;
		
		if(honors_heart > 2 || points_heart > 4)
			biddable_he = num_heart - 3;
		else biddable_he = num_heart - 4;
		
		if(honors_dia > 2 || points_dia > 4)
			biddable_di = num_dia - 3;
		else biddable_di = num_dia - 4;
		
		if(honors_club > 2 || points_club > 4)
			biddable_cl = num_club - 3;
		else biddable_cl = num_club - 4;
		
		intermediate_ha = intermediate_sp + intermediate_he+ intermediate_di + intermediate_cl;
		
		// Stopper (NT)
		if(points_spade + num_spade > 8) 
			stopper_sp = 4;
		else if((points_spade > 4) ||
				(this.contains(Suit.SPADE, Value.ACE)) ||
				(num_spade > 3 && this.contains(Suit.SPADE, Value.QUEEN) && this.contains(Suit.SPADE, Value.JACK)))
			stopper_sp = 3;
		else if((num_spade > 1 && this.contains(Suit.SPADE, Value.KING)) ||
				(num_spade > 2 && this.contains(Suit.SPADE, Value.QUEEN)))
			stopper_sp = 2;
		else if((num_spade > 1 && this.contains(Suit.SPADE, Value.QUEEN)) ||
				(num_spade > 2 && this.contains(Suit.SPADE, Value.JACK)))
			stopper_sp = 1;
		else
			stopper_sp = 0;
		
		if(points_heart + num_heart > 8) 
			stopper_he = 4;
		else if((points_heart > 4) ||
				(this.contains(Suit.HEART, Value.ACE)) ||
				(num_heart > 3 && this.contains(Suit.HEART, Value.QUEEN) && this.contains(Suit.HEART, Value.JACK)))
			stopper_he = 3;
		else if((num_heart > 1 && this.contains(Suit.HEART, Value.KING)) ||
				(num_heart > 2 && this.contains(Suit.HEART, Value.QUEEN)))
			stopper_he = 2;
		else if((num_heart > 1 && this.contains(Suit.HEART, Value.QUEEN)) ||
				(num_heart > 2 && this.contains(Suit.HEART, Value.JACK)))
			stopper_he = 1;
		else
			stopper_he = 0;
		
		if(points_dia + num_dia > 8) 
			stopper_di = 4;
		else if((points_dia > 4) ||
				(this.contains(Suit.DIAMOND, Value.ACE)) ||
				(num_dia > 3 && this.contains(Suit.DIAMOND, Value.QUEEN) && this.contains(Suit.DIAMOND, Value.JACK)))
			stopper_di = 3;
		else if((num_dia > 1 && this.contains(Suit.DIAMOND, Value.KING)) ||
				(num_dia > 2 && this.contains(Suit.DIAMOND, Value.QUEEN)))
			stopper_di = 2;
		else if((num_dia > 1 && this.contains(Suit.DIAMOND, Value.QUEEN)) ||
				(num_dia > 2 && this.contains(Suit.DIAMOND, Value.JACK)))
			stopper_di = 1;
		else
			stopper_di = 0;
		
		if(points_club + num_club > 8) 
			stopper_cl = 4;
		else if((points_club > 4) ||
				(this.contains(Suit.CLUB, Value.ACE)) ||
				(num_club > 3 && this.contains(Suit.CLUB, Value.QUEEN) && this.contains(Suit.CLUB, Value.JACK)))
			stopper_cl = 3;
		else if((num_club > 1 && this.contains(Suit.CLUB, Value.KING)) ||
				(num_club > 2 && this.contains(Suit.CLUB, Value.QUEEN)))
			stopper_cl = 2;
		else if((num_club > 1 && this.contains(Suit.CLUB, Value.QUEEN)) ||
				(num_club > 2 && this.contains(Suit.CLUB, Value.JACK)))
			stopper_cl = 1;
		else
			stopper_cl = 0;
		
		// Stopper (TRUMP PLAY) 
		int stopper_count;
		if(this.contains(Suit.SPADE, Value.ACE) && 
				(this.contains(Suit.SPADE, Value.KING) || this.contains(Suit.SPADE, Value.QUEEN)))
			stopper_count = 4;
		else if(this.contains(Suit.SPADE, Value.ACE))
			stopper_count = 3;
		else if(num_spade == 0)
			stopper_count = 3;
		else if(this.contains(Suit.SPADE, Value.KING))
			stopper_count = 2;
		else if(num_spade == 1)
			stopper_count = 2;
		else if(this.contains(Suit.SPADE, Value.QUEEN))
			stopper_count = 1;
		else if(num_spade == 2)
			stopper_count = 1;
		else
			stopper_count = 0;
		tr_stopper_he_sp = tr_stopper_di_sp = tr_stopper_cl_sp = stopper_count;
		
		if(this.contains(Suit.HEART, Value.ACE) && 
				(this.contains(Suit.HEART, Value.KING) || this.contains(Suit.HEART, Value.QUEEN)))
			stopper_count = 4;
		else if(this.contains(Suit.HEART, Value.ACE))
			stopper_count = 3;
		else if(num_heart == 0)
			stopper_count = 3;
		else if(this.contains(Suit.HEART, Value.KING))
			stopper_count = 2;
		else if(num_heart == 1)
			stopper_count = 2;
		else if(this.contains(Suit.HEART, Value.QUEEN))
			stopper_count = 1;
		else if(num_heart == 2)
			stopper_count = 1;
		else
			stopper_count = 0;
		tr_stopper_sp_he = tr_stopper_di_he = tr_stopper_cl_he = stopper_count;
		
		if(this.contains(Suit.HEART, Value.ACE) && 
				(this.contains(Suit.HEART, Value.KING) || this.contains(Suit.HEART, Value.QUEEN)))
			stopper_count = 4;
		else if(this.contains(Suit.HEART, Value.ACE))
			stopper_count = 3;
		else if(num_heart == 0)
			stopper_count = 3;
		else if(this.contains(Suit.HEART, Value.KING))
			stopper_count = 2;
		else if(num_heart == 1)
			stopper_count = 2;
		else if(this.contains(Suit.HEART, Value.QUEEN))
			stopper_count = 1;
		else if(num_heart == 2)
			stopper_count = 1;
		else
			stopper_count = 0;
		tr_stopper_sp_di = tr_stopper_he_di = tr_stopper_cl_di = stopper_count;
		
		if(this.contains(Suit.CLUB, Value.ACE) && 
				(this.contains(Suit.CLUB, Value.KING) || this.contains(Suit.CLUB, Value.QUEEN)))
			stopper_count = 4;
		else if(this.contains(Suit.CLUB, Value.ACE))
			stopper_count = 3;
		else if(num_club == 0)
			stopper_count = 3;
		else if(this.contains(Suit.CLUB, Value.KING))
			stopper_count = 2;
		else if(num_club == 1)
			stopper_count = 2;
		else if(this.contains(Suit.CLUB, Value.QUEEN))
			stopper_count = 1;
		else if(num_club == 2)
			stopper_count = 1;
		else
			stopper_count = 0;
		tr_stopper_sp_cl = tr_stopper_he_cl = tr_stopper_di_cl = stopper_count;
		
		if(this.contains(Suit.SPADE, Value.ACE) && 
				(this.contains(Suit.SPADE, Value.KING) || this.contains(Suit.SPADE, Value.QUEEN)))
			stopper_count = 4;
		else if(this.contains(Suit.SPADE, Value.ACE))
			stopper_count = 3;
		else if(this.contains(Suit.SPADE, Value.KING))
			stopper_count = 2;
		else if(this.contains(Suit.SPADE, Value.QUEEN))
			stopper_count = 1;
		else
			stopper_count = 0;
		tr_stopper_sp_sp = stopper_count;
		
		if(this.contains(Suit.HEART, Value.ACE) && 
				(this.contains(Suit.HEART, Value.KING) || this.contains(Suit.HEART, Value.QUEEN)))
			stopper_count = 4;
		else if(this.contains(Suit.HEART, Value.ACE))
			stopper_count = 3;
		else if(this.contains(Suit.HEART, Value.KING))
			stopper_count = 2;
		else if(this.contains(Suit.HEART, Value.QUEEN))
			stopper_count = 1;
		else
			stopper_count = 0;
		tr_stopper_he_he = stopper_count;
		
		if(this.contains(Suit.DIAMOND, Value.ACE) && 
				(this.contains(Suit.DIAMOND, Value.KING) || this.contains(Suit.DIAMOND, Value.QUEEN)))
			stopper_count = 4;
		else if(this.contains(Suit.DIAMOND, Value.ACE))
			stopper_count = 3;
		else if(this.contains(Suit.DIAMOND, Value.KING))
			stopper_count = 2;
		else if(this.contains(Suit.DIAMOND, Value.QUEEN))
			stopper_count = 1;
		else
			stopper_count = 0;
		tr_stopper_di_di = stopper_count;
		
		if(this.contains(Suit.CLUB, Value.ACE) && 
				(this.contains(Suit.CLUB, Value.KING) || this.contains(Suit.CLUB, Value.QUEEN)))
			stopper_count = 4;
		else if(this.contains(Suit.CLUB, Value.ACE))
			stopper_count = 3;
		else if(this.contains(Suit.CLUB, Value.KING))
			stopper_count = 2;
		else if(this.contains(Suit.CLUB, Value.QUEEN))
			stopper_count = 1;
		else
			stopper_count = 0;
		tr_stopper_cl_cl = stopper_count;
		
		// TODO: losers
		
		// Quality
		boolean flag = false, flag_ak = false;
		if(this.contains(Suit.SPADE, Value.ACE) && this.contains(Suit.SPADE, Value.KING)) {
			flag_ak = true;
			if(num_spade > 8)
				this.quality_sp = 9;
			else if(num_spade > 7  && (this.contains(Suit.SPADE, Value.QUEEN) || this.contains(Suit.SPADE, Value.JACK)))
				this.quality_sp = 9;
			else if(num_spade > 6 && this.contains(Suit.SPADE, Value.QUEEN))
				this.quality_sp = 8;
			else if(num_spade > 5 && this.contains(Suit.SPADE, Value.QUEEN) && this.contains(Suit.SPADE, Value.TEN))
				this.quality_sp = 7;
			else flag = true;
		} else if(!flag_ak || (flag_ak && flag)) {
			if(num_spade > 5 && (points_spade >= 8 || honors_spade > 3))
				this.quality_sp = 6;
			else if(num_spade > 6)
				this.quality_sp = 5;
			else if(num_spade > 5 && (points_spade > 4 || honors_spade > 2))
				this.quality_sp = 5;
			else if(num_spade > 5)
				this.quality_sp = 4;
			else if(num_spade > 4 && (points_spade > 4 || honors_spade > 2))
				this.quality_sp = 4;
			else if(num_spade > 4)
				this.quality_sp = 3;
			else if(num_spade > 3 && (points_spade > 4 || honors_spade > 2))
				this.quality_sp = 3;
			else if(num_spade > 3)
				this.quality_sp = 2;
			else if(num_spade > 2)
				this.quality_sp = 1;
			else this.quality_sp = 0;
		} 
		
		flag = false; flag_ak = false;
		if(this.contains(Suit.HEART, Value.ACE) && this.contains(Suit.HEART, Value.KING)) {
			flag_ak = true;
			if(num_heart > 8) 
				this.quality_he = 9;
			else if(num_heart > 7  && (this.contains(Suit.HEART, Value.QUEEN) || this.contains(Suit.HEART, Value.JACK)))
				this.quality_he = 9;
			else if(num_heart > 6 && this.contains(Suit.HEART, Value.QUEEN))
				this.quality_he = 8;
			else if(num_heart > 5 && this.contains(Suit.HEART, Value.QUEEN) && this.contains(Suit.HEART, Value.TEN))
				this.quality_he = 7;
			else flag = true;
		} else if(!flag_ak || (flag_ak && flag)) {
			if(num_heart > 5 && (points_heart >= 8 || honors_heart > 3))
				this.quality_he = 6;
			else if(num_heart > 6)
				this.quality_he = 5;
			else if(num_heart > 5 && (points_heart > 4 || honors_heart > 2))
				this.quality_he = 5;
			else if(num_heart > 5)
				this.quality_he = 4;
			else if(num_heart > 4 && (points_heart > 4 || honors_heart > 2))
				this.quality_he = 4;
			else if(num_heart > 4)
				this.quality_he = 3;
			else if(num_heart > 3 && (points_heart > 4 || honors_heart > 2))
				this.quality_he = 3;
			else if(num_heart > 3)
				this.quality_he = 2;
			else if(num_heart > 2)
				this.quality_he = 1;
			else this.quality_he = 0;
		}
		
		flag = false; flag_ak = false;
		if(this.contains(Suit.DIAMOND, Value.ACE) && this.contains(Suit.DIAMOND, Value.KING)) {
			flag_ak = true;
			if(num_dia > 8) 
				this.quality_di = 9;
			else if(num_dia > 7  && (this.contains(Suit.DIAMOND, Value.QUEEN) || this.contains(Suit.DIAMOND, Value.JACK)))
				this.quality_di = 9;
			else if(num_dia > 6 && this.contains(Suit.DIAMOND, Value.QUEEN))
				this.quality_di = 8;
			else if(num_dia > 5 && this.contains(Suit.DIAMOND, Value.QUEEN) && this.contains(Suit.DIAMOND, Value.TEN))
				this.quality_di = 7;
			else flag = true;
		} else if(!flag_ak || (flag_ak && flag)) {
			if(num_dia > 5 && (points_dia >= 8 || honors_dia > 3))
				this.quality_di = 6;
			else if(num_dia > 6)
				this.quality_di = 5;
			else if(num_dia > 5 && (points_dia > 4 || honors_dia > 2))
				this.quality_di = 5;
			else if(num_dia > 5)
				this.quality_di = 4;
			else if(num_dia > 4 && (points_dia > 4 || honors_dia > 2))
				this.quality_di = 4;
			else if(num_dia > 4)
				this.quality_di = 3;
			else if(num_dia > 3 && (points_dia > 4 || honors_dia > 2))
				this.quality_di = 3;
			else if(num_dia > 3)
				this.quality_di = 2;
			else if(num_dia > 2)
				this.quality_di = 1;
			else this.quality_di = 0;
		}

		flag = false; flag_ak = false;
		if(this.contains(Suit.CLUB, Value.ACE) && this.contains(Suit.CLUB, Value.KING)) {
			flag_ak = true;
			if(num_club > 8) 
				this.quality_cl = 9;
			else if(num_club > 7  && (this.contains(Suit.CLUB, Value.QUEEN) || this.contains(Suit.CLUB, Value.JACK)))
				this.quality_cl = 9;
			else if(num_club > 6 && this.contains(Suit.CLUB, Value.QUEEN))
				this.quality_cl = 8;
			else if(num_club > 5 && this.contains(Suit.CLUB, Value.QUEEN) && this.contains(Suit.CLUB, Value.TEN))
				this.quality_cl = 7;
			else flag = true;
		} else if(!flag_ak || (flag_ak && flag)) {
			if(num_club > 5 && (points_club >= 8 || honors_club > 3))
				this.quality_cl = 6;
			else if(num_club > 6)
				this.quality_cl = 5;
			else if(num_club > 5 && (points_club > 4 || honors_club > 2))
				this.quality_cl = 5;
			else if(num_club > 5)
				this.quality_cl = 4;
			else if(num_club > 4 && (points_club > 4 || honors_club > 2))
				this.quality_cl = 4;
			else if(num_club > 4)
				this.quality_cl = 3;
			else if(num_club > 3 && (points_club > 4 || honors_club > 2))
				this.quality_cl = 3;
			else if(num_club > 3)
				this.quality_cl = 2;
			else if(num_club > 2)
				this.quality_cl = 1;
			else this.quality_cl = 0;
		}

		// Ratio
		ArrayList<SuitToFindRatio> suitsForRatio = new ArrayList<>(4);
		suitsForRatio.add(new SuitToFindRatio("spade", num_spade, points_spade));
		suitsForRatio.add(new SuitToFindRatio("heart", num_heart, points_heart));
		suitsForRatio.add(new SuitToFindRatio("dia", num_dia, points_dia));
		suitsForRatio.add(new SuitToFindRatio("club", num_club, points_club));
		Collections.sort(suitsForRatio);
		ratio = (suitsForRatio.get(2).hcp + suitsForRatio.get(3).hcp) * 1.0 / points_hc;
		
		// Unlos
		this.unlos_sp = calculateUnlos(this.getSuit(Suit.SPADE), Suit.SPADE);
		this.unlos_he = calculateUnlos(this.getSuit(Suit.HEART), Suit.HEART);
		this.unlos_di = calculateUnlos(this.getSuit(Suit.DIAMOND), Suit.DIAMOND);
		this.unlos_cl = calculateUnlos(this.getSuit(Suit.CLUB), Suit.CLUB);
		
		this.total_points_sp = this.points_hc + dp_sp_ha;
		this.total_points_he = this.points_hc + dp_he_ha;
		this.total_points_di = this.points_hc + dp_di_ha;
		this.total_points_cl = this.points_hc + dp_cl_ha;
	}
	
	private double calculateUnlos(List<Card> cards, Suit suit) {
		double temp_unlos = 0;
		
		if(cards.size() == 0) {
			temp_unlos = 0;
		} 
		
		else if(cards.size() == 1) {
			if(suitHasCard(cards, Value.ACE)) temp_unlos = 0;
			else temp_unlos = 1;
		} 
		
		else if(cards.size() == 2) {
			if(suitHasCard(cards, Value.ACE) && suitHasCard(cards, Value.KING)) temp_unlos = 0;
			else if(suitHasCard(cards, Value.ACE) && suitHasCard(cards, Value.QUEEN)) temp_unlos = 0.5;
			else if(suitHasCard(cards, Value.ACE)) temp_unlos = 1;
			else if(suitHasCard(cards, Value.KING)) temp_unlos = 1.5;
			else temp_unlos = 2;
		} 
		
		else if(cards.size() == 3) {
			if(suitHasCard(cards, Value.ACE)) {
				if(suitHasCard(cards, Value.KING)) {
					if(suitHasCard(cards, Value.QUEEN)) temp_unlos = 0;
					else if(suitHasCard(cards, Value.JACK)) temp_unlos = 0.5;
					else if(suitHasCard(cards, Value.TEN)) temp_unlos = 0.75;
					else temp_unlos = 1;
				} else if(suitHasCard(cards, Value.QUEEN)) {
					if(suitHasCard(cards, Value.JACK)) temp_unlos = 0.5;
					else if(suitHasCard(cards, Value.TEN)) temp_unlos = 1;
					else if(suitHasCard(cards, Value.NINE)) temp_unlos = 1.25;
					else temp_unlos = 1.5;
				} else if(suitHasCard(cards, Value.JACK)) {
					if(suitHasCard(cards, Value.TEN)) temp_unlos = 1.25;
					else if(suitHasCard(cards, Value.NINE)) temp_unlos = 1.5;
					else temp_unlos = 1.75;
				} else {
					temp_unlos = 2;
				}
			} else if(suitHasCard(cards, Value.KING)) {
				if(suitHasCard(cards, Value.QUEEN)) {
					if(suitHasCard(cards, Value.JACK)) temp_unlos = 1;
					else if(suitHasCard(cards, Value.TEN)) temp_unlos = 1;
					else temp_unlos = 1.25;
				} else if(suitHasCard(cards, Value.JACK)) {
					if(suitHasCard(cards, Value.TEN)) temp_unlos = 1.5;
					else if(suitHasCard(cards, Value.NINE)) temp_unlos = 1.75;
					else temp_unlos = 2;
				} else if(suitHasCard(cards, Value.TEN) && suitHasCard(cards, Value.NINE))
					temp_unlos = 2.25;
				else
					temp_unlos = 2.5;
			} else if(suitHasCard(cards, Value.QUEEN)) {
				if(suitHasCard(cards, Value.JACK)) {
					if(suitHasCard(cards, Value.TEN)) temp_unlos = 2;
					else temp_unlos = 2.25;
				} else if(suitHasCard(cards, Value.TEN) && suitHasCard(cards, Value.NINE))
					temp_unlos = 2.5;
				else 
					temp_unlos = 2.75;
			} else 
				temp_unlos = 3;
		} 
		
		else { 
			// Suit length > 3
			if(cards.get(3).compareTo(new Card(suit, Value.EIGHT)) > 0 &&
					cards.get(2).getValue().ordinal() == cards.get(3).getValue().ordinal() + 1)
				temp_unlos = 0;
			else temp_unlos = 0.5;
			temp_unlos -= 0.25 * cards.size();
			temp_unlos += 1.5;
			temp_unlos += calculateUnlos(cards.subList(0,3), suit);
		}
		
		return temp_unlos;
	}

	/*
	 * Check if the given number is present in the cards given
	 * Ideal usage: All the cards given should belong to the same suit
	 */
	private boolean suitHasCard(List<Card> cards, Value value) {
		for(Card card : cards)
			if(card.getValue().equals(value))
				return true;
		return false;
	}
	
	/*
	 * Returns sorted list of cards in the specified suit
	 */
	public List<Card> getSuit(Suit suit) {
		ArrayList<Card> list = new ArrayList<>();
		
		for(Card c : this.cards)
			if(c.getSuit().equals(suit))
				list.add(c);
		
		Collections.sort(list, Collections.reverseOrder());
		
		return list;
	}
	
	public String toString() {
		return this.cards.toString();
	}
}

class SuitToFindRatio implements Comparable<SuitToFindRatio> {
	String name;
	int len;
	public int hcp;
	
	public SuitToFindRatio(String name, int len, int hcp) {
		this.name = name;
		this.len = len;
		this.hcp = hcp;
	}
	
	public int compareTo(SuitToFindRatio s2) {
		if(this.len < s2.len) return -1;
		else if(this.len == s2.len && this.hcp < s2.hcp) return -1;
		else return 1;
	}
}
