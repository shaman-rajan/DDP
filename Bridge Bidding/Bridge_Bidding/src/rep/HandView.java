package rep;

import sml.Identifier;
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
	
	private static final int MAXVALUE = 50;
	
	public HandView() {
		
		points_hc_low = -1;
		points_hc_high = MAXVALUE;
		points_spade_low = -1;
		points_spade_high = MAXVALUE; 
		points_heart_low = -1;
		points_heart_high = MAXVALUE; 
		points_dia_low = -1;
		points_dia_high = MAXVALUE; 		
		points_club_low = -1;
		points_club_high = MAXVALUE; 
				
		controls_low = -1;
		controls_high = MAXVALUE;
		controls_spade_low = -1;
		controls_spade_high = MAXVALUE;
		controls_heart_low = -1;
		controls_heart_high = MAXVALUE;
		controls_dia_low = -1;
		controls_dia_high = MAXVALUE;
		controls_club_low = -1;
		controls_club_high = MAXVALUE;
		
		highCards_low = -1;
		highCards_high = MAXVALUE;
		highCards_spade_low = -1;
		highCards_spade_high = MAXVALUE;
		highCards_heart_low = -1;
		highCards_heart_high = MAXVALUE;
		highCards_dia_low = -1;
		highCards_dia_high = MAXVALUE;
		highCards_club_low = -1;
		highCards_club_high = MAXVALUE;		
		
		honors_low = -1;
		honors_high = MAXVALUE;
		honors_spade_low = -1;
		honors_spade_high = MAXVALUE;		
		honors_heart_low = -1;
		honors_heart_high = MAXVALUE;
		honors_dia_low = -1;
		honors_dia_high = MAXVALUE;
		honors_club_low = -1;
		honors_club_high = MAXVALUE;

		aces = ace_spade = ace_heart = ace_dia = ace_club = -1;
		kings = king_spade = king_heart = king_dia = king_club = -1;
		queens = queen_spade = queen_heart = queen_dia = queen_club = -1;
		jacks = jack_spade = jack_heart = jack_dia = jack_club = -1;
		tens = ten_spade = ten_heart = ten_dia = ten_club = -1;
		rkcb_spade = rkcb_heart = rkcb_dia = rkcb_club = -1;
		
		num_spade_low = -1;
		num_spade_high = MAXVALUE;		
		num_heart_low = -1;
		num_heart_high = MAXVALUE;
		num_dia_low = -1;
		num_dia_high = MAXVALUE;
		num_club_low = -1;
		num_club_high = MAXVALUE;

		balanced_low = -1;
		balanced_high = MAXVALUE;
		
		dp_sp_he = dp_sp_di = dp_sp_cl = dp_sp_ha =
		dp_he_sp = dp_he_di = dp_he_cl = dp_he_ha =
		dp_di_sp = dp_di_he = dp_di_cl = dp_di_ha =
		dp_cl_sp = dp_cl_he = dp_cl_di = dp_cl_ha = -1;
		
		num_suits = -1;
		
		lmaj = lmin = -1;
		
		longest_sp = longest_he = longest_di = longest_cl = -1;
		
		shortest_sp = shortest_he = shortest_di = shortest_cl = -1;
		
		biddable_sp = biddable_he = biddable_di = biddable_cl = -1;
		
		intermediate_sp_low = -1;
		intermediate_sp_high = MAXVALUE;		
		intermediate_he_low = -1;
		intermediate_he_high = MAXVALUE;
		intermediate_di_low = -1;
		intermediate_di_high = MAXVALUE;
		intermediate_cl_low = -1;
		intermediate_cl_high = MAXVALUE;

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
		
		ratio_low = -1;
		ratio_high = MAXVALUE;
	}
	
	/*
	 * Interface with SOAR
	 */
	public void addToSoarIdentifier(Identifier view) {
		
		view.CreateIntWME("hcp_low", this.points_hc_low);
		view.CreateIntWME("hcp_high", this.points_hc_high);
		
		view.CreateIntWME("controls_low", this.controls_low);
		view.CreateIntWME("controls_high", this.controls_high);
		
		view.CreateIntWME("hc_low", this.highCards_low);
		view.CreateIntWME("hc_high", this.highCards_high);
		
		view.CreateIntWME("honors_low", this.honors_low);
		view.CreateIntWME("honors_high", this.honors_high);
		
		view.CreateIntWME("aces", this.aces);
		view.CreateIntWME("kings", this.kings);
		view.CreateIntWME("queens", this.queens);
		view.CreateIntWME("jacks", this.jacks);
		view.CreateIntWME("tens", this.tens);
		
		view.CreateIntWME("balanced_low", this.balanced_low);
		view.CreateIntWME("balanced_high", this.balanced_high);

		view.CreateIntWME("num_suits", this.num_suits);
		view.CreateIntWME("lmaj", this.lmaj);
		view.CreateIntWME("lmin", this.lmin);
				
		view.CreateFloatWME("ratio_low", this.ratio_low);
		view.CreateFloatWME("ratio_high", this.ratio_high);

		Identifier spade = view.CreateIdWME("spade");
		spade.CreateIntWME("hcp_low", points_spade_low);
		spade.CreateIntWME("hcp_high", points_spade_high);
		spade.CreateIntWME("controls_low", controls_spade_low);
		spade.CreateIntWME("controls_high", controls_spade_high);
		spade.CreateIntWME("hc_low", highCards_spade_low);
		spade.CreateIntWME("hc_high", highCards_spade_high);
		spade.CreateIntWME("honors_low", honors_spade_low);
		spade.CreateIntWME("honors_high", honors_spade_high);
		spade.CreateIntWME("ace", ace_spade);
		spade.CreateIntWME("king", king_spade);
		spade.CreateIntWME("quen", queen_spade);
		spade.CreateIntWME("jack", jack_spade);
		spade.CreateIntWME("ten", ten_spade);
		spade.CreateIntWME("rkcb", rkcb_spade);
		spade.CreateIntWME("num_low", num_spade_low);
		spade.CreateIntWME("num_high", num_spade_high);
		spade.CreateIntWME("longest", this.longest_sp);
		spade.CreateIntWME("shortest", this.shortest_sp);
		spade.CreateIntWME("biddable", this.biddable_sp);
		spade.CreateFloatWME("intermediate_low", this.intermediate_sp_low);
		spade.CreateFloatWME("intermediate_high", this.intermediate_sp_high);
		spade.CreateFloatWME("stopper", this.stopper_sp);
		spade.CreateFloatWME("quality", this.quality_sp);

		Identifier heart = view.CreateIdWME("heart");
		heart.CreateIntWME("hcp_low", points_heart_low);
		heart.CreateIntWME("hcp_high", points_heart_high);
		heart.CreateIntWME("controls_low", controls_heart_low);
		heart.CreateIntWME("controls_high", controls_heart_high);
		heart.CreateIntWME("hc_low", highCards_heart_low);
		heart.CreateIntWME("hc_high", highCards_heart_high);
		heart.CreateIntWME("honors_low", honors_heart_low);
		heart.CreateIntWME("honors_high", honors_heart_high);
		heart.CreateIntWME("ace", ace_heart);
		heart.CreateIntWME("king", king_heart);
		heart.CreateIntWME("quen", queen_heart);
		heart.CreateIntWME("jack", jack_heart);
		heart.CreateIntWME("ten", ten_heart);
		heart.CreateIntWME("rkcb", rkcb_heart);
		heart.CreateIntWME("num_low", num_heart_low);
		heart.CreateIntWME("num_high", num_heart_high);
		heart.CreateIntWME("longest", this.longest_he);
		heart.CreateIntWME("shortest", this.shortest_he);
		heart.CreateIntWME("biddable", this.biddable_he);
		heart.CreateFloatWME("intermediate_low", this.intermediate_he_low);
		heart.CreateFloatWME("intermediate_high", this.intermediate_he_high);
		heart.CreateFloatWME("stopper", this.stopper_he);
		heart.CreateFloatWME("quality", this.quality_he);

		Identifier dia = view.CreateIdWME("dia");
		dia.CreateIntWME("hcp_low", points_dia_low);
		dia.CreateIntWME("hcp_high", points_dia_high);
		dia.CreateIntWME("controls_low", controls_dia_low);
		dia.CreateIntWME("controls_high", controls_dia_high);
		dia.CreateIntWME("hc_low", highCards_dia_low);
		dia.CreateIntWME("hc_high", highCards_dia_high);
		dia.CreateIntWME("honors_low", honors_dia_low);
		dia.CreateIntWME("honors_high", honors_dia_high);
		dia.CreateIntWME("ace", ace_dia);
		dia.CreateIntWME("king", king_dia);
		dia.CreateIntWME("quen", queen_dia);
		dia.CreateIntWME("jack", jack_dia);
		dia.CreateIntWME("ten", ten_dia);
		dia.CreateIntWME("rkcb", rkcb_dia);
		dia.CreateIntWME("num_low", num_dia_low);
		dia.CreateIntWME("num_high", num_dia_high);
		dia.CreateIntWME("longest", this.longest_di);
		dia.CreateIntWME("shortest", this.shortest_di);
		dia.CreateIntWME("biddable", this.biddable_di);
		dia.CreateFloatWME("intermediate_low", this.intermediate_di_low);
		dia.CreateFloatWME("intermediate_high", this.intermediate_di_high);
		dia.CreateFloatWME("stopper", this.stopper_di);
		dia.CreateFloatWME("quality", this.quality_di);

		Identifier club = view.CreateIdWME("club");
		club.CreateIntWME("hcp_low", points_club_low);
		club.CreateIntWME("hcp_high", points_club_high);
		club.CreateIntWME("controls_low", controls_club_low);
		club.CreateIntWME("controls_high", controls_club_high);
		club.CreateIntWME("hc_low", highCards_club_low);
		club.CreateIntWME("hc_high", highCards_club_high);
		club.CreateIntWME("honors_low", honors_club_low);
		club.CreateIntWME("honors_high", honors_club_high);
		club.CreateIntWME("ace", ace_club);
		club.CreateIntWME("king", king_club);
		club.CreateIntWME("quen", queen_club);
		club.CreateIntWME("jack", jack_club);
		club.CreateIntWME("ten", ten_club);
		club.CreateIntWME("rkcb", rkcb_club);
		club.CreateIntWME("num_low", num_club_low);
		club.CreateIntWME("num_high", num_club_high);
		club.CreateIntWME("longest", this.longest_cl);
		club.CreateIntWME("shortest", this.shortest_cl);
		club.CreateIntWME("biddable", this.biddable_cl);
		club.CreateFloatWME("intermediate_low", this.intermediate_cl_low);
		club.CreateFloatWME("intermediate_high", this.intermediate_cl_high);
		club.CreateFloatWME("stopper", this.stopper_cl);
		club.CreateFloatWME("quality", this.quality_cl);
		
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
	}
	
	// TODO: Complete this function
	public boolean updateView(Identifier iden) {
		System.out.println("Updates sent by SOAR:");
		int numUpdates = iden.GetNumberChildren();
		
		for(int i=0; i<numUpdates; ++i) {
			WMElement feature = iden.GetChild(i);
			System.out.println(feature.GetAttribute() + ": " + feature.GetValueAsString());

			if(feature.GetAttribute().equals("hcp_low")) {
				if(this.points_hc_low < feature.ConvertToIntElement().GetValue())
					this.points_hc_low = (int) feature.ConvertToIntElement().GetValue();
			}
			
			if(feature.GetAttribute().equals("hcp_high")) {
				if(this.points_hc_high < feature.ConvertToIntElement().GetValue())
					this.points_hc_high = (int) feature.ConvertToIntElement().GetValue();
			}
		}
		
		return true;
	}
	
	public boolean matchesView(Hand hand) {
		if( (this.points_hc_low <= hand.points_hc && this.points_hc_high >= hand.points_hc) &&
			(this.points_spade_low <= hand.points_spade && this.points_spade_high >= hand.points_spade) &&
			(this.points_heart_low <= hand.points_heart && this.points_heart_high >= hand.points_heart) &&
			(this.points_dia_low <= hand.points_dia && this.points_dia_high >= hand.points_dia) &&
			(this.points_club_low <= hand.points_club && this.points_club_high >= hand.points_club) &&
			(this.controls_low <= hand.controls && this.controls_high >= hand.controls) &&
			(this.controls_spade_low <= hand.controls_spade && this.controls_spade_high >= hand.controls_spade) &&
			(this.controls_heart_low <= hand.controls_heart && this.controls_heart_high >= hand.controls_heart) &&
			(this.controls_dia_low <= hand.controls_dia && this.controls_dia_high >= hand.controls_dia) &&
			(this.controls_club_low <= hand.controls_club && this.controls_club_high >= hand.controls_club) &&
			(this.highCards_low <= hand.highCards && this.highCards_high >= hand.highCards) &&
			(this.highCards_spade_low <= hand.highCards_spade && this.highCards_spade_high >= hand.highCards_spade) &&
			(this.highCards_heart_low <= hand.highCards_heart && this.highCards_heart_high >= hand.highCards_heart) &&
			(this.highCards_dia_low <= hand.highCards_dia && this.highCards_dia_high >= hand.highCards_dia) &&
			(this.highCards_club_low <= hand.highCards_club && this.highCards_club_high >= hand.highCards_club) &&
			(this.honors_low <= hand.honors && this.honors_high >= hand.honors) &&
			(this.honors_spade_low <= hand.honors_spade && this.honors_spade_high >= hand.honors_spade) &&
			(this.honors_heart_low <= hand.honors_heart && this.honors_heart_high >= hand.honors_heart) &&
			(this.honors_dia_low <= hand.honors_dia && this.honors_dia_high >= hand.honors_dia) &&
			(this.honors_club_low <= hand.honors_club && this.honors_club_high >= hand.honors_club) &&
			(this.aces == hand.aces) &&
			(this.ace_spade == -1 || this.ace_spade == hand.ace_spade) &&
			(this.ace_heart == -1 || this.ace_heart == hand.ace_heart) &&
			(this.ace_dia == -1 || this.ace_dia == hand.ace_dia) &&
			(this.ace_club == -1 || this.ace_club == hand.ace_club) &&
			(this.kings == hand.kings) &&
			(this.king_spade == -1 || this.king_spade == hand.king_spade) &&
			(this.king_heart == -1 || this.king_heart == hand.king_heart) &&
			(this.king_dia == -1 || this.king_dia == hand.king_dia) &&
			(this.king_club == -1 || this.king_club == hand.king_club) &&
			(this.queens == hand.queens) &&
			(this.queen_spade == -1 || this.queen_spade == hand.queen_spade) &&
			(this.queen_heart == -1 || this.queen_heart == hand.queen_heart) &&
			(this.queen_dia == -1 || this.queen_dia == hand.queen_dia) &&
			(this.queen_club == -1 || this.queen_club == hand.queen_club) &&
			(this.jacks == hand.jacks) &&
			(this.jack_spade == -1 || this.jack_spade == hand.jack_spade) &&
			(this.jack_heart == -1 || this.jack_heart == hand.jack_heart) &&
			(this.jack_dia == -1 || this.jack_dia == hand.jack_dia) &&
			(this.jack_club == -1 || this.jack_club == hand.jack_club) &&
			(this.tens == hand.tens) &&
			(this.ten_spade == -1 || this.ten_spade == hand.ten_spade) &&
			(this.ten_heart == -1 || this.ten_heart == hand.ten_heart) &&
			(this.ten_dia == -1 || this.ten_dia == hand.ten_dia) &&
			(this.ten_club == -1 || this.ten_club == hand.ten_club) &&
			(this.rkcb_spade == -1 || this.rkcb_spade == hand.rkcb_spade) &&
			(this.rkcb_heart == -1 || this.rkcb_heart == hand.rkcb_heart) &&
			(this.rkcb_dia == -1 || this.rkcb_dia == hand.rkcb_dia) &&
			(this.rkcb_club == -1 || this.rkcb_club == hand.rkcb_club) &&
			(this.num_spade_low <= hand.num_spade && this.num_spade_high >= hand.num_spade) &&
			(this.num_heart_low <= hand.num_heart && this.num_heart_high >= hand.num_heart) &&
			(this.num_dia_low <= hand.num_dia && this.num_dia_high >= hand.num_dia) &&
			(this.num_club_low <= hand.num_club && this.num_club_high >= hand.num_club) &&
			(this.balanced_low <= hand.balanced && this.balanced_high >= hand.balanced) &&
			(this.dp_sp_he == -1 || this.dp_sp_he == hand.dp_sp_he) &&
			(this.dp_sp_di == -1 || this.dp_sp_di == hand.dp_sp_di) &&
			(this.dp_sp_cl == -1 || this.dp_sp_cl == hand.dp_sp_cl) &&
			(this.dp_sp_ha == -1 || this.dp_sp_ha == hand.dp_sp_ha) &&
			(this.dp_he_sp == -1 || this.dp_he_sp == hand.dp_he_sp) &&
			(this.dp_he_di == -1 || this.dp_he_di == hand.dp_he_di) &&
			(this.dp_he_cl == -1 || this.dp_he_cl == hand.dp_he_cl) &&
			(this.dp_he_ha == -1 || this.dp_he_ha == hand.dp_he_ha) &&
			(this.dp_di_sp == -1 || this.dp_di_sp == hand.dp_di_sp) &&
			(this.dp_di_he == -1 || this.dp_di_he == hand.dp_di_he) &&
			(this.dp_di_cl == -1 || this.dp_di_cl == hand.dp_di_cl) &&
			(this.dp_di_ha == -1 || this.dp_di_ha == hand.dp_di_ha) &&
			(this.dp_cl_sp == -1 || this.dp_cl_sp == hand.dp_cl_sp) &&
			(this.dp_cl_he == -1 || this.dp_cl_he == hand.dp_cl_he) &&
			(this.dp_cl_di == -1 || this.dp_cl_di == hand.dp_cl_di) &&
			(this.dp_cl_ha == -1 || this.dp_cl_ha == hand.dp_cl_ha) &&
			(this.num_suits == -1 || this.num_suits == hand.num_suits) &&
			(this.lmaj == -1 || this.lmaj == hand.lmaj) &&
			(this.lmin == -1 || this.lmin == hand.lmin) &&
			(this.longest_sp == -1 || this.longest_sp == hand.longest_sp) &&
			(this.longest_he == -1 || this.longest_he == hand.longest_he) &&
			(this.longest_di == -1 || this.longest_di == hand.longest_di) &&
			(this.longest_cl == -1 || this.longest_cl == hand.longest_cl) &&
			(this.shortest_sp == -1 || this.shortest_sp == hand.shortest_sp) &&
			(this.shortest_he == -1 || this.shortest_he == hand.shortest_he) &&
			(this.shortest_di == -1 || this.shortest_di == hand.shortest_di) &&
			(this.shortest_cl == -1 || this.shortest_cl == hand.shortest_cl) &&
			(this.biddable_sp == -1 || this.biddable_sp == hand.biddable_sp) &&
			(this.biddable_he == -1 || this.biddable_he == hand.biddable_he) &&
			(this.biddable_di == -1 || this.biddable_di == hand.biddable_di) &&
			(this.biddable_cl == -1 || this.biddable_cl == hand.biddable_cl) &&
			(this.intermediate_ha_low <= hand.intermediate_ha && this.intermediate_ha_high >= hand.intermediate_ha) &&
			(this.intermediate_sp_low <= hand.intermediate_sp && this.intermediate_sp_high >= hand.intermediate_sp) &&
			(this.intermediate_he_low <= hand.intermediate_he && this.intermediate_he_high >= hand.intermediate_he) &&
			(this.intermediate_di_low <= hand.intermediate_di && this.intermediate_di_high >= hand.intermediate_di) &&
			(this.intermediate_cl_low <= hand.intermediate_cl && this.intermediate_cl_high >= hand.intermediate_cl) &&
			(this.stopper_sp == -1 || this.stopper_sp == hand.stopper_sp) &&
			(this.stopper_he == -1 || this.stopper_he == hand.stopper_he) &&
			(this.stopper_di == -1 || this.stopper_di == hand.stopper_di) &&
			(this.stopper_cl == -1 || this.stopper_cl == hand.stopper_cl) &&
			(this.tr_stopper_sp_he == -1 || this.tr_stopper_sp_he == hand.tr_stopper_sp_he) &&
			(this.tr_stopper_sp_di == -1 || this.tr_stopper_sp_di == hand.tr_stopper_sp_di) &&
			(this.tr_stopper_sp_cl == -1 || this.tr_stopper_sp_cl == hand.tr_stopper_sp_cl) &&
			(this.tr_stopper_he_sp == -1 || this.tr_stopper_he_sp == hand.tr_stopper_he_sp) &&
			(this.tr_stopper_he_di == -1 || this.tr_stopper_he_di == hand.tr_stopper_he_di) &&
			(this.tr_stopper_he_cl == -1 || this.tr_stopper_he_cl == hand.tr_stopper_he_cl) &&
			(this.tr_stopper_di_sp == -1 || this.tr_stopper_di_sp == hand.tr_stopper_di_sp) &&
			(this.tr_stopper_di_he == -1 || this.tr_stopper_di_he == hand.tr_stopper_di_he) &&
			(this.tr_stopper_di_cl == -1 || this.tr_stopper_di_cl == hand.tr_stopper_di_cl) &&
			(this.tr_stopper_cl_sp == -1 || this.tr_stopper_cl_sp == hand.tr_stopper_cl_sp) &&
			(this.tr_stopper_cl_he == -1 || this.tr_stopper_cl_he == hand.tr_stopper_cl_he) &&
			(this.tr_stopper_cl_di == -1 || this.tr_stopper_cl_di == hand.tr_stopper_cl_di) &&
			(this.losers_sp_he == -1 || this.losers_sp_he == hand.losers_sp_he) &&
			(this.losers_sp_di == -1 || this.losers_sp_di == hand.losers_sp_di) &&
			(this.losers_sp_cl == -1 || this.losers_sp_cl == hand.losers_sp_cl) &&
			(this.losers_sp_ha == -1 || this.losers_sp_ha == hand.losers_sp_ha) &&
			(this.losers_he_sp == -1 || this.losers_he_sp == hand.losers_he_sp) &&
			(this.losers_he_di == -1 || this.losers_he_di == hand.losers_he_di) &&
			(this.losers_he_cl == -1 || this.losers_he_cl == hand.losers_he_cl) &&
			(this.losers_he_ha == -1 || this.losers_he_ha == hand.losers_he_ha) &&
			(this.losers_di_sp == -1 || this.losers_di_sp == hand.losers_di_sp) &&
			(this.losers_di_he == -1 || this.losers_di_he == hand.losers_di_he) &&
			(this.losers_di_cl == -1 || this.losers_di_cl == hand.losers_di_cl) &&
			(this.losers_di_ha == -1 || this.losers_di_ha == hand.losers_di_ha) &&
			(this.losers_cl_sp == -1 || this.losers_cl_sp == hand.losers_cl_sp) &&
			(this.losers_cl_he == -1 || this.losers_cl_he == hand.losers_cl_he) &&
			(this.losers_cl_di == -1 || this.losers_cl_di == hand.losers_cl_di) &&
			(this.losers_cl_ha == -1 || this.losers_cl_ha == hand.losers_cl_ha) &&
			(this.quality_sp == -1 || this.quality_sp == hand.quality_sp) &&
			(this.quality_he == -1 || this.quality_he == hand.quality_he) &&
			(this.quality_di == -1 || this.quality_di == hand.quality_di) &&
			(this.quality_cl == -1 || this.quality_cl == hand.quality_cl) &&
			(this.ratio_low <= hand.ratio && this.ratio_high >= hand.ratio)
		)
			return true;
		else
			return false;
	}
	
	private void copyValues(HandView to, HandView from) {
		try {
			to = (HandView) from.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
