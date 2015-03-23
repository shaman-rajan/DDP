package rep;

import sml.Identifier;
import sml.IntElement;
import sml.WMElement;


public class HandView implements Cloneable {
	
	public int points_hc_low, points_hc_high, 
				points_spade_low, points_spade_high, 
				points_heart_low, points_heart_high, 
				points_dia_low, points_dia_high, 
				points_club_low, points_club_high;
	public int controls_low, controls_high,
				controls_spade_low, controls_spade_high, 
				controls_heart_low, controls_heart_high,
				controls_dia_low, controls_dia_high,
				controls_club_low, controls_club_high;
	public int highCards_low, highCards_high,
				highCards_spade_low, highCards_spade_high, 
				highCards_heart_low, highCards_heart_high,
				highCards_dia_low, highCards_dia_high,
				highCards_club_low, highCards_club_high;
				
	public int honors_low, honors_high,
				honors_spade_low, honors_spade_high, 
				honors_heart_low, honors_heart_high,
				honors_dia_low, honors_dia_high,
				honors_club_low, honors_club_high;
	public int aces, ace_spade, ace_heart, ace_dia, ace_club;
	public int kings, king_spade, king_heart, king_dia, king_club;
	public int queens, queen_spade, queen_heart, queen_dia, queen_club;
	public int jacks, jack_spade, jack_heart, jack_dia, jack_club;
	public int tens, ten_spade, ten_heart, ten_dia, ten_club;
	public int rkcb_spade, rkcb_heart, rkcb_dia, rkcb_club;
	public int num_spade_low, num_spade_high, 
				num_heart_low, num_heart_high,
				num_dia_low, num_dia_high,
				num_club_low, num_club_high;

	public int balanced_low, balanced_high;
	
	public int dp_sp_he, dp_sp_di, dp_sp_cl, dp_sp_ha,
				dp_he_sp, dp_he_di, dp_he_cl, dp_he_ha,
				dp_di_sp, dp_di_he, dp_di_cl, dp_di_ha,
				dp_cl_sp, dp_cl_he, dp_cl_di, dp_cl_ha;
	public int num_suits;
	public int lmaj, lmin;
	public int longest_sp, longest_he, longest_di, longest_cl;
	public int shortest_sp, shortest_he, shortest_di, shortest_cl;
	
	public int biddable_sp, biddable_he, biddable_di, biddable_cl;
	
	public float intermediate_sp_low, intermediate_sp_high,
				intermediate_he_low, intermediate_he_high, 
				intermediate_di_low, intermediate_di_high,
				intermediate_cl_low, intermediate_cl_high,
				intermediate_ha_low, intermediate_ha_high;
	
	public int stopper_sp, stopper_he, stopper_di, stopper_cl;
	
	public int tr_stopper_sp_sp, tr_stopper_sp_he, tr_stopper_sp_di, tr_stopper_sp_cl,
				tr_stopper_he_sp, tr_stopper_he_he, tr_stopper_he_di, tr_stopper_he_cl,
				tr_stopper_di_sp, tr_stopper_di_he, tr_stopper_di_di, tr_stopper_di_cl,
				tr_stopper_cl_sp, tr_stopper_cl_he, tr_stopper_cl_di, tr_stopper_cl_cl;
	
	public int losers_sp_he, losers_sp_di, losers_sp_cl, losers_sp_ha,
				losers_he_sp, losers_he_di, losers_he_cl, losers_he_ha,
				losers_di_sp, losers_di_he, losers_di_cl, losers_di_ha,
				losers_cl_sp, losers_cl_he, losers_cl_di, losers_cl_ha;
	
	public int quality_sp, quality_he, quality_di, quality_cl;
	
	public double ratio_low, ratio_high;
	
	public HandView(Hand fullHand) {
		this.points_hc_low = this.points_hc_high = fullHand.points_hc;
		this.points_spade_low = this.points_spade_high = fullHand.points_spade;
		this.points_heart_low = this.points_heart_high = fullHand.points_heart;
		this.points_dia_low = this.points_dia_high = fullHand.points_dia;
		this.points_club_low = this.points_club_high = fullHand.points_club;
		
		this.controls_low = this.controls_high = fullHand.controls;
		this.controls_spade_low = this.controls_spade_high = fullHand.controls_spade;
		this.controls_heart_low = this.controls_heart_high = fullHand.controls_heart;
		this.controls_dia_low = this.controls_dia_high = fullHand.controls_dia;
		this.controls_club_low = this.controls_club_high = fullHand.controls_club;

		this.highCards_low = this.highCards_high = fullHand.highCards;
		this.highCards_spade_low = this.highCards_spade_high = fullHand.highCards_spade;
		this.highCards_heart_low = this.highCards_heart_high = fullHand.highCards_heart;
		this.highCards_dia_low = this.highCards_dia_high = fullHand.highCards_dia;
		this.highCards_club_low = this.highCards_club_high = fullHand.highCards_club;

		this.honors_low = this.honors_high = fullHand.honors;
		this.honors_spade_low = this.honors_spade_high = fullHand.honors_spade;
		this.honors_heart_low = this.honors_heart_high = fullHand.honors_heart;
		this.honors_dia_low = this.honors_dia_high = fullHand.honors_dia;
		this.honors_club_low = this.honors_club_high = fullHand.honors_club;
		
		this.aces = fullHand.aces;
		this.ace_club = fullHand.ace_club;
		this.ace_dia = fullHand.ace_dia;
		this.ace_heart = fullHand.ace_heart;
		this.ace_spade = fullHand.ace_spade;

		this.kings = fullHand.kings;
		this.king_club = fullHand.king_club;
		this.king_dia = fullHand.king_dia;
		this.king_heart = fullHand.king_heart;
		this.king_spade = fullHand.king_spade;
		
		this.queens = fullHand.queens;
		this.queen_club = fullHand.queen_club;
		this.queen_dia = fullHand.queen_dia;
		this.queen_heart = fullHand.queen_heart;
		this.queen_spade = fullHand.queen_spade;
		
		this.jacks = fullHand.jacks;
		this.jack_club = fullHand.jack_club;
		this.jack_dia = fullHand.jack_dia;
		this.jack_heart = fullHand.jack_heart;
		this.jack_spade = fullHand.jack_spade;
		
		this.tens = fullHand.tens;
		this.ten_club = fullHand.ten_club;
		this.ten_dia = fullHand.ten_dia;
		this.ten_heart = fullHand.ten_heart;
		this.ten_spade = fullHand.ten_spade;
		
		this.rkcb_club = fullHand.rkcb_club;
		this.rkcb_dia = fullHand.rkcb_dia;
		this.rkcb_heart = fullHand.rkcb_heart;
		this.rkcb_spade = fullHand.rkcb_spade;
		
		this.num_spade_low = this.num_spade_high = fullHand.num_spade;
		this.num_heart_low = this.num_heart_high = fullHand.num_heart;
		this.num_dia_low = this.num_dia_high = fullHand.num_dia;
		this.num_club_low = this.num_club_high = fullHand.num_club;
		
		this.balanced_low = this.balanced_high = fullHand.balanced;
		
		this.dp_sp_he = fullHand.dp_sp_he;
		this.dp_sp_di = fullHand.dp_sp_di;
		this.dp_sp_cl = fullHand.dp_sp_cl;
		this.dp_sp_ha = fullHand.dp_sp_ha;
		this.dp_he_sp = fullHand.dp_he_sp;
		this.dp_he_di = fullHand.dp_he_di;
		this.dp_he_cl = fullHand.dp_he_cl;
		this.dp_he_ha = fullHand.dp_he_ha;
		this.dp_di_sp = fullHand.dp_di_sp;
		this.dp_di_he = fullHand.dp_di_he;
		this.dp_di_cl = fullHand.dp_di_cl;
		this.dp_di_ha = fullHand.dp_di_ha;
		this.dp_cl_sp = fullHand.dp_cl_sp;
		this.dp_cl_he = fullHand.dp_cl_he;
		this.dp_cl_di = fullHand.dp_cl_di;
		this.dp_cl_ha = fullHand.dp_cl_ha;
		
		this.num_suits = fullHand.num_suits;
		this.lmaj = fullHand.lmaj;
		this.lmin = fullHand.lmin;
		
		this.longest_sp = fullHand.longest_sp;
		this.longest_he = fullHand.longest_he;
		this.longest_di = fullHand.longest_di;
		this.longest_cl = fullHand.longest_cl;
		
		this.shortest_sp = fullHand.shortest_sp;
		this.shortest_he = fullHand.shortest_he;
		this.shortest_di = fullHand.shortest_di;
		this.shortest_cl = fullHand.shortest_cl;
		
		this.intermediate_ha_low = this.intermediate_ha_high = fullHand.intermediate_ha;
		this.intermediate_sp_low = this.intermediate_sp_high = fullHand.intermediate_sp;
		this.intermediate_he_low = this.intermediate_he_high = fullHand.intermediate_he;
		this.intermediate_di_low = this.intermediate_di_high = fullHand.intermediate_di;
		this.intermediate_cl_low = this.intermediate_cl_high = fullHand.intermediate_cl;
		
		this.stopper_sp = fullHand.stopper_sp;
		this.stopper_he = fullHand.stopper_he;
		this.stopper_di = fullHand.stopper_di;
		this.stopper_cl = fullHand.stopper_cl;
		
		this.tr_stopper_sp_he = fullHand.tr_stopper_sp_he;
		this.tr_stopper_sp_di = fullHand.tr_stopper_sp_di;
		this.tr_stopper_sp_cl = fullHand.tr_stopper_sp_cl;
		this.tr_stopper_he_sp = fullHand.tr_stopper_he_sp;
		this.tr_stopper_he_di = fullHand.tr_stopper_he_di;
		this.tr_stopper_he_cl = fullHand.tr_stopper_he_cl;
		this.tr_stopper_di_sp = fullHand.tr_stopper_di_sp;
		this.tr_stopper_di_he = fullHand.tr_stopper_di_he;
		this.tr_stopper_di_cl = fullHand.tr_stopper_di_cl;
		this.tr_stopper_cl_sp = fullHand.tr_stopper_cl_sp;
		this.tr_stopper_cl_he = fullHand.tr_stopper_cl_he;
		this.tr_stopper_cl_di = fullHand.tr_stopper_cl_di;
			
		this.quality_sp = fullHand.quality_sp;
		this.quality_he = fullHand.quality_he;
		this.quality_di = fullHand.quality_di;
		this.quality_cl = fullHand.quality_cl;
		
		ratio_low = ratio_high = fullHand.ratio;
	}
	
	public HandView(HandView hv) {
		copyValues(this, hv);
	}
	
	public HandView() {
		points_hc_low = points_hc_high = -1;
		points_spade_low = points_spade_high = -1; 
		points_heart_low = points_heart_high = -1;
		points_dia_low = points_dia_high = -1;
		points_club_low = points_club_high = -1;
		controls_low = controls_high = -1;
		controls_spade_low = controls_spade_high = -1; 
		controls_heart_low = controls_heart_high = -1;
		controls_dia_low = controls_dia_high = -1;
		controls_club_low = controls_club_high = -1;;
		highCards_low = highCards_high = -1;
		highCards_spade_low = highCards_spade_high = -1; 
		highCards_heart_low = highCards_heart_high = -1;
		highCards_dia_low = highCards_dia_high = -1;
		highCards_club_low = highCards_club_high = -1;;
				
		honors_low = honors_high = -1;
		honors_spade_low = honors_spade_high = -1; 
		honors_heart_low = honors_heart_high = -1;
		honors_dia_low = honors_dia_high = -1;
		honors_club_low = honors_club_high = -1;
		aces = ace_spade = ace_heart = ace_dia = ace_club = -1;
		kings = king_spade = king_heart = king_dia = king_club = -1;
		queens = queen_spade = queen_heart = queen_dia = queen_club = -1;
		jacks = jack_spade = jack_heart = jack_dia = jack_club = -1;
		tens = ten_spade = ten_heart = ten_dia = ten_club = -1;
		rkcb_spade = rkcb_heart = rkcb_dia = rkcb_club = -1;
		
		num_spade_low = num_spade_high = 
		num_heart_low = num_heart_high =
		num_dia_low = num_dia_high =
		num_club_low = num_club_high = -1;
		
		balanced_low = balanced_high = -1;
		
		dp_sp_he = dp_sp_di = dp_sp_cl = dp_sp_ha =
		dp_he_sp = dp_he_di = dp_he_cl = dp_he_ha =
		dp_di_sp = dp_di_he = dp_di_cl = dp_di_ha =
		dp_cl_sp = dp_cl_he = dp_cl_di = dp_cl_ha = -1;
		num_suits = -1;
		lmaj = lmin = -1;
		longest_sp = longest_he = longest_di = longest_cl = -1;
		shortest_sp = shortest_he = shortest_di = shortest_cl = -1;
		
		biddable_sp = biddable_he = biddable_di = biddable_cl = -1;
		
		intermediate_sp_low = intermediate_sp_high =
		intermediate_he_low = intermediate_he_high = 
		intermediate_di_low = intermediate_di_high =
		intermediate_cl_low = intermediate_cl_high =
		intermediate_ha_low = intermediate_ha_high = -1;

		stopper_sp = stopper_he = stopper_di = stopper_cl = -1;
		
		tr_stopper_sp_sp = tr_stopper_sp_he = tr_stopper_sp_di = tr_stopper_sp_cl =
		tr_stopper_he_sp = tr_stopper_he_he = tr_stopper_he_di = tr_stopper_he_cl =
		tr_stopper_di_sp = tr_stopper_di_he = tr_stopper_di_di = tr_stopper_di_cl =
		tr_stopper_cl_sp = tr_stopper_cl_he = tr_stopper_cl_di = tr_stopper_cl_cl = -1;
		
		losers_sp_he = losers_sp_di = losers_sp_cl = losers_sp_ha =
		losers_he_sp = losers_he_di = losers_he_cl = losers_he_ha =
		losers_di_sp = losers_di_he = losers_di_cl = losers_di_ha =
		losers_cl_sp = losers_cl_he = losers_cl_di = losers_cl_ha = -1;
		
		quality_sp = quality_he = quality_di = quality_cl = -1;
		
		ratio_low = ratio_high = -1;
	}
	
	/*
	 * Interface with SOAR
	 */
	public void addToSoarIdentifier(Identifier view) {
		view.CreateIntWME("hcp_low", this.points_hc_low);
		view.CreateIntWME("hcp_high", this.points_hc_high);
		view.CreateIntWME("hcp_sp_low", this.points_spade_low);
		view.CreateIntWME("hcp_sp_high", this.points_spade_high);
		view.CreateIntWME("hcp_he_low", this.points_heart_low);
		view.CreateIntWME("hcp_he_high", this.points_heart_high);
		view.CreateIntWME("hcp_di_low", this.points_dia_low);
		view.CreateIntWME("hcp_di_high", this.points_dia_high);
		view.CreateIntWME("hcp_cl_low", this.points_club_low);
		view.CreateIntWME("hcp_cl_high", this.points_club_high);

		view.CreateIntWME("controls_low", this.controls_low);
		view.CreateIntWME("controls_high", this.controls_high);
		view.CreateIntWME("controls_sp_low", this.controls_spade_low);
		view.CreateIntWME("controls_sp_high", this.controls_spade_high);
		view.CreateIntWME("controls_he_low", this.controls_heart_low);
		view.CreateIntWME("controls_he_high", this.controls_heart_high);
		view.CreateIntWME("controls_di_low", this.controls_dia_low);
		view.CreateIntWME("controls_di_high", this.controls_dia_high);
		view.CreateIntWME("controls_cl_low", this.controls_club_low);
		view.CreateIntWME("controls_cl_high", this.controls_club_high);

		view.CreateIntWME("hc_low", this.highCards_low);
		view.CreateIntWME("hc_high", this.highCards_high);
		view.CreateIntWME("hc_sp_low", this.highCards_spade_low);
		view.CreateIntWME("hc_sp_high", this.highCards_spade_high);
		view.CreateIntWME("hc_he_low", this.highCards_heart_low);
		view.CreateIntWME("hc_he_high", this.highCards_heart_high);
		view.CreateIntWME("hc_di_low", this.highCards_dia_low);
		view.CreateIntWME("hc_di_high", this.highCards_dia_high);
		view.CreateIntWME("hc_cl_low", this.highCards_club_low);
		view.CreateIntWME("hc_cl_high", this.highCards_club_high);
		
		view.CreateIntWME("honors_low", this.honors_low);
		view.CreateIntWME("honors_high", this.honors_high);
		view.CreateIntWME("honors_sp_low", this.honors_spade_low);
		view.CreateIntWME("honors_sp_high", this.honors_spade_high);
		view.CreateIntWME("honors_he_low", this.honors_heart_low);
		view.CreateIntWME("honors_he_high", this.honors_heart_high);
		view.CreateIntWME("honors_di_low", this.honors_dia_low);
		view.CreateIntWME("honors_di_high", this.honors_dia_high);
		view.CreateIntWME("honors_cl_low", this.honors_club_low);
		view.CreateIntWME("honors_cl_high", this.honors_club_high);
		
		view.CreateIntWME("aces", this.aces);
		view.CreateIntWME("kings", this.kings);
		view.CreateIntWME("queens", this.queens);
		view.CreateIntWME("jacks", this.jacks);
		view.CreateIntWME("tens", this.tens);
		view.CreateIntWME("ace_sp", this.ace_spade);
		view.CreateIntWME("king_sp", this.king_spade);
		view.CreateIntWME("queen_sp", this.queen_spade);
		view.CreateIntWME("jack_sp", this.jack_spade);
		view.CreateIntWME("ten_he", this.ten_heart);
		view.CreateIntWME("ace_he", this.ace_heart);
		view.CreateIntWME("king_he", this.king_heart);
		view.CreateIntWME("queen_he", this.queen_heart);
		view.CreateIntWME("jack_he", this.jack_heart);
		view.CreateIntWME("ten_he", this.ten_heart);
		view.CreateIntWME("ace_di", this.ace_dia);
		view.CreateIntWME("king_di", this.king_dia);
		view.CreateIntWME("queen_di", this.queen_dia);
		view.CreateIntWME("jack_di", this.jack_dia);
		view.CreateIntWME("ten_di", this.ten_dia);
		view.CreateIntWME("ace_cl", this.ace_club);
		view.CreateIntWME("king_cl", this.king_club);
		view.CreateIntWME("queen_cl", this.queen_club);
		view.CreateIntWME("jack_cl", this.jack_club);
		view.CreateIntWME("ten_cl", this.ten_club);
		view.CreateIntWME("rkcb_sp", this.rkcb_spade);
		view.CreateIntWME("rkcb_he", this.rkcb_heart);
		view.CreateIntWME("rkcb_di", this.rkcb_dia);
		view.CreateIntWME("rkcb_cl", this.rkcb_club);
		
		view.CreateIntWME("num_sp_low", this.num_spade_low);
		view.CreateIntWME("num_sp_high", this.num_spade_high);
		view.CreateIntWME("num_he_low", this.num_heart_low);
		view.CreateIntWME("num_he_high", this.num_heart_high);
		view.CreateIntWME("num_di_low", this.num_dia_low);
		view.CreateIntWME("num_di_high", this.num_dia_high);
		view.CreateIntWME("num_cl_low", this.num_club_low);
		view.CreateIntWME("num_cl_high", this.num_club_high);

		view.CreateIntWME("balanced_low", this.balanced_low);
		view.CreateIntWME("balanced_high", this.balanced_high);
		
		view.CreateIntWME("dp_sp_he", this.dp_sp_he); 
		view.CreateIntWME("dp_sp_di", this.dp_sp_di);
		view.CreateIntWME("dp_sp_cl", this.dp_sp_cl);
		view.CreateIntWME("dp_sp_ha", this.dp_sp_ha);
		view.CreateIntWME("dp_he_sp", this.dp_he_sp);
		view.CreateIntWME("dp_he_di", this.dp_he_di);
		view.CreateIntWME("dp_he_cl", this.dp_he_cl);
		view.CreateIntWME("dp_he_ha", this.dp_he_ha);
		view.CreateIntWME("dp_di_sp", this.dp_di_sp);
		view.CreateIntWME("dp_di_he", this.dp_di_he);
		view.CreateIntWME("dp_di_cl", this.dp_di_cl);
		view.CreateIntWME("dp_di_ha", this.dp_di_ha);
		view.CreateIntWME("dp_cl_sp", this.dp_cl_sp);
		view.CreateIntWME("dp_cl_he", this.dp_cl_he);
		view.CreateIntWME("dp_cl_di", this.dp_cl_di);
		view.CreateIntWME("dp_cl_ha", this.dp_cl_ha);
		
		view.CreateIntWME("num_suits", this.num_suits);
		view.CreateIntWME("lmaj", this.lmaj);
		view.CreateIntWME("lmin", this.lmin);
		
		view.CreateIntWME("longest_sp", this.longest_sp);
		view.CreateIntWME("longest_he", this.longest_he);
		view.CreateIntWME("longest_di", this.longest_di);
		view.CreateIntWME("longest_cl", this.longest_cl);
		view.CreateIntWME("shortest_sp", this.shortest_sp);
		view.CreateIntWME("shortest_he", this.shortest_he);
		view.CreateIntWME("shortest_di", this.shortest_di);
		view.CreateIntWME("shortest_cl", this.shortest_cl);
		
		view.CreateIntWME("biddable_sp", this.biddable_sp);
		view.CreateIntWME("biddable_he", this.biddable_he);
		view.CreateIntWME("biddable_di", this.biddable_di);
		view.CreateIntWME("biddable_cl", this.biddable_cl);

		view.CreateFloatWME("intermediate_sp_low", this.intermediate_sp_low);
		view.CreateFloatWME("intermediate_sp_high", this.intermediate_sp_high);
		view.CreateFloatWME("intermediate_he_low", this.intermediate_he_low);
		view.CreateFloatWME("intermediate_he_high", this.intermediate_he_high);
		view.CreateFloatWME("intermediate_di_low", this.intermediate_di_low);
		view.CreateFloatWME("intermediate_di_high", this.intermediate_di_high);
		view.CreateFloatWME("intermediate_cl_low", this.intermediate_cl_low);
		view.CreateFloatWME("intermediate_cl_high", this.intermediate_cl_high);
		
		view.CreateIntWME("losers_sp_he", this.losers_sp_he); 
		view.CreateIntWME("losers_sp_di", this.losers_sp_di);
		view.CreateIntWME("losers_sp_cl", this.losers_sp_cl);
		view.CreateIntWME("losers_sp_ha", this.losers_sp_ha);
		view.CreateIntWME("losers_he_sp", this.losers_he_sp);
		view.CreateIntWME("losers_he_di", this.losers_he_di);
		view.CreateIntWME("losers_he_cl", this.losers_he_cl);
		view.CreateIntWME("losers_he_ha", this.losers_he_ha);
		view.CreateIntWME("losers_di_sp", this.losers_di_sp);
		view.CreateIntWME("losers_di_he", this.losers_di_he);
		view.CreateIntWME("losers_di_cl", this.losers_di_cl);
		view.CreateIntWME("losers_di_ha", this.losers_di_ha);
		view.CreateIntWME("losers_cl_sp", this.losers_cl_sp);
		view.CreateIntWME("losers_cl_he", this.losers_cl_he);
		view.CreateIntWME("losers_cl_di", this.losers_cl_di);
		view.CreateIntWME("losers_cl_ha", this.losers_cl_ha);
		
		view.CreateFloatWME("stopper_sp", this.stopper_sp);
		view.CreateFloatWME("stopper_he", this.stopper_he);
		view.CreateFloatWME("stopper_di", this.stopper_di);
		view.CreateFloatWME("stopper_cl", this.stopper_cl);

		view.CreateIntWME("tr_stopper_sp_ha", this.tr_stopper_sp_sp);
		view.CreateIntWME("tr_stopper_sp_he", this.tr_stopper_sp_he); 
		view.CreateIntWME("tr_stopper_sp_di", this.tr_stopper_sp_di);
		view.CreateIntWME("tr_stopper_sp_cl", this.tr_stopper_sp_cl);
		view.CreateIntWME("tr_stopper_he_sp", this.tr_stopper_he_sp);
		view.CreateIntWME("tr_stopper_he_ha", this.tr_stopper_he_he);
		view.CreateIntWME("tr_stopper_he_di", this.tr_stopper_he_di);
		view.CreateIntWME("tr_stopper_he_cl", this.tr_stopper_he_cl);
		view.CreateIntWME("tr_stopper_di_sp", this.tr_stopper_di_sp);
		view.CreateIntWME("tr_stopper_di_he", this.tr_stopper_di_he);
		view.CreateIntWME("tr_stopper_di_ha", this.tr_stopper_di_di);
		view.CreateIntWME("tr_stopper_di_cl", this.tr_stopper_di_cl);
		view.CreateIntWME("tr_stopper_cl_sp", this.tr_stopper_cl_sp);
		view.CreateIntWME("tr_stopper_cl_he", this.tr_stopper_cl_he);
		view.CreateIntWME("tr_stopper_cl_di", this.tr_stopper_cl_di);
		view.CreateIntWME("tr_stopper_cl_ha", this.tr_stopper_cl_cl);
		
		view.CreateFloatWME("quality_sp", this.quality_sp);
		view.CreateFloatWME("quality_he", this.quality_he);
		view.CreateFloatWME("quality_di", this.quality_di);
		view.CreateFloatWME("quality_cl", this.quality_cl);
		
		view.CreateFloatWME("ratio_low", this.ratio_low);
		view.CreateFloatWME("ratio_high", this.ratio_high);
	}
	
	public boolean updateView(Identifier iden) {
		System.out.println("Updates sent by SOAR:");
		int numUpdates = iden.GetNumberChildren();
		
		for(int i=0; i<numUpdates; ++i) {
			WMElement feature = iden.GetChild(i);
			System.out.println(feature.GetAttribute() + ": " + feature.GetValueAsString());

			if(feature.GetAttribute().equals("hcp_low")) {
				if(this.points_hc_low < ((IntElement)feature).GetValue())
					this.points_hc_low = (int) ((IntElement)feature).GetValue();
			}
			
			if(feature.GetAttribute().equals("hcp_high")) {
				if(this.points_hc_high < ((IntElement)feature).GetValue())
					this.points_hc_high = (int) ((IntElement)feature).GetValue();
			}
		}
		
		return true;
	}
	
	private void copyValues(HandView to, HandView from) {
		try {
			to = (HandView) from.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
