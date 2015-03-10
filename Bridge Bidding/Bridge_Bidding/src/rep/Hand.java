package rep;

import java.util.ArrayList;

import rep.Card.Suit;
import rep.Card.Value;

public class Hand {
	private ArrayList<Card> cards;
	
	public Hand(Card c) {
		cards = new ArrayList<>(13);
		cards.add(c);
	}
	
	public Hand(ArrayList<Card> cl) {
		cards = new ArrayList<>(13);
		if(cl.size() <= 13)
			cards.addAll(cl);
		if(cards.size() == 13)
			calculateFeatureValues();
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
		if(cards.size() == 13) return true;
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
	private int points_hc, points_spade, points_heart, points_dia, points_club;
	private int controls, controls_spade, controls_heart, controls_dia, controls_club;
	private int highCards, highCards_spade, highCards_heart, highCards_dia, highCards_club;
	private int honors, honors_spade, honors_heart, honors_dia, honors_club;
	private int aces, ace_spade, ace_heart, ace_dia, ace_club;
	private int kings, king_spade, king_heart, king_dia, king_club;
	private int queens, queen_spade, queen_heart, queen_dia, queen_club;
	private int jacks, jack_spade, jack_heart, jack_dia, jack_club;
	private int tens, ten_spade, ten_heart, ten_dia, ten_club;
	private int rkcb_spade, rkcb_heart, rkcb_dia, rkcb_club;
	private int num_spade, num_heart, num_dia, num_club;
	
	/*
	 *  Distribution
	 */
	private int balanced;
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
	
	public int losers_sp_he, losers_sp_di, losers_sp_cl, losers_sp_ha,
				losers_he_sp, losers_he_di, losers_he_cl, losers_he_ha,
				losers_di_sp, losers_di_he, losers_di_cl, losers_di_ha,
				losers_cl_sp, losers_cl_he, losers_cl_di, losers_cl_ha;
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
	public float ratio;
	
	// Calculation function
	private void calculateFeatureValues() {
		
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
		
		tr_stopper_sp_sp = tr_stopper_sp_he = tr_stopper_sp_di = tr_stopper_sp_cl =
		tr_stopper_he_sp = tr_stopper_he_he = tr_stopper_he_di = tr_stopper_he_cl =
		tr_stopper_di_sp = tr_stopper_di_he = tr_stopper_di_di = tr_stopper_di_cl =
		tr_stopper_cl_sp = tr_stopper_cl_he = tr_stopper_cl_di = tr_stopper_cl_cl = 0;
		
		quality_sp = quality_he = quality_di = quality_cl = 0;
		ratio = 0;
		
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
		if(num_dia == max) longest_cl = 1;
		if(num_club == max) longest_di = 1;
		if(num_spade == min) shortest_sp = 1;
		if(num_heart == min) shortest_he = 1;
		if(num_dia == min) shortest_di = 1;
		if(num_club == min) shortest_cl = 1;
		
		intermediate_ha = intermediate_sp + intermediate_he+ intermediate_di + intermediate_cl;
		
		// TODO: Calculate losers
		
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
		
		// Quality
		
		boolean flag = false;
		if(this.contains(Suit.SPADE, Value.ACE) && this.contains(Suit.SPADE, Value.KING)) {
			if(num_spade > 8) 
				quality_sp = 9;
			else if(num_spade > 7  && (this.contains(Suit.SPADE, Value.QUEEN) || this.contains(Suit.SPADE, Value.JACK)))
				quality_sp = 9;
			else if(num_spade > 6 && this.contains(Suit.SPADE, Value.QUEEN))
				quality_sp = 8;
			else if(num_spade > 5 && this.contains(Suit.SPADE, Value.QUEEN) && this.contains(Suit.SPADE, Value.TEN))
				quality_sp = 7;
			else flag = true;
		} 
		if(flag) {
			if(num_spade > 5 && (points_spade >= 8 || honors_spade > 3))
				quality_sp = 6;
			else if(num_spade > 6)
				quality_sp = 5;
			else if(num_spade > 5 && (points_spade > 4 || honors_spade > 2))
				quality_sp = 5;
			else if(num_spade > 5)
				quality_sp = 4;
			else if(num_spade > 4 && (points_spade > 4 || honors_spade > 2))
				quality_sp = 4;
			else if(num_spade > 4)
				quality_sp = 3;
			else if(num_spade > 3 && (points_spade > 4 || honors_spade > 2))
				quality_sp = 3;
			else if(num_spade > 3)
				quality_sp = 2;
			else if(num_spade > 2)
				quality_sp = 1;
			else quality_sp = 0;
		}
		
		if(this.contains(Suit.HEART, Value.ACE) && this.contains(Suit.HEART, Value.KING)) {
			if(num_heart > 8) 
				quality_he = 9;
			else if(num_heart > 7  && (this.contains(Suit.HEART, Value.QUEEN) || this.contains(Suit.HEART, Value.JACK)))
				quality_he = 9;
			else if(num_heart > 6 && this.contains(Suit.HEART, Value.QUEEN))
				quality_he = 8;
			else if(num_heart > 5 && this.contains(Suit.HEART, Value.QUEEN) && this.contains(Suit.HEART, Value.TEN))
				quality_he = 7;
			else flag = true;
		} 
		if(flag) {
			if(num_heart > 5 && (points_heart >= 8 || honors_heart > 3))
				quality_he = 6;
			else if(num_heart > 6)
				quality_he = 5;
			else if(num_heart > 5 && (points_heart > 4 || honors_heart > 2))
				quality_he = 5;
			else if(num_heart > 5)
				quality_he = 4;
			else if(num_heart > 4 && (points_heart > 4 || honors_heart > 2))
				quality_he = 4;
			else if(num_heart > 4)
				quality_he = 3;
			else if(num_heart > 3 && (points_heart > 4 || honors_heart > 2))
				quality_he = 3;
			else if(num_heart > 3)
				quality_he = 2;
			else if(num_heart > 2)
				quality_he = 1;
			else quality_he = 0;
		}
		
		if(this.contains(Suit.DIAMOND, Value.ACE) && this.contains(Suit.DIAMOND, Value.KING)) {
			if(num_dia > 8) 
				quality_di = 9;
			else if(num_dia > 7  && (this.contains(Suit.DIAMOND, Value.QUEEN) || this.contains(Suit.DIAMOND, Value.JACK)))
				quality_di = 9;
			else if(num_dia > 6 && this.contains(Suit.DIAMOND, Value.QUEEN))
				quality_di = 8;
			else if(num_dia > 5 && this.contains(Suit.DIAMOND, Value.QUEEN) && this.contains(Suit.DIAMOND, Value.TEN))
				quality_di = 7;
			else flag = true;
		} 
		if(flag) {
			if(num_dia > 5 && (points_dia >= 8 || honors_dia > 3))
				quality_di = 6;
			else if(num_dia > 6)
				quality_di = 5;
			else if(num_dia > 5 && (points_dia > 4 || honors_dia > 2))
				quality_di = 5;
			else if(num_dia > 5)
				quality_di = 4;
			else if(num_dia > 4 && (points_dia > 4 || honors_dia > 2))
				quality_di = 4;
			else if(num_dia > 4)
				quality_di = 3;
			else if(num_dia > 3 && (points_dia > 4 || honors_dia > 2))
				quality_di = 3;
			else if(num_dia > 3)
				quality_di = 2;
			else if(num_dia > 2)
				quality_di = 1;
			else quality_di = 0;
		}

		if(this.contains(Suit.CLUB, Value.ACE) && this.contains(Suit.CLUB, Value.KING)) {
			if(num_club > 8) 
				quality_cl = 9;
			else if(num_club > 7  && (this.contains(Suit.CLUB, Value.QUEEN) || this.contains(Suit.CLUB, Value.JACK)))
				quality_cl = 9;
			else if(num_club > 6 && this.contains(Suit.CLUB, Value.QUEEN))
				quality_cl = 8;
			else if(num_club > 5 && this.contains(Suit.CLUB, Value.QUEEN) && this.contains(Suit.CLUB, Value.TEN))
				quality_cl = 7;
			else flag = true;
		} 
		if(flag) {
			if(num_club > 5 && (points_club >= 8 || honors_club > 3))
				quality_cl = 6;
			else if(num_club > 6)
				quality_cl = 5;
			else if(num_club > 5 && (points_club > 4 || honors_club > 2))
				quality_cl = 5;
			else if(num_club > 5)
				quality_cl = 4;
			else if(num_club > 4 && (points_club > 4 || honors_club > 2))
				quality_cl = 4;
			else if(num_club > 4)
				quality_cl = 3;
			else if(num_club > 3 && (points_club > 4 || honors_club > 2))
				quality_cl = 3;
			else if(num_club > 3)
				quality_cl = 2;
			else if(num_club > 2)
				quality_cl = 1;
			else quality_cl = 0;
		}

		// TODO: Ratio
		
	}
	
	/*
	 *  Functions to get feature values
	 */
	
	// Overall Hand
	
	public int getHCPoints() {
		return points_hc;
	}
	
	public int getControls() {
		return controls;
	}
	
	public int getHighCards() {
		return highCards;
	}
	
	public int getHonors() {
		return honors;
	}
	
	public int getAces() {
		return aces;
	}
	
	public int getKings() {
		return kings;
	}
	
	public int getQueens() {
		return queens;
	}
	
	public int getJacks() {
		return jacks;
	}
	
	public int getTens() {
		return tens;
	}
	
	// Spade features
	
	public int getNumSpades() {
		return num_spade;
	}
	
	public int getHCPointsSpade() {
		return points_spade;
	}
	
	public int getControlsSpade() {
		return controls_spade;
	}
	
	public int getHighCardsSpade() {
		return highCards_spade;
	}
	
	public int getHonorsSpade() {
		return honors_spade;
	}
	
	public int getAceSpade() {
		return ace_spade;
	}
	
	public int getKingSpade() {
		return king_spade;
	}
	
	public int getQueenSpade() {
		return queen_spade;
	}
	
	public int getJackSpade() {
		return jack_spade;
	}
	
	public int getTenSpade() {
		return ten_spade;
	}
	
	public int getRkcbSpade() {
		return rkcb_spade;
	}
	
	// Heart features
	
	public int getNumHearts() {
		return num_heart;
	}
	
	public int getHCPointsHeart() {
		return points_heart;
	}
	
	public int getControlsHeart() {
		return controls_heart;
	}
	
	public int getHighCardsHeart() {
		return highCards_heart;
	}
	
	public int getHonorsHeart() {
		return honors_heart;
	}
	
	public int getAceHeart() {
		return ace_heart;
	}
	
	public int getKingHeart() {
		return king_heart;
	}
	
	public int getQueenHeart() {
		return queen_heart;
	}
	
	public int getJackHeart() {
		return jack_heart;
	}
	
	public int getTenHeart() {
		return ten_heart;
	}
	
	public int getRkcbHeart() {
		return rkcb_heart;
	}
	
	// Diamond features
	
	public int getNumDias() {
		return num_dia;
	}
	
	public int getHCPointsDia() {
		return points_dia;
	}
	
	public int getControlsDia() {
		return controls_dia;
	}
	
	public int getHighCardsDia() {
		return highCards_dia;
	}
	
	public int getHonorsDia() {
		return honors_dia;
	}
	
	public int getAceDia() {
		return ace_dia;
	}
	
	public int getKingDia() {
		return king_dia;
	}
	
	public int getQueenDia() {
		return queen_dia;
	}
	
	public int getJackDia() {
		return jack_dia;
	}
	
	public int getTenDia() {
		return ten_dia;
	}
	
	public int getRkcbDia() {
		return rkcb_dia;
	}
	
	// Club features
	
	public int getNumClubs() {
		return num_club;
	}
	
	public int getHCPointsClub() {
		return points_club;
	}
	
	public int getControlsClub() {
		return controls_club;
	}
	
	public int getHighCardsClub() {
		return highCards_club;
	}
	
	public int getHonorsClub() {
		return honors_club;
	}
	
	public int getAceClub() {
		return ace_club;
	}
	
	public int getKingClub() {
		return king_club;
	}
	
	public int getQueenClub() {
		return queen_club;
	}
	
	public int getJackClub() {
		return jack_club;
	}
	
	public int getTenClub() {
		return ten_club;
	}
	
	public int getRkcbClub() {
		return rkcb_club;
	}
	
	public int getBalance() {
		return balanced;
	}
}
