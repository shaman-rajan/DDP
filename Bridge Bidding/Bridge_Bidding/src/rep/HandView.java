package rep;

import sml.Identifier;


public class HandView {
	
	/*
	 * Features and their values that describe the hand
	 * Level of certainty or uncertainty of correctness of values
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
	
	
	public HandView(Hand fullHand) {
		points_hc = fullHand.getHCPoints();
		controls = fullHand.getControls();
		highCards = fullHand.getHighCards();
		honors = fullHand.getHonors();
		aces = fullHand.getAces();
		kings = fullHand.getKings();
		queens = fullHand.getQueens();
		jacks = fullHand.getJacks();
		tens = fullHand.getTens();
		balanced = fullHand.getBalance();
		num_suits = fullHand.num_suits;
		lmaj = fullHand.lmaj;
		lmin = fullHand.lmin;
		intermediate_ha = fullHand.intermediate_ha;
		ratio = fullHand.ratio;
		
		points_spade = fullHand.getHCPointsSpade();
		controls_spade = fullHand.getControlsSpade();
		highCards_spade = fullHand.getHighCardsSpade();
		honors_spade = fullHand.getHonorsSpade();
		ace_spade = fullHand.getAceSpade();
		king_spade = fullHand.getKingSpade();
		queen_spade = fullHand.getQueenSpade();
		jack_spade = fullHand.getJackSpade();
		ten_spade = fullHand.getTenSpade();
		rkcb_spade = fullHand.getRkcbSpade();
		num_spade = fullHand.getNumSpades();
		longest_sp = fullHand.longest_sp;
		shortest_sp = fullHand.shortest_sp;
		intermediate_sp = fullHand.intermediate_sp;
		stopper_sp = fullHand.stopper_sp;
		quality_sp = fullHand.quality_sp;
		
		points_heart = fullHand.getHCPointsHeart();
		controls_heart = fullHand.getControlsHeart();
		highCards_heart = fullHand.getHighCardsHeart();
		honors_heart = fullHand.getHonorsHeart();
		ace_heart = fullHand.getAceHeart();
		king_heart = fullHand.getKingHeart();
		queen_heart = fullHand.getQueenHeart();
		jack_heart = fullHand.getJackHeart();
		ten_heart = fullHand.getTenHeart();
		rkcb_heart = fullHand.getRkcbHeart();
		num_heart = fullHand.getNumHearts();
		longest_he = fullHand.longest_he;
		shortest_he = fullHand.shortest_he;
		intermediate_he = fullHand.intermediate_he;
		stopper_he = fullHand.stopper_he;
		quality_he = fullHand.quality_he;
		
		points_dia = fullHand.getHCPointsDia();
		controls_dia = fullHand.getControlsDia();
		highCards_dia = fullHand.getHighCardsDia();
		honors_dia = fullHand.getHonorsDia();
		ace_dia = fullHand.getAceDia();
		king_dia = fullHand.getKingDia();
		queen_dia = fullHand.getQueenDia();
		jack_dia = fullHand.getJackDia();
		ten_dia = fullHand.getTenDia();
		rkcb_dia = fullHand.getRkcbDia();
		num_dia = fullHand.getNumDias();
		longest_di = fullHand.longest_di;
		shortest_di = fullHand.shortest_di;
		intermediate_di = fullHand.intermediate_di;
		stopper_di = fullHand.stopper_di;
		quality_di = fullHand.quality_di;
		
		points_club = fullHand.getHCPointsClub();
		controls_club = fullHand.getControlsClub();
		highCards_club = fullHand.getHighCardsClub();
		honors_club = fullHand.getHonorsClub();
		ace_club = fullHand.getAceClub();
		king_club = fullHand.getKingClub();
		queen_club = fullHand.getQueenClub();
		jack_club = fullHand.getJackClub();
		ten_club = fullHand.getTenClub();
		rkcb_club = fullHand.getRkcbClub();
		num_club = fullHand.getNumClubs();
		longest_cl = fullHand.longest_cl;
		shortest_cl = fullHand.shortest_cl;
		intermediate_cl = fullHand.intermediate_cl;
		stopper_cl = fullHand.stopper_cl;
		quality_cl = fullHand.quality_cl;
		
		dp_sp_he = fullHand.dp_sp_he;
		dp_sp_di = fullHand.dp_sp_di;
		dp_sp_cl = fullHand.dp_sp_cl;
		dp_sp_ha = fullHand.dp_sp_ha;
		dp_he_sp = fullHand.dp_he_sp;
		dp_he_di = fullHand.dp_he_di;
		dp_he_cl = fullHand.dp_he_cl;
		dp_he_ha = fullHand.dp_he_ha;
		dp_di_sp = fullHand.dp_di_sp;
		dp_di_he = fullHand.dp_di_he;
		dp_di_cl = fullHand.dp_di_cl;
		dp_di_ha = fullHand.dp_di_ha;
		dp_cl_sp = fullHand.dp_cl_sp;
		dp_cl_he = fullHand.dp_cl_he;
		dp_cl_di = fullHand.dp_cl_di;
		dp_cl_ha = fullHand.dp_cl_ha;
		
		losers_sp_he = fullHand.losers_sp_he;
		losers_sp_di = fullHand.losers_sp_di;
		losers_sp_cl = fullHand.losers_sp_cl;
		losers_sp_ha = fullHand.losers_sp_ha;
		losers_he_sp = fullHand.losers_he_sp;
		losers_he_di = fullHand.losers_he_di;
		losers_he_cl = fullHand.losers_he_cl;
		losers_he_ha = fullHand.losers_he_ha;
		losers_di_sp = fullHand.losers_di_sp;
		losers_di_he = fullHand.losers_di_he;
		losers_di_cl = fullHand.losers_di_cl;
		losers_di_ha = fullHand.losers_di_ha;
		losers_cl_sp = fullHand.losers_cl_sp;
		losers_cl_he = fullHand.losers_cl_he;
		losers_cl_di = fullHand.losers_cl_di;
		losers_cl_ha = fullHand.losers_cl_ha;
		
		tr_stopper_sp_sp = fullHand.tr_stopper_sp_sp;
		tr_stopper_sp_he = fullHand.tr_stopper_sp_he;
		tr_stopper_sp_di = fullHand.tr_stopper_sp_di;
		tr_stopper_sp_cl = fullHand.tr_stopper_sp_cl;
		tr_stopper_he_sp = fullHand.tr_stopper_he_sp;
		tr_stopper_he_he = fullHand.tr_stopper_he_he;
		tr_stopper_he_di = fullHand.tr_stopper_he_di;
		tr_stopper_he_cl = fullHand.tr_stopper_he_cl;
		tr_stopper_di_sp = fullHand.tr_stopper_di_sp;
		tr_stopper_di_he = fullHand.tr_stopper_di_he;
		tr_stopper_di_di = fullHand.tr_stopper_di_di;
		tr_stopper_di_cl = fullHand.tr_stopper_di_cl;
		tr_stopper_cl_sp = fullHand.tr_stopper_cl_sp;
		tr_stopper_cl_he = fullHand.tr_stopper_cl_he;
		tr_stopper_cl_di = fullHand.tr_stopper_cl_di;
		tr_stopper_cl_cl = fullHand.tr_stopper_cl_cl;
	}
	
	public HandView(HandView hv) {
		points_hc = hv.getHCPoints();
		controls = hv.getControls();
		highCards = hv.getHighCards();
		honors = hv.getHonors();
		aces = hv.getAces();
		kings = hv.getKings();
		queens = hv.getQueens();
		jacks = hv.getJacks();
		tens = hv.getTens();
		balanced = hv.getBalance();
		num_suits = hv.num_suits;
		lmaj = hv.lmaj;
		lmin = hv.lmin;
		intermediate_ha = hv.intermediate_ha;
		ratio = hv.ratio;
		
		points_spade = hv.getHCPointsSpade();
		controls_spade = hv.getControlsSpade();
		highCards_spade = hv.getHighCardsSpade();
		honors_spade = hv.getHonorsSpade();
		ace_spade = hv.getAceSpade();
		king_spade = hv.getKingSpade();
		queen_spade = hv.getQueenSpade();
		jack_spade = hv.getJackSpade();
		ten_spade = hv.getTenSpade();
		rkcb_spade = hv.getRkcbSpade();
		num_spade = hv.getNumSpades();
		longest_sp = hv.longest_sp;
		shortest_sp = hv.shortest_sp;
		intermediate_sp = hv.intermediate_sp;
		stopper_sp = hv.stopper_sp;
		quality_sp = hv.quality_sp;
		
		points_heart = hv.getHCPointsHeart();
		controls_heart = hv.getControlsHeart();
		highCards_heart = hv.getHighCardsHeart();
		honors_heart = hv.getHonorsHeart();
		ace_heart = hv.getAceHeart();
		king_heart = hv.getKingHeart();
		queen_heart = hv.getQueenHeart();
		jack_heart = hv.getJackHeart();
		ten_heart = hv.getTenHeart();
		rkcb_heart = hv.getRkcbHeart();
		num_heart = hv.getNumHearts();
		longest_he = hv.longest_he;
		shortest_he = hv.shortest_he;
		intermediate_he = hv.intermediate_he;
		stopper_he = hv.stopper_he;
		quality_he = hv.quality_he;
		
		points_dia = hv.getHCPointsDia();
		controls_dia = hv.getControlsDia();
		highCards_dia = hv.getHighCardsDia();
		honors_dia = hv.getHonorsDia();
		ace_dia = hv.getAceDia();
		king_dia = hv.getKingDia();
		queen_dia = hv.getQueenDia();
		jack_dia = hv.getJackDia();
		ten_dia = hv.getTenDia();
		rkcb_dia = hv.getRkcbDia();
		num_dia = hv.getNumDias();
		longest_di = hv.longest_di;
		shortest_di = hv.shortest_di;
		intermediate_di = hv.intermediate_di;
		stopper_di = hv.stopper_di;
		quality_di = hv.quality_di;
		
		points_club = hv.getHCPointsClub();
		controls_club = hv.getControlsClub();
		highCards_club = hv.getHighCardsClub();
		honors_club = hv.getHonorsClub();
		ace_club = hv.getAceClub();
		king_club = hv.getKingClub();
		queen_club = hv.getQueenClub();
		jack_club = hv.getJackClub();
		ten_club = hv.getTenClub();
		rkcb_club = hv.getRkcbClub();
		num_club = hv.getNumClubs();
		longest_cl = hv.longest_cl;
		shortest_cl = hv.shortest_cl;
		intermediate_cl = hv.intermediate_cl;
		stopper_cl = hv.stopper_cl;
		quality_cl = hv.quality_cl;
		
		dp_sp_he = hv.dp_sp_he;
		dp_sp_di = hv.dp_sp_di;
		dp_sp_cl = hv.dp_sp_cl;
		dp_sp_ha = hv.dp_sp_ha;
		dp_he_sp = hv.dp_he_sp;
		dp_he_di = hv.dp_he_di;
		dp_he_cl = hv.dp_he_cl;
		dp_he_ha = hv.dp_he_ha;
		dp_di_sp = hv.dp_di_sp;
		dp_di_he = hv.dp_di_he;
		dp_di_cl = hv.dp_di_cl;
		dp_di_ha = hv.dp_di_ha;
		dp_cl_sp = hv.dp_cl_sp;
		dp_cl_he = hv.dp_cl_he;
		dp_cl_di = hv.dp_cl_di;
		dp_cl_ha = hv.dp_cl_ha;
		
		losers_sp_he = hv.losers_sp_he;
		losers_sp_di = hv.losers_sp_di;
		losers_sp_cl = hv.losers_sp_cl;
		losers_sp_ha = hv.losers_sp_ha;
		losers_he_sp = hv.losers_he_sp;
		losers_he_di = hv.losers_he_di;
		losers_he_cl = hv.losers_he_cl;
		losers_he_ha = hv.losers_he_ha;
		losers_di_sp = hv.losers_di_sp;
		losers_di_he = hv.losers_di_he;
		losers_di_cl = hv.losers_di_cl;
		losers_di_ha = hv.losers_di_ha;
		losers_cl_sp = hv.losers_cl_sp;
		losers_cl_he = hv.losers_cl_he;
		losers_cl_di = hv.losers_cl_di;
		losers_cl_ha = hv.losers_cl_ha;
		
		tr_stopper_sp_sp = hv.tr_stopper_sp_sp;
		tr_stopper_sp_he = hv.tr_stopper_sp_he;
		tr_stopper_sp_di = hv.tr_stopper_sp_di;
		tr_stopper_sp_cl = hv.tr_stopper_sp_cl;
		tr_stopper_he_sp = hv.tr_stopper_he_sp;
		tr_stopper_he_he = hv.tr_stopper_he_he;
		tr_stopper_he_di = hv.tr_stopper_he_di;
		tr_stopper_he_cl = hv.tr_stopper_he_cl;
		tr_stopper_di_sp = hv.tr_stopper_di_sp;
		tr_stopper_di_he = hv.tr_stopper_di_he;
		tr_stopper_di_di = hv.tr_stopper_di_di;
		tr_stopper_di_cl = hv.tr_stopper_di_cl;
		tr_stopper_cl_sp = hv.tr_stopper_cl_sp;
		tr_stopper_cl_he = hv.tr_stopper_cl_he;
		tr_stopper_cl_di = hv.tr_stopper_cl_di;
		tr_stopper_cl_cl = hv.tr_stopper_cl_cl;
	}
	
	public HandView() {
		// No info available to start with

		/*
		 * Initialization
		 */
		points_hc = points_club = points_dia = points_heart = points_spade = -1;
		controls = controls_club = controls_dia = controls_heart = controls_spade = -1;
		highCards = highCards_club = highCards_dia = highCards_heart = highCards_spade = -1;
		honors = honors_club = honors_dia = honors_heart = honors_spade = -1;
		aces = ace_club = ace_dia = ace_heart = ace_spade = -1;
		kings = king_club = king_dia = king_heart = king_spade = -1;
		queens = queen_club = queen_dia = queen_heart = queen_spade = -1;
		jacks = jack_club = jack_dia = jack_heart = jack_spade = -1;
		tens = ten_club = ten_dia = ten_heart = ten_spade = -1;
		rkcb_club = rkcb_dia = rkcb_heart = rkcb_spade = -1;
		num_spade = num_heart = num_dia = num_club = -1;
		
		balanced = -1;
		dp_sp_he = dp_sp_di = dp_sp_cl = dp_sp_ha = -1;
		dp_he_sp = dp_he_di = dp_he_cl = dp_he_ha = -1;
		dp_di_sp = dp_di_he = dp_di_cl = dp_di_ha = -1;
		dp_cl_sp = dp_cl_he = dp_cl_di = dp_cl_ha = -1;
		num_suits = -1;
		lmaj = lmin = -1;
		longest_sp = longest_he = longest_di = longest_cl = -1;
		shortest_sp = shortest_he = shortest_di = shortest_cl = -1;
		
		intermediate_sp = intermediate_he = intermediate_di = intermediate_cl = intermediate_ha = -1;
		
		tr_stopper_sp_sp = tr_stopper_sp_he = tr_stopper_sp_di = tr_stopper_sp_cl =
		tr_stopper_he_sp = tr_stopper_he_he = tr_stopper_he_di = tr_stopper_he_cl =
		tr_stopper_di_sp = tr_stopper_di_he = tr_stopper_di_di = tr_stopper_di_cl =
		tr_stopper_cl_sp = tr_stopper_cl_he = tr_stopper_cl_di = tr_stopper_cl_cl = -1;
		
		quality_sp = quality_he = quality_di = quality_cl = -1;
		ratio = -1;
	}
	
	/*
	 * Interface with SOAR
	 */
	public void addToSoarIdentifier(Identifier obj) {
		// TODO: Add details of the view to the SOAR identifier
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
