package rep;

import rep.Bid.BidSuit;
import sml.Identifier;

public class TeamView implements Cloneable {
	
	private Player player;
	private HandView p1view, p2view;
	
	private final int MINVALUE = 0;
	private final int MAXVALUE = 100;
	private final int TOTALPOINTS = 40;
	private final int TOTALPOINTS_SUIT = 10;
	private final int NUMCARDSINHANDS = 26;
	private final int NUMCARDSINSUIT = 13;

	/*
	 * Features and their values that describe the hand
	 * Range of these values
	 */
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
	
	public int num_spade_low, num_spade_high, 
				num_heart_low, num_heart_high,
				num_dia_low, num_dia_high,
				num_club_low, num_club_high;
	
	public BidSuit trumpDecided;
	public BidSuit trumpSuggested;
	public int gameForced;
	public boolean toPlay;
	public boolean slamPoss;
	public boolean invited;
	
	
	public TeamView(TeamView hv, Player p) {
		copyValues(this, hv);
		this.player = p;
	}
	
	public TeamView(HandView hv1, HandView hv2, Player p) {
		this.player = p;
		this.p1view = hv1;
		this.p2view = hv2;
		
		points_hc_low = hv1.points_hc_low + hv2.points_hc_low;
		points_hc_high = hv1.points_hc_high + hv2.points_hc_high;
		points_spade_low = hv1.points_spade_low + hv2.points_spade_low;
		points_spade_high = hv1.points_spade_high + hv2.points_spade_high; 
		points_heart_low = hv1.points_heart_low + hv2.points_heart_low;
		points_heart_high = hv1.points_heart_high + hv2.points_heart_high; 
		points_dia_low = hv1.points_dia_low + hv2.points_dia_low;
		points_dia_high = hv1.points_dia_high + hv2.points_dia_high; 		
		points_club_low = hv1.points_club_low + hv2.points_club_low;
		points_club_high = hv1.points_club_high + hv2.points_club_high; 
				
		controls_low = hv1.controls_low + hv2.controls_low;
		controls_high = hv1.controls_high + hv2.controls_high;
		controls_spade_low = hv1.controls_spade_low + hv2.controls_spade_low;
		controls_spade_high = hv1.controls_spade_high + hv2.controls_spade_high; 
		controls_heart_low = hv1.controls_heart_low + hv2.controls_heart_low;
		controls_heart_high = hv1.controls_heart_high + hv2.controls_heart_high; 
		controls_dia_low = hv1.controls_dia_low + hv2.controls_dia_low;
		controls_dia_high = hv1.controls_dia_high + hv2.controls_dia_high; 		
		controls_club_low = hv1.controls_club_low + hv2.controls_club_low;
		controls_club_high = hv1.controls_club_high + hv2.controls_club_high; 
		
		highCards_low = hv1.highCards_low + hv2.highCards_low;
		highCards_high = hv1.highCards_high + hv2.highCards_high;
		highCards_spade_low = hv1.highCards_spade_low + hv2.highCards_spade_low;
		highCards_spade_high = hv1.highCards_spade_high + hv2.highCards_spade_high; 
		highCards_heart_low = hv1.highCards_heart_low + hv2.highCards_heart_low;
		highCards_heart_high = hv1.highCards_heart_high + hv2.highCards_heart_high; 
		highCards_dia_low = hv1.highCards_dia_low + hv2.highCards_dia_low;
		highCards_dia_high = hv1.highCards_dia_high + hv2.highCards_dia_high; 		
		highCards_club_low = hv1.highCards_club_low + hv2.highCards_club_low;
		highCards_club_high = hv1.highCards_club_high + hv2.highCards_club_high; 
		
		honors_low = hv1.honors_low + hv2.honors_low;
		honors_high = hv1.honors_high + hv2.honors_high;
		honors_spade_low = hv1.honors_spade_low + hv2.honors_spade_low;
		honors_spade_high = hv1.honors_spade_high + hv2.honors_spade_high; 
		honors_heart_low = hv1.honors_heart_low + hv2.honors_heart_low;
		honors_heart_high = hv1.honors_heart_high + hv2.honors_heart_high; 
		honors_dia_low = hv1.honors_dia_low + hv2.honors_dia_low;
		honors_dia_high = hv1.honors_dia_high + hv2.honors_dia_high; 		
		honors_club_low = hv1.honors_club_low + hv2.honors_club_low;
		honors_club_high = hv1.honors_club_high + hv2.honors_club_high; 
		
		aces = (hv1.aces != -1 && hv2.aces != -1) ? hv1.aces + hv2.aces : -1;
		ace_spade = (hv1.ace_spade != -1 && hv2.ace_spade != -1) ? ((hv1.ace_spade == 1 || hv2.ace_spade == 1) ? 1 : 0): -1;
		king_spade = (hv1.king_spade != -1 && hv2.king_spade != -1) ? ((hv1.king_spade == 1 || hv2.king_spade == 1) ? 1 : 0): -1;
		queen_spade = (hv1.queen_spade != -1 && hv2.queen_spade != -1) ? ((hv1.queen_spade == 1 || hv2.queen_spade == 1) ? 1 : 0): -1;
		jack_spade = (hv1.jack_spade != -1 && hv2.jack_spade != -1) ? ((hv1.jack_spade == 1 || hv2.jack_spade == 1) ? 1 : 0): -1;
		ten_spade = (hv1.ten_spade != -1 && hv2.ten_spade != -1) ? ((hv1.ten_spade == 1 || hv2.ten_spade == 1) ? 1 : 0): -1;
		
		aces = (hv1.aces != -1 && hv2.aces != -1) ? hv1.aces + hv2.aces : -1;
		kings = (hv1.kings != -1 && hv2.kings != -1) ? hv1.kings + hv2.kings : -1;
		queens = (hv1.queens != -1 && hv2.queens != -1) ? hv1.queens + hv2.queens : -1;
		jacks = (hv1.jacks != -1 && hv2.jacks != -1) ? hv1.jacks + hv2.jacks : -1;
		tens = (hv1.tens != -1 && hv2.tens != -1) ? hv1.tens + hv2.tens : -1;
		
		ace_spade = (hv1.ace_spade != -1 && hv2.ace_spade != -1) ? ((hv1.ace_spade == 1 || hv2.ace_spade == 1) ? 1 : 0): -1;
		king_spade = (hv1.king_spade != -1 && hv2.king_spade != -1) ? ((hv1.king_spade == 1 || hv2.king_spade == 1) ? 1 : 0): -1;
		queen_spade = (hv1.queen_spade != -1 && hv2.queen_spade != -1) ? ((hv1.queen_spade == 1 || hv2.queen_spade == 1) ? 1 : 0): -1;
		jack_spade = (hv1.jack_spade != -1 && hv2.jack_spade != -1) ? ((hv1.jack_spade == 1 || hv2.jack_spade == 1) ? 1 : 0): -1;
		ten_spade = (hv1.ten_spade != -1 && hv2.ten_spade != -1) ? ((hv1.ten_spade == 1 || hv2.ten_spade == 1) ? 1 : 0): -1;
		
		ace_heart = (hv1.ace_heart != -1 && hv2.ace_heart != -1) ? ((hv1.ace_heart == 1 || hv2.ace_heart == 1) ? 1 : 0): -1;
		king_heart = (hv1.king_heart != -1 && hv2.king_heart != -1) ? ((hv1.king_heart == 1 || hv2.king_heart == 1) ? 1 : 0): -1;
		queen_heart = (hv1.queen_heart != -1 && hv2.queen_heart != -1) ? ((hv1.queen_heart == 1 || hv2.queen_heart == 1) ? 1 : 0): -1;
		jack_heart = (hv1.jack_heart != -1 && hv2.jack_heart != -1) ? ((hv1.jack_heart == 1 || hv2.jack_heart == 1) ? 1 : 0): -1;
		ten_heart = (hv1.ten_heart != -1 && hv2.ten_heart != -1) ? ((hv1.ten_heart == 1 || hv2.ten_heart == 1) ? 1 : 0): -1;
		
		ace_dia = (hv1.ace_dia != -1 && hv2.ace_dia != -1) ? ((hv1.ace_dia == 1 || hv2.ace_dia == 1) ? 1 : 0): -1;
		king_dia = (hv1.king_dia != -1 && hv2.king_dia != -1) ? ((hv1.king_dia == 1 || hv2.king_dia == 1) ? 1 : 0): -1;
		queen_dia = (hv1.queen_dia != -1 && hv2.queen_dia != -1) ? ((hv1.queen_dia == 1 || hv2.queen_dia == 1) ? 1 : 0): -1;
		jack_dia = (hv1.jack_dia != -1 && hv2.jack_dia != -1) ? ((hv1.jack_dia == 1 || hv2.jack_dia == 1) ? 1 : 0): -1;
		ten_dia = (hv1.ten_dia != -1 && hv2.ten_dia != -1) ? ((hv1.ten_dia == 1 || hv2.ten_dia == 1) ? 1 : 0): -1;
		
		ace_club = (hv1.ace_club != -1 && hv2.ace_club != -1) ? ((hv1.ace_club == 1 || hv2.ace_club == 1) ? 1 : 0): -1;
		king_club = (hv1.king_club != -1 && hv2.king_club != -1) ? ((hv1.king_club == 1 || hv2.king_club == 1) ? 1 : 0): -1;
		queen_club = (hv1.queen_club != -1 && hv2.queen_club != -1) ? ((hv1.queen_club == 1 || hv2.queen_club == 1) ? 1 : 0): -1;
		jack_club = (hv1.jack_club != -1 && hv2.jack_club != -1) ? ((hv1.jack_club == 1 || hv2.jack_club == 1) ? 1 : 0): -1;
		ten_club = (hv1.ten_club != -1 && hv2.ten_club != -1) ? ((hv1.ten_club == 1 || hv2.ten_club == 1) ? 1 : 0): -1;
		
		num_spade_low = hv1.num_spade_low + hv2.num_spade_low;
		num_spade_high = hv1.num_spade_high + hv2.num_spade_high; 
		num_heart_low = hv1.num_heart_low + hv2.num_heart_low;
		num_heart_high = hv1.num_heart_high + hv2.num_heart_high; 
		num_dia_low = hv1.num_dia_low + hv2.num_dia_low;
		num_dia_high = hv1.num_dia_high + hv2.num_dia_high; 		
		num_club_low = hv1.num_club_low + hv2.num_club_low;
		num_club_high = hv1.num_club_high + hv2.num_club_high; 
		
		trumpDecided = null;
		trumpSuggested = null;
		gameForced = -1;
		toPlay = false;
		slamPoss = false;
		invited = false;
	}
	
	public TeamView(Player p) {
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
		
		num_spade_low = MINVALUE;
		num_spade_high = MAXVALUE;		
		num_heart_low = MINVALUE;
		num_heart_high = MAXVALUE;
		num_dia_low = MINVALUE;
		num_dia_high = MAXVALUE;
		num_club_low = MINVALUE;
		num_club_high = MAXVALUE;

		trumpDecided = null;
		trumpSuggested = null;
		gameForced = -1;
		toPlay = false;
		slamPoss = false;
		invited = false;
	}
	
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
		spade.CreateIntWME("num_low", num_spade_low);
		spade.CreateIntWME("num_high", num_spade_high);
		
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
		heart.CreateIntWME("num_low", num_heart_low);
		heart.CreateIntWME("num_high", num_heart_high);
		
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
		dia.CreateIntWME("num_low", num_dia_low);
		dia.CreateIntWME("num_high", num_dia_high);
		
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
		club.CreateIntWME("num_low", num_club_low);
		club.CreateIntWME("num_high", num_club_high);
		
		if(trumpDecided != null) view.CreateStringWME("trump", trumpDecided.toString().toLowerCase());
		if(trumpSuggested != null) view.CreateStringWME("trump_suggested", trumpSuggested.toString().toLowerCase());
		
		view.CreateIntWME("game_forced", gameForced);
		view.CreateStringWME("to_play", Boolean.toString(toPlay));
		view.CreateStringWME("slam_poss", Boolean.toString(slamPoss));
		view.CreateStringWME("invited", Boolean.toString(invited));
	}
	
	public void updateHandFeatures() {
		points_hc_low = this.p1view.points_hc_low + this.p2view.points_hc_low;
		points_hc_high = this.p1view.points_hc_high + this.p2view.points_hc_high;
		points_spade_low = this.p1view.points_spade_low + this.p2view.points_spade_low;
		points_spade_high = this.p1view.points_spade_high + this.p2view.points_spade_high; 
		points_heart_low = this.p1view.points_heart_low + this.p2view.points_heart_low;
		points_heart_high = this.p1view.points_heart_high + this.p2view.points_heart_high; 
		points_dia_low = this.p1view.points_dia_low + this.p2view.points_dia_low;
		points_dia_high = this.p1view.points_dia_high + this.p2view.points_dia_high; 		
		points_club_low = this.p1view.points_club_low + this.p2view.points_club_low;
		points_club_high = this.p1view.points_club_high + this.p2view.points_club_high; 
				
		controls_low = this.p1view.controls_low + this.p2view.controls_low;
		controls_high = this.p1view.controls_high + this.p2view.controls_high;
		controls_spade_low = this.p1view.controls_spade_low + this.p2view.controls_spade_low;
		controls_spade_high = this.p1view.controls_spade_high + this.p2view.controls_spade_high; 
		controls_heart_low = this.p1view.controls_heart_low + this.p2view.controls_heart_low;
		controls_heart_high = this.p1view.controls_heart_high + this.p2view.controls_heart_high; 
		controls_dia_low = this.p1view.controls_dia_low + this.p2view.controls_dia_low;
		controls_dia_high = this.p1view.controls_dia_high + this.p2view.controls_dia_high; 		
		controls_club_low = this.p1view.controls_club_low + this.p2view.controls_club_low;
		controls_club_high = this.p1view.controls_club_high + this.p2view.controls_club_high; 
		
		highCards_low = this.p1view.highCards_low + this.p2view.highCards_low;
		highCards_high = this.p1view.highCards_high + this.p2view.highCards_high;
		highCards_spade_low = this.p1view.highCards_spade_low + this.p2view.highCards_spade_low;
		highCards_spade_high = this.p1view.highCards_spade_high + this.p2view.highCards_spade_high; 
		highCards_heart_low = this.p1view.highCards_heart_low + this.p2view.highCards_heart_low;
		highCards_heart_high = this.p1view.highCards_heart_high + this.p2view.highCards_heart_high; 
		highCards_dia_low = this.p1view.highCards_dia_low + this.p2view.highCards_dia_low;
		highCards_dia_high = this.p1view.highCards_dia_high + this.p2view.highCards_dia_high; 		
		highCards_club_low = this.p1view.highCards_club_low + this.p2view.highCards_club_low;
		highCards_club_high = this.p1view.highCards_club_high + this.p2view.highCards_club_high; 
		
		honors_low = this.p1view.honors_low + this.p2view.honors_low;
		honors_high = this.p1view.honors_high + this.p2view.honors_high;
		honors_spade_low = this.p1view.honors_spade_low + this.p2view.honors_spade_low;
		honors_spade_high = this.p1view.honors_spade_high + this.p2view.honors_spade_high; 
		honors_heart_low = this.p1view.honors_heart_low + this.p2view.honors_heart_low;
		honors_heart_high = this.p1view.honors_heart_high + this.p2view.honors_heart_high; 
		honors_dia_low = this.p1view.honors_dia_low + this.p2view.honors_dia_low;
		honors_dia_high = this.p1view.honors_dia_high + this.p2view.honors_dia_high; 		
		honors_club_low = this.p1view.honors_club_low + this.p2view.honors_club_low;
		honors_club_high = this.p1view.honors_club_high + this.p2view.honors_club_high; 
		
		aces = (this.p1view.aces != -1 && this.p2view.aces != -1) ? this.p1view.aces + this.p2view.aces : -1;
		ace_spade = (this.p1view.ace_spade != -1 && this.p2view.ace_spade != -1) ? ((this.p1view.ace_spade == 1 || this.p2view.ace_spade == 1) ? 1 : 0): -1;
		king_spade = (this.p1view.king_spade != -1 && this.p2view.king_spade != -1) ? ((this.p1view.king_spade == 1 || this.p2view.king_spade == 1) ? 1 : 0): -1;
		queen_spade = (this.p1view.queen_spade != -1 && this.p2view.queen_spade != -1) ? ((this.p1view.queen_spade == 1 || this.p2view.queen_spade == 1) ? 1 : 0): -1;
		jack_spade = (this.p1view.jack_spade != -1 && this.p2view.jack_spade != -1) ? ((this.p1view.jack_spade == 1 || this.p2view.jack_spade == 1) ? 1 : 0): -1;
		ten_spade = (this.p1view.ten_spade != -1 && this.p2view.ten_spade != -1) ? ((this.p1view.ten_spade == 1 || this.p2view.ten_spade == 1) ? 1 : 0): -1;
		
		aces = (this.p1view.aces != -1 && this.p2view.aces != -1) ? this.p1view.aces + this.p2view.aces : -1;
		kings = (this.p1view.kings != -1 && this.p2view.kings != -1) ? this.p1view.kings + this.p2view.kings : -1;
		queens = (this.p1view.queens != -1 && this.p2view.queens != -1) ? this.p1view.queens + this.p2view.queens : -1;
		jacks = (this.p1view.jacks != -1 && this.p2view.jacks != -1) ? this.p1view.jacks + this.p2view.jacks : -1;
		tens = (this.p1view.tens != -1 && this.p2view.tens != -1) ? this.p1view.tens + this.p2view.tens : -1;
		
		ace_spade = (this.p1view.ace_spade != -1 && this.p2view.ace_spade != -1) ? ((this.p1view.ace_spade == 1 || this.p2view.ace_spade == 1) ? 1 : 0): -1;
		king_spade = (this.p1view.king_spade != -1 && this.p2view.king_spade != -1) ? ((this.p1view.king_spade == 1 || this.p2view.king_spade == 1) ? 1 : 0): -1;
		queen_spade = (this.p1view.queen_spade != -1 && this.p2view.queen_spade != -1) ? ((this.p1view.queen_spade == 1 || this.p2view.queen_spade == 1) ? 1 : 0): -1;
		jack_spade = (this.p1view.jack_spade != -1 && this.p2view.jack_spade != -1) ? ((this.p1view.jack_spade == 1 || this.p2view.jack_spade == 1) ? 1 : 0): -1;
		ten_spade = (this.p1view.ten_spade != -1 && this.p2view.ten_spade != -1) ? ((this.p1view.ten_spade == 1 || this.p2view.ten_spade == 1) ? 1 : 0): -1;
		
		ace_heart = (this.p1view.ace_heart != -1 && this.p2view.ace_heart != -1) ? ((this.p1view.ace_heart == 1 || this.p2view.ace_heart == 1) ? 1 : 0): -1;
		king_heart = (this.p1view.king_heart != -1 && this.p2view.king_heart != -1) ? ((this.p1view.king_heart == 1 || this.p2view.king_heart == 1) ? 1 : 0): -1;
		queen_heart = (this.p1view.queen_heart != -1 && this.p2view.queen_heart != -1) ? ((this.p1view.queen_heart == 1 || this.p2view.queen_heart == 1) ? 1 : 0): -1;
		jack_heart = (this.p1view.jack_heart != -1 && this.p2view.jack_heart != -1) ? ((this.p1view.jack_heart == 1 || this.p2view.jack_heart == 1) ? 1 : 0): -1;
		ten_heart = (this.p1view.ten_heart != -1 && this.p2view.ten_heart != -1) ? ((this.p1view.ten_heart == 1 || this.p2view.ten_heart == 1) ? 1 : 0): -1;
		
		ace_dia = (this.p1view.ace_dia != -1 && this.p2view.ace_dia != -1) ? ((this.p1view.ace_dia == 1 || this.p2view.ace_dia == 1) ? 1 : 0): -1;
		king_dia = (this.p1view.king_dia != -1 && this.p2view.king_dia != -1) ? ((this.p1view.king_dia == 1 || this.p2view.king_dia == 1) ? 1 : 0): -1;
		queen_dia = (this.p1view.queen_dia != -1 && this.p2view.queen_dia != -1) ? ((this.p1view.queen_dia == 1 || this.p2view.queen_dia == 1) ? 1 : 0): -1;
		jack_dia = (this.p1view.jack_dia != -1 && this.p2view.jack_dia != -1) ? ((this.p1view.jack_dia == 1 || this.p2view.jack_dia == 1) ? 1 : 0): -1;
		ten_dia = (this.p1view.ten_dia != -1 && this.p2view.ten_dia != -1) ? ((this.p1view.ten_dia == 1 || this.p2view.ten_dia == 1) ? 1 : 0): -1;
		
		ace_club = (this.p1view.ace_club != -1 && this.p2view.ace_club != -1) ? ((this.p1view.ace_club == 1 || this.p2view.ace_club == 1) ? 1 : 0): -1;
		king_club = (this.p1view.king_club != -1 && this.p2view.king_club != -1) ? ((this.p1view.king_club == 1 || this.p2view.king_club == 1) ? 1 : 0): -1;
		queen_club = (this.p1view.queen_club != -1 && this.p2view.queen_club != -1) ? ((this.p1view.queen_club == 1 || this.p2view.queen_club == 1) ? 1 : 0): -1;
		jack_club = (this.p1view.jack_club != -1 && this.p2view.jack_club != -1) ? ((this.p1view.jack_club == 1 || this.p2view.jack_club == 1) ? 1 : 0): -1;
		ten_club = (this.p1view.ten_club != -1 && this.p2view.ten_club != -1) ? ((this.p1view.ten_club == 1 || this.p2view.ten_club == 1) ? 1 : 0): -1;
		
		num_spade_low = this.p1view.num_spade_low + this.p2view.num_spade_low;
		num_spade_high = this.p1view.num_spade_high + this.p2view.num_spade_high; 
		num_heart_low = this.p1view.num_heart_low + this.p2view.num_heart_low;
		num_heart_high = this.p1view.num_heart_high + this.p2view.num_heart_high; 
		num_dia_low = this.p1view.num_dia_low + this.p2view.num_dia_low;
		num_dia_high = this.p1view.num_dia_high + this.p2view.num_dia_high; 		
		num_club_low = this.p1view.num_club_low + this.p2view.num_club_low;
		num_club_high = this.p1view.num_club_high + this.p2view.num_club_high; 		
	}
	
	public boolean updateFeature(String feature, long val) {
		int value = (int) val;
		
		if(feature.equals("hcp_low"))
			if(this.points_hc_low < value) {
				this.points_hc_low = value;
				return true;
			} else return false;
		
		if(feature.equals("hcp_high"))
			if(this.points_hc_high > value) {
				this.points_hc_high = value;
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
				return true;
			} else if(this.tens == value)
				return false;
			else {
				this.contradiction();
				return false;
			}
		
		if(feature.equals("spade hcp_low"))
			if(this.points_spade_low < value) {
				this.points_spade_low = value;
				return true;
			} else return false;
		
		if(feature.equals("spade hcp_high"))
			if(this.points_spade_high > value) {
				this.points_spade_high = value;
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

		if(feature.equals("spade num_low"))
			if(this.num_spade_low < value) {
				this.num_spade_low = value;
				return true;
			} else return false;
		
		if(feature.equals("spade num_high"))
			if(this.num_spade_high > value) {
				this.num_spade_high = value;
				return true;
			} else return false;

		if(feature.equals("heart hcp_low"))
			if(this.points_heart_low < value) {
				this.points_heart_low = value;
				return true;
			} else return false;
		
		if(feature.equals("heart hcp_high"))
			if(this.points_heart_high > value) {
				this.points_heart_high = value;
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

		if(feature.equals("heart num_low"))
			if(this.num_heart_low < value) {
				this.num_heart_low = value;
				return true;
			} else return false;
		
		if(feature.equals("heart num_high"))
			if(this.num_heart_high > value) {
				this.num_heart_high = value;
				return true;
			} else return false;

		if(feature.equals("dia hcp_low"))
			if(this.points_dia_low < value) {
				this.points_dia_low = value;
				return true;
			} else return false;
		
		if(feature.equals("dia hcp_high"))
			if(this.points_dia_high > value) {
				this.points_dia_high = value;
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

		if(feature.equals("dia num_low"))
			if(this.num_dia_low < value) {
				this.num_dia_low = value;
				return true;
			} else return false;
		
		if(feature.equals("dia num_high"))
			if(this.num_dia_high > value) {
				this.num_dia_high = value;
				return true;
			} else return false;

		if(feature.equals("club hcp_low"))
			if(this.points_club_low < value) {
				this.points_club_low = value;
				return true;
			} else return false;
		
		if(feature.equals("club hcp_high"))
			if(this.points_club_high > value) {
				this.points_club_high = value;
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

		if(feature.equals("club num_low"))
			if(this.num_club_low < value) {
				this.num_club_low = value;
				return true;
			} else return false;
		
		if(feature.equals("club num_high"))
			if(this.num_club_high > value) {
				this.num_club_high = value;
				return true;
			} else return false;
		
		if(feature.equals("game_forced"))
			if(this.gameForced == -1) {
				this.gameForced = value;
				return true;
			} else if(this.gameForced == value)
				return true;
			else return false;
		
		return false;
	}
	
	public boolean updateFeature(String feature, String value) {
		if(feature.equals("trump")) {
			if(trumpDecided == null) trumpDecided = BidSuit.valueOf(value.toUpperCase());
			else return false;
			return true;
		}
		
		if(feature.equals("trump_suggested")) {
			if(trumpSuggested == null) trumpSuggested = BidSuit.valueOf(value.toUpperCase());
			else return false;
			return true;
		}
		
		if(feature.equals("to_play"))
			if(toPlay == false) {
				if(value.equals("true"))
					toPlay = true;
				return true;
			} else if(value.equals("false"))
				this.contradiction();
			else return false;
		
		if(feature.equals("slam_poss"))
			if(slamPoss == false) {
				if(value.equals("true"))
					slamPoss = true;
				return true;
			} else if(value.equals("false"))
				this.contradiction();
			else return false;
		
		if(feature.equals("invited"))
			if(invited == false) {
				if(value.equals("true")) {
					invited = true;
					// TODO: Check if game is possible, and set something
				}
				return true;
			} else if(value.equals("false"))
				this.contradiction();
			else return false;
		
		return false;
	}
	
	private void contradiction() {
		
	}
	
	private void copyValues(TeamView to, TeamView from) {
		try {
			to = (TeamView) from.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
