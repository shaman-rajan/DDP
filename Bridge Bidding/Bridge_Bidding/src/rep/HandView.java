package rep;

import sml.Identifier;
import sml.WMElement;

public class HandView implements Cloneable {
	
	private static final int MINVALUE = 0;
	private static final int MAXVALUE = 100;
	private static final int TOTALPOINTS = 40;
	private static final int TOTALPOINTS_SUIT = 10;
	private static final int NUMCARDSINHAND = 13;
	private static final int NUMCARDSINSUIT = 13;
	
	private TeamView teamToUpdate;
	
	// Player who owns this view
	private Player player;
	
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
	public int lmaj_low, lmaj_high, lmin_low, lmin_high;
	public int longest_sp, longest_he, longest_di, longest_cl;
	public int shortest_sp, shortest_he, shortest_di, shortest_cl;
	
	public int biddable_sp, biddable_he, biddable_di, biddable_cl;
	
	public double intermediate_sp_low, intermediate_sp_high,
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
	
	public double unlos_sp_low, unlos_sp_high,
					unlos_he_low, unlos_he_high, 
					unlos_di_low, unlos_di_high,
					unlos_cl_low, unlos_cl_high;
	
	public int total_points_sp_low, total_points_sp_high,
				total_points_he_low, total_points_he_high,
				total_points_di_low, total_points_di_high,
				total_points_cl_low, total_points_cl_high;
	
	public HandView(Hand fullHand, Player p) {
		this.player = p;
		
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
		this.lmaj_low = lmaj_high = fullHand.lmaj;
		this.lmin_low = lmin_high = fullHand.lmin;
		
		this.longest_sp = fullHand.longest_sp;
		this.longest_he = fullHand.longest_he;
		this.longest_di = fullHand.longest_di;
		this.longest_cl = fullHand.longest_cl;
		
		this.shortest_sp = fullHand.shortest_sp;
		this.shortest_he = fullHand.shortest_he;
		this.shortest_di = fullHand.shortest_di;
		this.shortest_cl = fullHand.shortest_cl;
		
		this.biddable_sp = fullHand.biddable_sp;
		this.biddable_he = fullHand.biddable_he;
		this.biddable_di = fullHand.biddable_di;
		this.biddable_cl = fullHand.biddable_cl;
		
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
		
		this.losers_sp_he = fullHand.losers_sp_he;
		this.losers_sp_di = fullHand.losers_sp_di;
		this.losers_sp_cl = fullHand.losers_sp_cl;
		this.losers_he_sp = fullHand.losers_he_sp;
		this.losers_he_di = fullHand.losers_he_di;
		this.losers_he_cl = fullHand.losers_he_cl;
		this.losers_di_sp = fullHand.losers_di_sp;
		this.losers_di_he = fullHand.losers_di_he;
		this.losers_di_cl = fullHand.losers_di_cl;
		this.losers_cl_sp = fullHand.losers_cl_sp;
		this.losers_cl_he = fullHand.losers_cl_he;
		this.losers_cl_di = fullHand.losers_cl_di;
			
		this.quality_sp = fullHand.quality_sp;
		this.quality_he = fullHand.quality_he;
		this.quality_di = fullHand.quality_di;
		this.quality_cl = fullHand.quality_cl;
		
		this.ratio_low = this.ratio_high = fullHand.ratio;

		this.unlos_sp_low = this.unlos_sp_high = fullHand.unlos_sp;
		this.unlos_he_low = this.unlos_he_high = fullHand.unlos_he;
		this.unlos_di_low = this.unlos_di_high = fullHand.unlos_di;
		this.unlos_cl_low = this.unlos_cl_high = fullHand.unlos_cl;
		
		this.total_points_sp_low = this.total_points_sp_high = fullHand.total_points_sp;
		this.total_points_he_low = this.total_points_he_high =  fullHand.total_points_he;
		this.total_points_di_low = this.total_points_di_high = fullHand.total_points_di;
		this.total_points_cl_low = this.total_points_cl_high = fullHand.total_points_cl;
	}
	
	public HandView(HandView hv, Player p) {
		copyValues(this, hv);
		this.player = p;
	}
	
	public HandView(Player p) {
		this.player = p;
		
		points_hc_low = MINVALUE;
		points_hc_high = MAXVALUE;
		points_spade_low = MINVALUE;
		points_spade_high = MAXVALUE; 
		points_heart_low = MINVALUE;
		points_heart_high = MAXVALUE; 
		points_dia_low = MINVALUE;
		points_dia_high = MAXVALUE; 		
		points_club_low = MINVALUE;
		points_club_high = MAXVALUE; 
				
		controls_low = MINVALUE;
		controls_high = MAXVALUE;
		controls_spade_low = MINVALUE;
		controls_spade_high = MAXVALUE;
		controls_heart_low = MINVALUE;
		controls_heart_high = MAXVALUE;
		controls_dia_low = MINVALUE;
		controls_dia_high = MAXVALUE;
		controls_club_low = MINVALUE;
		controls_club_high = MAXVALUE;
		
		highCards_low = MINVALUE;
		highCards_high = MAXVALUE;
		highCards_spade_low = MINVALUE;
		highCards_spade_high = MAXVALUE;
		highCards_heart_low = MINVALUE;
		highCards_heart_high = MAXVALUE;
		highCards_dia_low = MINVALUE;
		highCards_dia_high = MAXVALUE;
		highCards_club_low = MINVALUE;
		highCards_club_high = MAXVALUE;		
		
		honors_low = MINVALUE;
		honors_high = MAXVALUE;
		honors_spade_low = MINVALUE;
		honors_spade_high = MAXVALUE;		
		honors_heart_low = MINVALUE;
		honors_heart_high = MAXVALUE;
		honors_dia_low = MINVALUE;
		honors_dia_high = MAXVALUE;
		honors_club_low = MINVALUE;
		honors_club_high = MAXVALUE;

		aces = ace_spade = ace_heart = ace_dia = ace_club = -1;
		kings = king_spade = king_heart = king_dia = king_club = -1;
		queens = queen_spade = queen_heart = queen_dia = queen_club = -1;
		jacks = jack_spade = jack_heart = jack_dia = jack_club = -1;
		tens = ten_spade = ten_heart = ten_dia = ten_club = -1;
		rkcb_spade = rkcb_heart = rkcb_dia = rkcb_club = -1;
		
		num_spade_low = MINVALUE;
		num_spade_high = MAXVALUE;		
		num_heart_low = MINVALUE;
		num_heart_high = MAXVALUE;
		num_dia_low = MINVALUE;
		num_dia_high = MAXVALUE;
		num_club_low = MINVALUE;
		num_club_high = MAXVALUE;

		balanced_low = MINVALUE;
		balanced_high = MAXVALUE;
		
		dp_sp_he = dp_sp_di = dp_sp_cl = dp_sp_ha =
		dp_he_sp = dp_he_di = dp_he_cl = dp_he_ha =
		dp_di_sp = dp_di_he = dp_di_cl = dp_di_ha =
		dp_cl_sp = dp_cl_he = dp_cl_di = dp_cl_ha = -1;
		
		num_suits = -1;
		
		lmaj_low = lmin_low = MINVALUE;
		lmaj_high = lmin_high = MAXVALUE;
		
		longest_sp = longest_he = longest_di = longest_cl = -1;
		
		shortest_sp = shortest_he = shortest_di = shortest_cl = -1;
		
		biddable_sp = biddable_he = biddable_di = biddable_cl = -1;
		
		intermediate_ha_low = MINVALUE;
		intermediate_ha_high = MAXVALUE;
		intermediate_sp_low = MINVALUE;
		intermediate_sp_high = MAXVALUE;		
		intermediate_he_low = MINVALUE;
		intermediate_he_high = MAXVALUE;
		intermediate_di_low = MINVALUE;
		intermediate_di_high = MAXVALUE;
		intermediate_cl_low = MINVALUE;
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
		
		ratio_low = MINVALUE;
		ratio_high = MAXVALUE;
		
		unlos_sp_low = MINVALUE;
		unlos_sp_high = MAXVALUE;		
		unlos_he_low = MINVALUE;
		unlos_he_high = MAXVALUE;
		unlos_di_low = MINVALUE;
		unlos_di_high = MAXVALUE;
		unlos_cl_low = MINVALUE;
		unlos_cl_high = MAXVALUE;
		
		total_points_sp_low = MINVALUE;
		total_points_sp_high = MAXVALUE;		
		total_points_he_low = MINVALUE;
		total_points_he_high = MAXVALUE;
		total_points_di_low = MINVALUE;
		total_points_di_high = MAXVALUE;
		total_points_cl_low = MINVALUE;
		total_points_cl_high = MAXVALUE;
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
		
		view.CreateFloatWME("intermediate_low", intermediate_ha_low);
		view.CreateFloatWME("intermediate_ha_high", intermediate_ha_high);

		view.CreateIntWME("num_suits", this.num_suits);
		
		view.CreateIntWME("lmaj_low", this.lmaj_low);
		view.CreateIntWME("lmaj_high", this.lmaj_high);
		view.CreateIntWME("lmin_low", this.lmin_low);
		view.CreateIntWME("lmin_high", this.lmin_high);
				
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
		spade.CreateIntWME("queen", queen_spade);
		spade.CreateIntWME("jack", jack_spade);
		spade.CreateIntWME("ten", ten_spade);
		spade.CreateIntWME("rkcb", rkcb_spade);
		spade.CreateIntWME("num_low", num_spade_low);
		spade.CreateIntWME("num_high", num_spade_high);
		spade.CreateIntWME("dp", this.dp_sp_ha);
		spade.CreateIntWME("longest", this.longest_sp);
		spade.CreateIntWME("shortest", this.shortest_sp);
		spade.CreateIntWME("biddable", this.biddable_sp);
		spade.CreateFloatWME("intermediate_low", this.intermediate_sp_low);
		spade.CreateFloatWME("intermediate_high", this.intermediate_sp_high);
		spade.CreateFloatWME("stopper", this.stopper_sp);
		spade.CreateIntWME("quality", this.quality_sp);
		spade.CreateFloatWME("unlos_low", this.unlos_sp_low);
		spade.CreateFloatWME("unlos_high", this.unlos_sp_high);
		spade.CreateIntWME("total_points_low", this.total_points_sp_low);
		spade.CreateIntWME("total_points_high", this.total_points_sp_high);

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
		heart.CreateIntWME("queen", queen_heart);
		heart.CreateIntWME("jack", jack_heart);
		heart.CreateIntWME("ten", ten_heart);
		heart.CreateIntWME("rkcb", rkcb_heart);
		heart.CreateIntWME("num_low", num_heart_low);
		heart.CreateIntWME("num_high", num_heart_high);
		heart.CreateIntWME("dp", this.dp_he_ha);
		heart.CreateIntWME("longest", this.longest_he);
		heart.CreateIntWME("shortest", this.shortest_he);
		heart.CreateIntWME("biddable", this.biddable_he);
		heart.CreateFloatWME("intermediate_low", this.intermediate_he_low);
		heart.CreateFloatWME("intermediate_high", this.intermediate_he_high);
		heart.CreateFloatWME("stopper", this.stopper_he);
		heart.CreateIntWME("quality", this.quality_he);
		heart.CreateFloatWME("unlos_low", this.unlos_he_low);
		heart.CreateFloatWME("unlos_high", this.unlos_he_high);
		heart.CreateIntWME("total_points_low", this.total_points_he_low);
		heart.CreateIntWME("total_points_high", this.total_points_he_high);
		
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
		dia.CreateIntWME("queen", queen_dia);
		dia.CreateIntWME("jack", jack_dia);
		dia.CreateIntWME("ten", ten_dia);
		dia.CreateIntWME("rkcb", rkcb_dia);
		dia.CreateIntWME("num_low", num_dia_low);
		dia.CreateIntWME("num_high", num_dia_high);
		dia.CreateIntWME("dp", this.dp_di_ha);
		dia.CreateIntWME("longest", this.longest_di);
		dia.CreateIntWME("shortest", this.shortest_di);
		dia.CreateIntWME("biddable", this.biddable_di);
		dia.CreateFloatWME("intermediate_low", this.intermediate_di_low);
		dia.CreateFloatWME("intermediate_high", this.intermediate_di_high);
		dia.CreateFloatWME("stopper", this.stopper_di);
		dia.CreateIntWME("quality", this.quality_di);
		dia.CreateFloatWME("unlos_low", this.unlos_di_low);
		dia.CreateFloatWME("unlos_high", this.unlos_di_high);
		dia.CreateIntWME("total_points_low", this.total_points_di_low);
		dia.CreateIntWME("total_points_high", this.total_points_di_high);
		
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
		club.CreateIntWME("queen", queen_club);
		club.CreateIntWME("jack", jack_club);
		club.CreateIntWME("ten", ten_club);
		club.CreateIntWME("rkcb", rkcb_club);
		club.CreateIntWME("num_low", num_club_low);
		club.CreateIntWME("num_high", num_club_high);
		club.CreateIntWME("dp", this.dp_cl_ha);
		club.CreateIntWME("longest", this.longest_cl);
		club.CreateIntWME("shortest", this.shortest_cl);
		club.CreateIntWME("biddable", this.biddable_cl);
		club.CreateFloatWME("intermediate_low", this.intermediate_cl_low);
		club.CreateFloatWME("intermediate_high", this.intermediate_cl_high);
		club.CreateFloatWME("stopper", this.stopper_cl);
		club.CreateIntWME("quality", this.quality_cl);
		club.CreateFloatWME("unlos_low", this.unlos_cl_low);
		club.CreateFloatWME("unlos_high", this.unlos_cl_high);
		club.CreateIntWME("total_points_low", this.total_points_cl_low);
		club.CreateIntWME("total_points_high", this.total_points_cl_high);
		
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
	
	public boolean updateView(Identifier iden, TeamView team) {
		this.teamToUpdate = team;
		
		System.out.println("Updates sent by SOAR:");
		int numUpdates = iden.GetNumberChildren();
		
		for(int i=0; i<numUpdates; ++i) {
			WMElement feature = iden.GetChild(i);
			if(!feature.GetValueType().equals("id")) System.out.println(feature.GetAttribute() + ": " + feature.GetValueAsString());

			boolean returned = false;
			if(feature.GetValueType().equalsIgnoreCase("double"))
				returned = this.updateFeature(feature.GetAttribute(), feature.ConvertToFloatElement().GetValue());
			
			else if(feature.GetValueType().equals("int"))
				returned = this.updateFeature(feature.GetAttribute(), feature.ConvertToIntElement().GetValue());
			
			else if(feature.GetValueType().equals("id")) {
				Identifier suit = feature.ConvertToIdentifier();
				int numSubUpdates = suit.GetNumberChildren();			
				
				if(suit.GetAttribute().equals("team")) {
					// It's a team update
					for(int j=0; j<numSubUpdates; ++j) {
						WMElement team_feature = suit.GetChild(j);
						System.out.println(suit.GetAttribute() + "." + team_feature.GetAttribute() + ": " + team_feature.GetValueAsString());
						
						boolean update_result_returned = false;
						if(team_feature.GetValueType().equals("int"))
							update_result_returned = teamToUpdate.updateFeature(team_feature.GetAttribute(), team_feature.ConvertToIntElement().GetValue());
						else if(team_feature.GetValueType().equals("string"))
							update_result_returned = teamToUpdate.updateFeature(team_feature.GetAttribute(), team_feature.ConvertToStringElement().GetValue());
					}
				} else {
					// It's a suit update
					for(int j=0; j<numSubUpdates; ++j) {
						WMElement suit_feature = suit.GetChild(j);
						System.out.println(suit.GetAttribute() + "." + suit_feature.GetAttribute() + ": " + suit_feature.GetValueAsString());
						
						boolean update_result_returned = false;
						if(suit_feature.GetValueType().equals("int"))
							update_result_returned = this.updateFeature(suit.GetAttribute() + " " + suit_feature.GetAttribute(), suit_feature.ConvertToIntElement().GetValue());
						else if(suit_feature.GetValueType().equals("double"))
							update_result_returned = this.updateFeature(suit.GetAttribute() + " " + suit_feature.GetAttribute(), suit_feature.ConvertToFloatElement().GetValue());
					}
				}
			}
		}
		
		// TeamView features automatically updated from the HandView features 
		this.player.getTeamView().updateHandFeatures();
		this.player.getOpponentsView().updateHandFeatures();
		
		return true;
	}
	
	public boolean matchesHand(Hand hand) {
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
			(this.aces == -1 || this.aces == hand.aces) &&
			(this.ace_spade == -1 || this.ace_spade == hand.ace_spade) &&
			(this.ace_heart == -1 || this.ace_heart == hand.ace_heart) &&
			(this.ace_dia == -1 || this.ace_dia == hand.ace_dia) &&
			(this.ace_club == -1 || this.ace_club == hand.ace_club) &&
			(this.kings == -1 || this.kings == hand.kings) &&
			(this.king_spade == -1 || this.king_spade == hand.king_spade) &&
			(this.king_heart == -1 || this.king_heart == hand.king_heart) &&
			(this.king_dia == -1 || this.king_dia == hand.king_dia) &&
			(this.king_club == -1 || this.king_club == hand.king_club) &&
			(this.queens == -1 || this.queens == hand.queens) &&
			(this.queen_spade == -1 || this.queen_spade == hand.queen_spade) &&
			(this.queen_heart == -1 || this.queen_heart == hand.queen_heart) &&
			(this.queen_dia == -1 || this.queen_dia == hand.queen_dia) &&
			(this.queen_club == -1 || this.queen_club == hand.queen_club) &&
			(this.jacks == -1 || this.jacks == hand.jacks) &&
			(this.jack_spade == -1 || this.jack_spade == hand.jack_spade) &&
			(this.jack_heart == -1 || this.jack_heart == hand.jack_heart) &&
			(this.jack_dia == -1 || this.jack_dia == hand.jack_dia) &&
			(this.jack_club == -1 || this.jack_club == hand.jack_club) &&
			(this.tens == -1 || this.tens == hand.tens) &&
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
			(this.lmaj_low <= hand.lmaj && this.lmaj_high >= hand.lmaj) &&
			(this.lmin_low <= hand.lmin && this.lmin_high >= hand.lmin) &&
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
			(this.ratio_low <= hand.ratio && this.ratio_high >= hand.ratio) &&
			(this.unlos_sp_low <= hand.unlos_sp && this.unlos_sp_high >= hand.unlos_sp) &&
			(this.unlos_he_low <= hand.unlos_he && this.unlos_he_high >= hand.unlos_he) &&
			(this.unlos_di_low <= hand.unlos_di && this.unlos_di_high >= hand.unlos_di) &&
			(this.unlos_cl_low <= hand.unlos_cl && this.unlos_cl_high >= hand.unlos_cl) &&
			(this.total_points_sp_low <= hand.total_points_sp && this.total_points_sp_high >= hand.total_points_sp) &&
			(this.total_points_he_low <= hand.total_points_he && this.total_points_he_high >= hand.total_points_he) &&
			(this.total_points_di_low <= hand.total_points_di && this.total_points_di_high >= hand.total_points_di) &&
			(this.total_points_cl_low <= hand.total_points_cl && this.total_points_cl_high >= hand.total_points_cl)
		)
			return true;
		else
			return false;
	}
	
	// TODO: Infer as much as possible
	private boolean updateFeature(String feature, long val) {
		int value = (int) val;
		
		if(feature.equals("hcp_low"))
			if(this.points_hc_low < value) {
				this.points_hc_low = value;
				
				int total = this.player.getSelfView().points_hc_low +
						this.player.getPartnerView().points_hc_low +
						this.player.getLeftOppView().points_hc_low +
						this.player.getRightOppView().points_hc_low;
				
				this.player.getPartnerView().updateFeature("hcp_high", TOTALPOINTS - (total - this.player.getPartnerView().points_hc_low));
				this.player.getLeftOppView().updateFeature("hcp_high", TOTALPOINTS - (total - this.player.getLeftOppView().points_hc_low));
				this.player.getRightOppView().updateFeature("hcp_high", TOTALPOINTS - (total - this.player.getRightOppView().points_hc_low));
				
				return true;
			} else return false;
		
		if(feature.equals("hcp_high"))
			if(this.points_hc_high > value) {
				this.points_hc_high = value;
				
				int total = this.player.getSelfView().points_hc_high +
						this.player.getPartnerView().points_hc_high +
						this.player.getLeftOppView().points_hc_high +
						this.player.getRightOppView().points_hc_high;
				
				this.player.getPartnerView().updateFeature("hcp_low", TOTALPOINTS - (total - this.player.getPartnerView().points_hc_high));
				this.player.getLeftOppView().updateFeature("hcp_low", TOTALPOINTS - (total - this.player.getLeftOppView().points_hc_high));
				this.player.getRightOppView().updateFeature("hcp_low", TOTALPOINTS - (total - this.player.getRightOppView().points_hc_high));
				
				this.updateFeature("spade hcp_high", this.points_hc_high);
				this.updateFeature("heart hcp_high", this.points_hc_high);
				this.updateFeature("dia hcp_high", this.points_hc_high);
				this.updateFeature("club hcp_high", this.points_hc_high);
				
				return true;
			} else return false;

		if(feature.equals("controls_low"))
			if(this.controls_low < value) {
				this.controls_low = value;
				return true;
			} else return false;
		
		if(feature.equals("controls_high"))
			if(this.controls_high > value) {
				this.controls_high = value;
				return true;
			} else return false;
		
		if(feature.equals("hc_low"))
			if(this.highCards_low < value) {
				this.highCards_low = value;
				return true;
			} else return false;
		
		if(feature.equals("hc_high"))
			if(this.highCards_high > value) {
				this.highCards_high = value;
				return true;
			} else return false;
		
		if(feature.equals("honors_low"))
			if(this.honors_low < value) {
				this.honors_low = value;
				return true;
			} else return false;
		
		if(feature.equals("honors_high"))
			if(this.honors_high > value) {
				this.honors_high = value;
				return true;
			} else return false;
		
		if(feature.equals("aces"))
			if(this.aces == -1) {
				this.aces = value;
				
				if(this.aces == 0) {
					this.updateFeature("spade ace", 0);
					this.updateFeature("heart ace", 0);
					this.updateFeature("dia ace", 0);
					this.updateFeature("club ace", 0);
				} else if(this.aces == 4) {
					this.updateFeature("spade ace", 1);
					this.updateFeature("heart ace", 1);
					this.updateFeature("dia ace", 1);
					this.updateFeature("club ace", 1);
					
					if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("aces", 0);
					if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("aces", 0);
					if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("aces", 0);
				}
				
				this.updateFeature("hc_low", this.aces);
				
				return true;
			} else if(this.aces == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("kings"))
			if(this.kings == -1) {
				this.kings = value;
				
				if(this.kings == 0) {
					this.updateFeature("spade king", 0);
					this.updateFeature("heart king", 0);
					this.updateFeature("dia king", 0);
					this.updateFeature("club king", 0);
				} else if(this.kings == 4) {
					this.updateFeature("spade king", 1);
					this.updateFeature("heart king", 1);
					this.updateFeature("dia king", 1);
					this.updateFeature("club king", 1);
					
					if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("kings", 0);
					if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("kings", 0);
					if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("kings", 0);
				}
				
				this.updateFeature("hc_low", this.kings);
				
				return true;
			} else if(this.kings == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("queens"))
			if(this.queens == -1) {
				this.queens = value;
				
				if(this.queens == 0) {
					this.updateFeature("spade queen", 0);
					this.updateFeature("heart queen", 0);
					this.updateFeature("dia queen", 0);
					this.updateFeature("club queen", 0);
				} else if(this.queens == 4) {
					this.updateFeature("spade queen", 1);
					this.updateFeature("heart queen", 1);
					this.updateFeature("dia queen", 1);
					this.updateFeature("club queen", 1);
					
					if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("queens", 0);
					if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("queens", 0);
					if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("queens", 0);
				}
				
				this.updateFeature("hc_low", this.queens);
				
				return true;
			} else if(this.queens == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("jacks"))
			if(this.jacks == -1) {
				this.jacks = value;
				
				if(this.jacks == 0) {
					this.updateFeature("spade jack", 0);
					this.updateFeature("heart jack", 0);
					this.updateFeature("dia jack", 0);
					this.updateFeature("club jack", 0);
				} else if(this.jacks == 4) {
					this.updateFeature("spade jack", 1);
					this.updateFeature("heart jack", 1);
					this.updateFeature("dia jack", 1);
					this.updateFeature("club jack", 1);
					
					if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("jacks", 0);
					if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("jacks", 0);
					if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("jacks", 0);
				}
				
				return true;
			} else if(this.jacks == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("tens"))
			if(this.tens == -1) {
				this.tens = value;
				
				if(this.tens == 0) {
					this.updateFeature("spade ten", 0);
					this.updateFeature("heart ten", 0);
					this.updateFeature("dia ten", 0);
					this.updateFeature("club ten", 0);
				} else if(this.tens == 4) {
					this.updateFeature("spade ten", 1);
					this.updateFeature("heart ten", 1);
					this.updateFeature("dia ten", 1);
					this.updateFeature("club ten", 1);
					
					if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("tens", 0);
					if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("tens", 0);
					if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("tens", 0);
				}
				
				return true;
			} else if(this.tens == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("balanced_low"))
			if(this.balanced_low < value) {
				this.balanced_low = value;
				return true;
			} else return false;

		if(feature.equals("balanced_high"))
			if(this.balanced_high > value) {
				this.balanced_high = value;
				return true;
			} else return false;

		if(feature.equals("num_suits"))
			if(this.num_suits == -1) {
				this.num_suits = value;
				return true;
			} else if(this.num_suits != value)
				this.contradiction();
			else return false;

		if(feature.equals("lmaj_low"))
			if(this.lmaj_low < value) {
				this.lmaj_low = value;
				
				if(this.num_spade_high < this.lmaj_low)
					this.updateFeature("heart num_low", this.lmaj_low);
				if(this.num_heart_high < this.lmaj_low)
					this.updateFeature("spade num_low", this.lmaj_low);
				
				return true;
			} else return false;
		
		if(feature.equals("lmaj_high"))
			if(this.lmaj_high > value) {
				this.lmaj_high = value;
				
				this.updateFeature("spade num_high", this.lmaj_high);
				this.updateFeature("heart num_high", this.lmaj_high);
				
				return true;
			} else return false;
		
		if(feature.equals("lmin_low"))
			if(this.lmin_low < value) {
				this.lmin_low = value;
				
				if(this.num_dia_high < this.lmin_low)
					this.updateFeature("club num_low", this.lmin_low);
				if(this.num_club_high < this.lmin_low)
					this.updateFeature("dia num_low", this.lmin_low);
				
				return true;
			} else return false;
		
		if(feature.equals("lmin_high"))
			if(this.lmin_high > value) {
				this.lmin_high = value;
				
				this.updateFeature("dia num_high", this.lmin_high);
				this.updateFeature("club num_high", this.lmaj_high);
			
				return true;
			} else return false;

		/*
		 * Spade updates
		 */
		
		if(feature.equals("spade hcp_low"))
			if(this.points_spade_low < value) {
				this.points_spade_low = value;
				
				int total = this.player.getSelfView().points_spade_low +
						this.player.getPartnerView().points_spade_low +
						this.player.getLeftOppView().points_spade_low +
						this.player.getRightOppView().points_spade_low;
				
				this.player.getPartnerView().updateFeature("spade hcp_high", TOTALPOINTS_SUIT - (total - this.player.getPartnerView().points_spade_low));
				this.player.getLeftOppView().updateFeature("spade hcp_high", TOTALPOINTS_SUIT - (total - this.player.getLeftOppView().points_spade_low));
				this.player.getRightOppView().updateFeature("spade hcp_high", TOTALPOINTS_SUIT - (total - this.player.getRightOppView().points_spade_low));
				
				return true;
			} else return false;
		
		if(feature.equals("spade hcp_high"))
			if(this.points_spade_high > value) {
				this.points_spade_high = value;
				
				int total = this.player.getSelfView().points_spade_high +
						this.player.getPartnerView().points_spade_high +
						this.player.getLeftOppView().points_spade_high +
						this.player.getRightOppView().points_spade_high;
				
				this.player.getPartnerView().updateFeature("spade hcp_low", TOTALPOINTS_SUIT - (total - this.player.getPartnerView().points_spade_high));
				this.player.getLeftOppView().updateFeature("spade hcp_low", TOTALPOINTS_SUIT - (total - this.player.getLeftOppView().points_spade_high));
				this.player.getRightOppView().updateFeature("spade hcp_low", TOTALPOINTS_SUIT - (total - this.player.getRightOppView().points_spade_high));
				
				return true;
			} else return false;

		if(feature.equals("spade controls_low"))
			if(this.controls_spade_low < value) {
				this.controls_spade_low = value;
				return true;
			} else return false;
		
		if(feature.equals("spade controls_high"))
			if(this.controls_spade_high > value) {
				this.controls_spade_high = value;
				return true;
			} else return false;

		if(feature.equals("spade hc_low"))
			if(this.highCards_spade_low < value) {
				this.highCards_spade_low = value;
				return true;
			} else return false;
		
		if(feature.equals("spade hc_high"))
			if(this.highCards_spade_high > value) {
				this.highCards_spade_high = value;
				return true;
			} else return false;

		
		if(feature.equals("spade honors_low"))
			if(this.honors_spade_low < value) {
				this.honors_spade_low = value;
				return true;
			} else return false;
		
		if(feature.equals("spade honors_high"))
			if(this.honors_spade_high > value) {
				this.honors_spade_high = value;
				return true;
			} else return false;

		if(feature.equals("spade ace"))
			if(this.ace_spade == -1) {
				this.ace_spade = value;
				
				return true;
			} else if(this.ace_spade == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("spade king"))
			if(this.king_spade == -1) {
				this.king_spade = value;
				return true;
			} else if(this.king_spade == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("spade queen"))
			if(this.queen_spade == -1) {
				this.queen_spade = value;
				return true;
			} else if(this.queen_spade == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("spade jack"))
			if(this.jack_spade == -1) {
				this.jack_spade = value;
				return true;
			} else if(this.jack_spade == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("spade ten"))
			if(this.ten_spade == -1) {
				this.ten_spade = value;
				return true;
			} else if(this.ten_spade == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		

		if(feature.equals("spade rkcb"))
			if(this.rkcb_spade == -1) {
				this.rkcb_spade = value;
				return true;
			} else if(this.rkcb_spade == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("spade num_low"))
			if(this.num_spade_low < value) {
				this.num_spade_low = value;
				
				this.updateFeature("heart num_high", NUMCARDSINHAND - this.num_spade_low);
				this.updateFeature("dia num_high", NUMCARDSINHAND - this.num_spade_low);
				this.updateFeature("club num_high", NUMCARDSINHAND - this.num_spade_low);
				
				if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("spade num_high", NUMCARDSINSUIT - this.num_spade_low);
				if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("spade num_high", NUMCARDSINSUIT - this.num_spade_low);
				if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("spade num_high", NUMCARDSINSUIT - this.num_spade_low);
				
				return true;
			} else return false;
		
		if(feature.equals("spade num_high"))
			if(this.num_spade_high > value) {
				this.num_spade_high = value;
				
				this.updateFeature("heart num_low", NUMCARDSINHAND - this.num_spade_high);
				this.updateFeature("dia num_low", NUMCARDSINHAND - this.num_spade_high);
				this.updateFeature("club num_low", NUMCARDSINHAND - this.num_spade_high);
				
				if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("spade num_low", NUMCARDSINSUIT - this.num_spade_high);
				if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("spade num_low", NUMCARDSINSUIT - this.num_spade_high);
				if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("spade num_low", NUMCARDSINSUIT - this.num_spade_high);
				
				return true;
			} else return false;

		if(feature.equals("spade longest")) {
			if(this.longest_sp == -1) {
				this.longest_sp = value;
				
				if(this.longest_sp == 1) {
					this.updateFeature("heart longest", 0);
					this.updateFeature("dia longest", 0);
					this.updateFeature("club longest", 0);
					
					this.updateFeature("spade num_low", 4);
				}
				
				return true;
			} else if(longest_sp == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("spade shortest")) {
			if(this.shortest_sp == -1) {
				this.shortest_sp = value;
				
				if(this.shortest_sp == 1) {
					this.updateFeature("heart shortest", 0);
					this.updateFeature("dia shortest", 0);
					this.updateFeature("club shortest", 0);
				}
				
				return true;
			} else if(shortest_sp == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("spade biddable")) {
			if(this.biddable_sp == -1) {
				this.biddable_sp = value;
				return true;
			} else if(biddable_sp == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}

		if(feature.equals("spade stopper")) {
			if(this.stopper_sp == -1) {
				this.stopper_sp = value;
				return true;
			} else if(stopper_sp == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("spade quality")) {
			if(this.quality_sp == -1) {
				this.quality_sp = value;
				return true;
			} else if(quality_sp == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("spade dp")) {
			if(this.dp_sp_ha == -1) {
				this.dp_sp_ha = value;
				return true;
			} else if(this.dp_sp_ha == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("spade total_points_low")) {
			if(this.dp_sp_ha < value) {
				this.dp_sp_ha = value;
				
				// TODO: Infer
				
				return true;
			} else
				return false;
		}
		
		/*
		 * Heart updates
		 */
		
		if(feature.equals("heart hcp_low"))
			if(this.points_heart_low < value) {
				this.points_heart_low = value;
				
				int total = this.player.getSelfView().points_heart_low +
						this.player.getPartnerView().points_heart_low +
						this.player.getLeftOppView().points_heart_low +
						this.player.getRightOppView().points_heart_low;
				
				this.player.getPartnerView().updateFeature("heart hcp_high", TOTALPOINTS_SUIT - (total - this.player.getPartnerView().points_heart_low));
				this.player.getLeftOppView().updateFeature("heart hcp_high", TOTALPOINTS_SUIT - (total - this.player.getLeftOppView().points_heart_low));
				this.player.getRightOppView().updateFeature("heart hcp_high", TOTALPOINTS_SUIT - (total - this.player.getRightOppView().points_heart_low));
				
				return true;
			} else return false;
		
		if(feature.equals("heart hcp_high"))
			if(this.points_heart_high > value) {
				this.points_heart_high = value;
				
				int total = this.player.getSelfView().points_heart_high +
						this.player.getPartnerView().points_heart_high +
						this.player.getLeftOppView().points_heart_high +
						this.player.getRightOppView().points_heart_high;
				
				this.player.getPartnerView().updateFeature("heart hcp_low", TOTALPOINTS_SUIT - (total - this.player.getPartnerView().points_heart_high));
				this.player.getLeftOppView().updateFeature("heart hcp_low", TOTALPOINTS_SUIT - (total - this.player.getLeftOppView().points_heart_high));
				this.player.getRightOppView().updateFeature("heart hcp_low", TOTALPOINTS_SUIT - (total - this.player.getRightOppView().points_heart_high));
				
				return true;
			} else return false;

		if(feature.equals("heart controls_low"))
			if(this.controls_heart_low < value) {
				this.controls_heart_low = value;
				return true;
			} else return false;
		
		if(feature.equals("heart controls_high"))
			if(this.controls_heart_high > value) {
				this.controls_heart_high = value;
				return true;
			} else return false;

		if(feature.equals("heart hc_low"))
			if(this.highCards_heart_low < value) {
				this.highCards_heart_low = value;
				return true;
			} else return false;
		
		if(feature.equals("heart hc_high"))
			if(this.highCards_heart_high > value) {
				this.highCards_heart_high = value;
				return true;
			} else return false;

		
		if(feature.equals("heart honors_low"))
			if(this.honors_heart_low < value) {
				this.honors_heart_low = value;
				return true;
			} else return false;
		
		if(feature.equals("heart honors_high"))
			if(this.honors_heart_high > value) {
				this.honors_heart_high = value;
				return true;
			} else return false;

		if(feature.equals("heart ace"))
			if(this.ace_heart == -1) {
				this.ace_heart = value;
				
				return true;
			} else if(this.ace_heart == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("heart king"))
			if(this.king_heart == -1) {
				this.king_heart = value;
				return true;
			} else if(this.king_heart == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("heart queen"))
			if(this.queen_heart == -1) {
				this.queen_heart = value;
				return true;
			} else if(this.queen_heart == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("heart jack"))
			if(this.jack_heart == -1) {
				this.jack_heart = value;
				return true;
			} else if(this.jack_heart == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("heart ten"))
			if(this.ten_heart == -1) {
				this.ten_heart = value;
				return true;
			} else if(this.ten_heart == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		

		if(feature.equals("heart rkcb"))
			if(this.rkcb_heart == -1) {
				this.rkcb_heart = value;
				return true;
			} else if(this.rkcb_heart == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("heart num_low"))
			if(this.num_heart_low < value) {
				
				this.updateFeature("spade num_high", NUMCARDSINHAND - this.num_heart_low);
				this.updateFeature("dia num_high", NUMCARDSINHAND - this.num_heart_low);
				this.updateFeature("club num_high", NUMCARDSINHAND - this.num_heart_low);
				
				if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("heart num_high", NUMCARDSINSUIT - this.num_heart_low);
				if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("heart num_high", NUMCARDSINSUIT - this.num_heart_low);
				if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("heart num_high", NUMCARDSINSUIT - this.num_heart_low);
				
				this.num_heart_low = value;
				return true;
			} else return false;
		
		if(feature.equals("heart num_high"))
			if(this.num_heart_high > value) {
				this.num_heart_high = value;
				
				this.updateFeature("spade num_low", NUMCARDSINHAND - this.num_heart_high);
				this.updateFeature("dia num_low", NUMCARDSINHAND - this.num_heart_high);
				this.updateFeature("club num_low", NUMCARDSINHAND - this.num_heart_high);
				
				if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("heart num_low", NUMCARDSINSUIT - this.num_heart_high);
				if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("heart num_low", NUMCARDSINSUIT - this.num_heart_high);
				if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("heart num_low", NUMCARDSINSUIT - this.num_heart_high);
				
				return true;
			} else return false;

		if(feature.equals("heart longest")) {
			if(this.longest_he == -1) {
				this.longest_he = value;
				
				if(this.longest_he == 1) {
					this.updateFeature("spade longest", 0);
					this.updateFeature("dia longest", 0);
					this.updateFeature("club longest", 0);
					
					this.updateFeature("heart num_low", 4);
				}
				
				return true;
			} else if(longest_he == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("heart shortest")) {
			if(this.shortest_he == -1) {
				this.shortest_he = value;
				
				if(this.shortest_he == 1) {
					this.updateFeature("spade shortest", 0);
					this.updateFeature("dia shortest", 0);
					this.updateFeature("club shortest", 0);
				}
				
				
				return true;
			} else if(shortest_he == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("heart biddable")) {
			if(this.biddable_he == -1) {
				this.biddable_he = value;
				return true;
			} else if(biddable_he == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}

		if(feature.equals("heart stopper")) {
			if(this.stopper_he == -1) {
				this.stopper_he = value;
				return true;
			} else if(stopper_he == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("heart quality")) {
			if(this.quality_he == -1) {
				this.quality_he = value;
				return true;
			} else if(quality_he == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("heart dp")) {
			if(this.dp_he_ha == -1) {
				this.dp_he_ha = value;
				return true;
			} else if(this.dp_he_ha == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("heart total_points_low")) {
			if(this.dp_he_ha < value) {
				this.dp_he_ha = value;
				
				// TODO: Infer
				
				return true;
			} else
				return false;
		}
		
		/*
		 * Diamond updates
		 */
		
		if(feature.equals("dia hcp_low"))
			if(this.points_dia_low < value) {
				this.points_dia_low = value;
				
				int total = this.player.getSelfView().points_dia_low +
						this.player.getPartnerView().points_dia_low +
						this.player.getLeftOppView().points_dia_low +
						this.player.getRightOppView().points_dia_low;
				
				this.player.getPartnerView().updateFeature("dia hcp_high", TOTALPOINTS_SUIT - (total - this.player.getPartnerView().points_dia_low));
				this.player.getLeftOppView().updateFeature("dia hcp_high", TOTALPOINTS_SUIT - (total - this.player.getLeftOppView().points_dia_low));
				this.player.getRightOppView().updateFeature("dia hcp_high", TOTALPOINTS_SUIT - (total - this.player.getRightOppView().points_dia_low));
				
				return true;
			} else return false;
		
		if(feature.equals("dia hcp_high"))
			if(this.points_dia_high > value) {
				this.points_dia_high = value;
				
				int total = this.player.getSelfView().points_dia_high +
						this.player.getPartnerView().points_dia_high +
						this.player.getLeftOppView().points_dia_high +
						this.player.getRightOppView().points_dia_high;
				
				this.player.getPartnerView().updateFeature("dia hcp_low", TOTALPOINTS_SUIT - (total - this.player.getPartnerView().points_dia_high));
				this.player.getLeftOppView().updateFeature("dia hcp_low", TOTALPOINTS_SUIT - (total - this.player.getLeftOppView().points_dia_high));
				this.player.getRightOppView().updateFeature("dia hcp_low", TOTALPOINTS_SUIT - (total - this.player.getRightOppView().points_dia_high));
				
				return true;
			} else return false;

		if(feature.equals("dia controls_low"))
			if(this.controls_dia_low < value) {
				this.controls_dia_low = value;
				return true;
			} else return false;
		
		if(feature.equals("dia controls_high"))
			if(this.controls_dia_high > value) {
				this.controls_dia_high = value;
				return true;
			} else return false;

		if(feature.equals("dia hc_low"))
			if(this.highCards_dia_low < value) {
				this.highCards_dia_low = value;
				return true;
			} else return false;
		
		if(feature.equals("dia hc_high"))
			if(this.highCards_dia_high > value) {
				this.highCards_dia_high = value;
				return true;
			} else return false;

		
		if(feature.equals("dia honors_low"))
			if(this.honors_dia_low < value) {
				this.honors_dia_low = value;
				return true;
			} else return false;
		
		if(feature.equals("dia honors_high"))
			if(this.honors_dia_high > value) {
				this.honors_dia_high = value;
				return true;
			} else return false;

		if(feature.equals("dia ace"))
			if(this.ace_dia == -1) {
				this.ace_dia = value;
				
				return true;
			} else if(this.ace_dia == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("dia king"))
			if(this.king_dia == -1) {
				this.king_dia = value;
				return true;
			} else if(this.king_dia == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("dia queen"))
			if(this.queen_dia == -1) {
				this.queen_dia = value;
				return true;
			} else if(this.queen_dia == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("dia jack"))
			if(this.jack_dia == -1) {
				this.jack_dia = value;
				return true;
			} else if(this.jack_dia == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("dia ten"))
			if(this.ten_dia == -1) {
				this.ten_dia = value;
				return true;
			} else if(this.ten_dia == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		

		if(feature.equals("dia rkcb"))
			if(this.rkcb_dia == -1) {
				this.rkcb_dia = value;
				return true;
			} else if(this.rkcb_dia == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("dia num_low"))
			if(this.num_dia_low < value) {
				
				this.updateFeature("spade num_high", NUMCARDSINHAND - this.num_dia_low);
				this.updateFeature("heart num_high", NUMCARDSINHAND - this.num_dia_low);
				this.updateFeature("club num_high", NUMCARDSINHAND - this.num_dia_low);
				
				if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("dia num_high", NUMCARDSINSUIT - this.num_dia_low);
				if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("dia num_high", NUMCARDSINSUIT - this.num_dia_low);
				if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("dia num_high", NUMCARDSINSUIT - this.num_dia_low);
				
				this.num_dia_low = value;
				return true;
			} else return false;
		
		if(feature.equals("dia num_high"))
			if(this.num_dia_high > value) {
				this.num_dia_high = value;
				
				this.updateFeature("spade num_low", NUMCARDSINHAND - this.num_dia_high);
				this.updateFeature("heart num_low", NUMCARDSINHAND - this.num_dia_high);
				this.updateFeature("club num_low", NUMCARDSINHAND - this.num_dia_high);
				
				if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("dia num_low", NUMCARDSINSUIT - this.num_dia_high);
				if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("dia num_low", NUMCARDSINSUIT - this.num_dia_high);
				if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("dia num_low", NUMCARDSINSUIT - this.num_dia_high);
				
				return true;
			} else return false;

		if(feature.equals("dia longest")) {
			if(this.longest_di == -1) {
				this.longest_di = value;
				
				if(this.longest_di == 1) {
					this.updateFeature("spade longest", 0);
					this.updateFeature("heart longest", 0);
					this.updateFeature("club longest", 0);
					
					this.updateFeature("dia num_low", 4);
				}
				
				return true;
			} else if(longest_di == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("dia shortest")) {
			if(this.shortest_di == -1) {
				this.shortest_di = value;
				
				if(this.shortest_di == 1) {
					this.updateFeature("spade shortest", 0);
					this.updateFeature("heart shortest", 0);
					this.updateFeature("club shortest", 0);
				}
				
				return true;
			} else if(shortest_di == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("dia biddable")) {
			if(this.biddable_di == -1) {
				this.biddable_di = value;
				return true;
			} else if(biddable_di == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}

		if(feature.equals("dia stopper")) {
			if(this.stopper_di == -1) {
				this.stopper_di = value;
				return true;
			} else if(stopper_di == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("dia quality")) {
			if(this.quality_di == -1) {
				this.quality_di = value;
				return true;
			} else if(quality_di == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("dia dp")) {
			if(this.dp_di_ha == -1) {
				this.dp_di_ha = value;
				return true;
			} else if(this.dp_di_ha == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("dia total_points_low")) {
			if(this.dp_di_ha < value) {
				this.dp_di_ha = value;
				
				// TODO: Infer
				
				return true;
			} else
				return false;
		}
		
		/*
		 * Club updates
		 */
		
		if(feature.equals("club hcp_low"))
			if(this.points_club_low < value) {
				this.points_club_low = value;
				
				int total = this.player.getSelfView().points_club_low +
						this.player.getPartnerView().points_club_low +
						this.player.getLeftOppView().points_club_low +
						this.player.getRightOppView().points_club_low;
				
				this.player.getPartnerView().updateFeature("club hcp_high", TOTALPOINTS_SUIT - (total - this.player.getPartnerView().points_club_low));
				this.player.getLeftOppView().updateFeature("club hcp_high", TOTALPOINTS_SUIT - (total - this.player.getLeftOppView().points_club_low));
				this.player.getRightOppView().updateFeature("club hcp_high", TOTALPOINTS_SUIT - (total - this.player.getRightOppView().points_club_low));
				
				return true;
			} else return false;
		
		if(feature.equals("club hcp_high"))
			if(this.points_club_high > value) {
				this.points_club_high = value;
				
				int total = this.player.getSelfView().points_club_high +
						this.player.getPartnerView().points_club_high +
						this.player.getLeftOppView().points_club_high +
						this.player.getRightOppView().points_club_high;
				
				this.player.getPartnerView().updateFeature("club hcp_low", TOTALPOINTS_SUIT - (total - this.player.getPartnerView().points_club_high));
				this.player.getLeftOppView().updateFeature("club hcp_low", TOTALPOINTS_SUIT - (total - this.player.getLeftOppView().points_club_high));
				this.player.getRightOppView().updateFeature("club hcp_low", TOTALPOINTS_SUIT - (total - this.player.getRightOppView().points_club_high));
				
				return true;
			} else return false;

		if(feature.equals("club controls_low"))
			if(this.controls_club_low < value) {
				this.controls_club_low = value;
				return true;
			} else return false;
		
		if(feature.equals("club controls_high"))
			if(this.controls_club_high > value) {
				this.controls_club_high = value;
				return true;
			} else return false;

		if(feature.equals("club hc_low"))
			if(this.highCards_club_low < value) {
				this.highCards_club_low = value;
				return true;
			} else return false;
		
		if(feature.equals("club hc_high"))
			if(this.highCards_club_high > value) {
				this.highCards_club_high = value;
				return true;
			} else return false;

		
		if(feature.equals("club honors_low"))
			if(this.honors_club_low < value) {
				this.honors_club_low = value;
				return true;
			} else return false;
		
		if(feature.equals("club honors_high"))
			if(this.honors_club_high > value) {
				this.honors_club_high = value;
				return true;
			} else return false;

		if(feature.equals("club ace"))
			if(this.ace_club == -1) {
				this.ace_club = value;
				
				return true;
			} else if(this.ace_club == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("club king"))
			if(this.king_club == -1) {
				this.king_club = value;
				return true;
			} else if(this.king_club == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("club queen"))
			if(this.queen_club == -1) {
				this.queen_club = value;
				return true;
			} else if(this.queen_club == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("club jack"))
			if(this.jack_club == -1) {
				this.jack_club = value;
				return true;
			} else if(this.jack_club == value)
				return false;
			else {
				this.contradiction();
				return false;
			}

		if(feature.equals("club ten"))
			if(this.ten_club == -1) {
				this.ten_club = value;
				return true;
			} else if(this.ten_club == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		

		if(feature.equals("club rkcb"))
			if(this.rkcb_club == -1) {
				this.rkcb_club = value;
				return true;
			} else if(this.rkcb_club == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("club num_low"))
			if(this.num_club_low < value) {
				
				this.updateFeature("spade num_high", NUMCARDSINHAND - this.num_club_low);
				this.updateFeature("heart num_high", NUMCARDSINHAND - this.num_club_low);
				this.updateFeature("dia num_high", NUMCARDSINHAND - this.num_club_low);
				
				if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("club num_high", NUMCARDSINSUIT - this.num_club_low);
				if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("club num_high", NUMCARDSINSUIT - this.num_club_low);
				if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("club num_high", NUMCARDSINSUIT - this.num_club_low);
				
				this.num_club_low = value;
				return true;
			} else return false;
		
		if(feature.equals("club num_high"))
			if(this.num_club_high > value) {
				this.num_club_high = value;
				
				this.updateFeature("spade num_low", NUMCARDSINHAND - this.num_club_high);
				this.updateFeature("heart num_low", NUMCARDSINHAND - this.num_club_high);
				this.updateFeature("dia num_low", NUMCARDSINHAND - this.num_club_high);
				
				if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("club num_low", NUMCARDSINSUIT - this.num_club_high);
				if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("club num_low", NUMCARDSINSUIT - this.num_club_high);
				if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("club num_low", NUMCARDSINSUIT - this.num_club_high);
				
				return true;
			} else return false;

		if(feature.equals("club longest")) {
			if(this.longest_cl == -1) {
				this.longest_cl = value;
				
				if(this.longest_cl == 1) {
					this.updateFeature("spade longest", 0);
					this.updateFeature("heart longest", 0);
					this.updateFeature("dia longest", 0);
					
					this.updateFeature("club num_low", 4);
				}
				
				return true;
			} else if(longest_cl == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("club shortest")) {
			if(this.shortest_cl == -1) {
				this.shortest_cl = value;
				
				if(this.shortest_cl == 1) {
					this.updateFeature("spade shortest", 0);
					this.updateFeature("heart shortest", 0);
					this.updateFeature("dia shortest", 0);
				}
				
				return true;
			} else if(shortest_cl == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("club biddable")) {
			if(this.biddable_cl == -1) {
				this.biddable_cl = value;
				return true;
			} else if(biddable_cl == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}

		if(feature.equals("club stopper")) {
			if(this.stopper_cl == -1) {
				this.stopper_cl = value;
				return true;
			} else if(stopper_cl == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("club quality")) {
			if(this.quality_cl == -1) {
				this.quality_cl = value;
				return true;
			} else if(quality_cl == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("club dp")) {
			if(this.dp_cl_ha == -1) {
				this.dp_cl_ha = value;
				return true;
			} else if(this.dp_cl_ha == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		}
		
		if(feature.equals("club total_points_low")) {
			if(this.dp_cl_ha < value) {
				this.dp_cl_ha = value;
				
				// TODO: Infer
				
				return true;
			} else
				return false;
		}
		
		/* TODO: view.CreateIntWME("dp_sp_he", this.dp_sp_he); 
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
		*/
		
		return false;
	}
	
	private boolean updateFeature(String feature, double value) {
		
		if(feature.equals("ratio_low"))
			if(this.ratio_low < value) {
				this.ratio_low = value;
				return true;
			} else return false;

		if(feature.equals("ratio_high"))
			if(this.ratio_high > value) {
				this.ratio_high = value;
				return true;
			} else return false;

		if(feature.equals("intermediate_low"))
			if(this.intermediate_ha_low < value) {
				this.intermediate_ha_low = value;
				return true;
			} else return false;
		
		if(feature.equals("intermediate_high"))
			if(this.intermediate_ha_high > value) {
				this.intermediate_ha_high = value;
				return true;
			} else return false;

		if(feature.equals("spade intermediate_low"))
			if(this.intermediate_sp_low < value) {
				this.intermediate_sp_low = value;
				
				if(this.intermediate_sp_low >= 2) {
					this.updateFeature("spade ten", 1);
					
					if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("spade ten", 0);
					if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("spade ten", 0);
					if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("spade ten", 0);
				}
				
				return true;
			} else return false;
		
		if(feature.equals("spade intermediate_high"))
			if(this.intermediate_sp_high > value) {
				this.intermediate_sp_high = value;

				if(this.intermediate_sp_high < 2) {
					this.updateFeature("spade ten", 0);
				}
				
				return true;
			} else return false;

		if(feature.equals("heart intermediate_low"))
			if(this.intermediate_he_low < value) {
				this.intermediate_he_low = value;

				if(this.intermediate_he_low >= 2) {
					this.updateFeature("heart ten", 1);
					
					if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("heart ten", 0);
					if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("heart ten", 0);
					if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("heart ten", 0);
				}
				
				return true;
			} else return false;
		
		if(feature.equals("heart intermediate_high"))
			if(this.intermediate_he_high > value) {
				this.intermediate_he_high = value;

				if(this.intermediate_he_high < 2) {
					this.updateFeature("heart ten", 0);
				}
				
				return true;
			} else return false;

		if(feature.equals("dia intermediate_low"))
			if(this.intermediate_di_low < value) {
				this.intermediate_di_low = value;

				if(this.intermediate_di_low >= 2) {
					this.updateFeature("dia ten", 1);
					
					if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("dia ten", 0);
					if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("dia ten", 0);
					if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("dia ten", 0);
				}
				
				return true;
			} else return false;
		
		if(feature.equals("dia intermediate_high"))
			if(this.intermediate_di_high > value) {
				this.intermediate_di_high = value;

				if(this.intermediate_di_high < 2) {
					this.updateFeature("dia ten", 0);
				}
				
				return true;
			} else return false;
		

		if(feature.equals("club intermediate_low"))
			if(this.intermediate_cl_low < value) {
				this.intermediate_cl_low = value;

				if(this.intermediate_cl_low >= 2) {
					this.updateFeature("club ten", 1);
					
					if(!this.equals(this.player.getPartnerView())) this.player.getPartnerView().updateFeature("club ten", 0);
					if(!this.equals(this.player.getLeftOppView())) this.player.getLeftOppView().updateFeature("club ten", 0);
					if(!this.equals(this.player.getRightOppView())) this.player.getRightOppView().updateFeature("club ten", 0);
				}
				
				return true;
			} else return false;
		
		if(feature.equals("club intermediate_high"))
			if(this.intermediate_cl_high > value) {
				this.intermediate_cl_high = value;

				if(this.intermediate_cl_high < 2) {
					this.updateFeature("club ten", 0);
				}
				
				return true;
			} else return false;
		
		if(feature.equals("spade unlos_low"))
			if(this.unlos_sp_low < value) {
				this.unlos_sp_low = value;
				return true;
			} else return false;
		
		if(feature.equals("spade unlos_high"))
			if(this.unlos_sp_high > value) {
				this.unlos_sp_high = value;
				return true;
			} else return false;
		
		if(feature.equals("heart unlos_low"))
			if(this.unlos_he_low < value) {
				this.unlos_he_low = value;
				return true;
			} else return false;
		
		if(feature.equals("heart unlos_high"))
			if(this.unlos_he_high > value) {
				this.unlos_he_high = value;
				return true;
			} else return false;
		
		if(feature.equals("dia unlos_low"))
			if(this.unlos_di_low < value) {
				this.unlos_di_low = value;
				return true;
			} else return false;
		
		if(feature.equals("dia unlos_high"))
			if(this.unlos_di_high > value) {
				this.unlos_di_high = value;
				return true;
			} else return false;
		
		if(feature.equals("club unlos_low"))
			if(this.unlos_cl_low < value) {
				this.unlos_cl_low = value;
				return true;
			} else return false;
		
		if(feature.equals("club unlos_high"))
			if(this.unlos_cl_high > value) {
				this.unlos_cl_high = value;
				return true;
			} else return false;
		
		return false;
	}
	
	private void contradiction() {
		
	}
	
	private void copyValues(HandView to, HandView from) {
		try {
			to = (HandView) from.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
