package rep;

import sml.Identifier;


public class HandView {
	
	Hand view;
	
	public HandView(Hand fullHand) {
		try {
			view = fullHand.copy();
		} catch (CloneNotSupportedException e) {
			System.out.println("Cannot clone");
			e.printStackTrace();
		}
	}
	
	public HandView(HandView hv) {
		try {
			view = hv.view.copy();
		} catch (CloneNotSupportedException e) {
			System.out.println("Cannot clone");
			e.printStackTrace();
		}
	}
	
	public HandView() {
		this.view = new Hand();
	}
	
	/*
	 * Interface with SOAR
	 */
	public void addToSoarIdentifier(Identifier view) {
		// TODO: Add details of the view to the SOAR identifier
		view.CreateIntWME("hcp", this.view.points_hc);
		view.CreateIntWME("hcp_sp", this.view.points_spade);
		view.CreateIntWME("hcp_he", this.view.points_heart);
		view.CreateIntWME("hcp_di", this.view.points_dia);
		view.CreateIntWME("hcp_cl", this.view.points_club);
		view.CreateIntWME("controls", this.view.controls);
		view.CreateIntWME("controls_sp", this.view.controls_spade);
		view.CreateIntWME("controls_he", this.view.controls_heart);
		view.CreateIntWME("controls_di", this.view.controls_dia);
		view.CreateIntWME("controls_cl", this.view.controls_club);
		view.CreateIntWME("hc", this.view.highCards);
		view.CreateIntWME("hc_sp", this.view.highCards_spade);
		view.CreateIntWME("hc_he", this.view.highCards_heart);
		view.CreateIntWME("hc_di", this.view.highCards_dia);
		view.CreateIntWME("hc_cl", this.view.highCards_club);
		view.CreateIntWME("honors", this.view.honors);
		view.CreateIntWME("honors_sp", this.view.honors_spade);
		view.CreateIntWME("honors_he", this.view.honors_heart);
		view.CreateIntWME("honors_di", this.view.honors_dia);
		view.CreateIntWME("honors_cl", this.view.honors_club);
		
		view.CreateIntWME("aces", this.view.aces);
		view.CreateIntWME("kings", this.view.kings);
		view.CreateIntWME("queens", this.view.queens);
		view.CreateIntWME("jacks", this.view.jacks);
		view.CreateIntWME("tens", this.view.tens);
		view.CreateIntWME("ace_sp", this.view.ace_spade);
		view.CreateIntWME("king_sp", this.view.king_spade);
		view.CreateIntWME("queen_sp", this.view.queen_spade);
		view.CreateIntWME("jack_sp", this.view.jack_spade);
		view.CreateIntWME("ten_he", this.view.ten_heart);
		view.CreateIntWME("ace_he", this.view.ace_heart);
		view.CreateIntWME("king_he", this.view.king_heart);
		view.CreateIntWME("queen_he", this.view.queen_heart);
		view.CreateIntWME("jack_he", this.view.jack_heart);
		view.CreateIntWME("ten_he", this.view.ten_heart);
		view.CreateIntWME("ace_di", this.view.ace_dia);
		view.CreateIntWME("king_di", this.view.king_dia);
		view.CreateIntWME("queen_di", this.view.queen_dia);
		view.CreateIntWME("jack_di", this.view.jack_dia);
		view.CreateIntWME("ten_di", this.view.ten_dia);
		view.CreateIntWME("ace_cl", this.view.ace_club);
		view.CreateIntWME("king_cl", this.view.king_club);
		view.CreateIntWME("queen_cl", this.view.queen_club);
		view.CreateIntWME("jack_cl", this.view.jack_club);
		view.CreateIntWME("ten_cl", this.view.ten_club);
		view.CreateIntWME("rkcb_sp", this.view.rkcb_spade);
		view.CreateIntWME("rkcb_he", this.view.rkcb_heart);
		view.CreateIntWME("rkcb_di", this.view.rkcb_dia);
		view.CreateIntWME("rkcb_cl", this.view.rkcb_club);
		
		view.CreateIntWME("num_sp", this.view.num_spade);
		view.CreateIntWME("num_he", this.view.num_heart);
		view.CreateIntWME("num_di", this.view.num_dia);
		view.CreateIntWME("num_cl", this.view.num_club);
		view.CreateIntWME("balanced", this.view.balanced);
		
		view.CreateIntWME("dp_sp_he", this.view.dp_sp_he); 
		view.CreateIntWME("dp_sp_di", this.view.dp_sp_di);
		view.CreateIntWME("dp_sp_cl", this.view.dp_sp_cl);
		view.CreateIntWME("dp_sp_ha", this.view.dp_sp_ha);
		view.CreateIntWME("dp_he_sp", this.view.dp_he_sp);
		view.CreateIntWME("dp_he_di", this.view.dp_he_di);
		view.CreateIntWME("dp_he_cl", this.view.dp_he_cl);
		view.CreateIntWME("dp_he_ha", this.view.dp_he_ha);
		view.CreateIntWME("dp_di_sp", this.view.dp_di_sp);
		view.CreateIntWME("dp_di_he", this.view.dp_di_he);
		view.CreateIntWME("dp_di_cl", this.view.dp_di_cl);
		view.CreateIntWME("dp_di_ha", this.view.dp_di_ha);
		view.CreateIntWME("dp_cl_sp", this.view.dp_cl_sp);
		view.CreateIntWME("dp_cl_he", this.view.dp_cl_he);
		view.CreateIntWME("dp_cl_di", this.view.dp_cl_di);
		view.CreateIntWME("dp_cl_ha", this.view.dp_cl_ha);
		
		view.CreateIntWME("num_suits", this.view.num_suits);
		view.CreateIntWME("lmaj", this.view.lmaj);
		view.CreateIntWME("lmin", this.view.lmin);
		
		view.CreateIntWME("longest_sp", this.view.longest_sp);
		view.CreateIntWME("longest_he", this.view.longest_he);
		view.CreateIntWME("longest_di", this.view.longest_di);
		view.CreateIntWME("longest_cl", this.view.longest_cl);
		view.CreateIntWME("shortest_sp", this.view.shortest_sp);
		view.CreateIntWME("shortest_he", this.view.shortest_he);
		view.CreateIntWME("shortest_di", this.view.shortest_di);
		view.CreateIntWME("shortest_cl", this.view.shortest_cl);
		
		view.CreateIntWME("biddable_sp", this.view.biddable_sp);
		view.CreateIntWME("biddable_he", this.view.biddable_he);
		view.CreateIntWME("biddable_di", this.view.biddable_di);
		view.CreateIntWME("biddable_cl", this.view.biddable_cl);
		
		view.CreateFloatWME("intermediate_sp", this.view.intermediate_sp);
		view.CreateFloatWME("intermediate_he", this.view.intermediate_he);
		view.CreateFloatWME("intermediate_di", this.view.intermediate_di);
		view.CreateFloatWME("intermediate_cl", this.view.intermediate_cl);
		
		view.CreateIntWME("losers_sp_he", this.view.losers_sp_he); 
		view.CreateIntWME("losers_sp_di", this.view.losers_sp_di);
		view.CreateIntWME("losers_sp_cl", this.view.losers_sp_cl);
		view.CreateIntWME("losers_sp_ha", this.view.losers_sp_ha);
		view.CreateIntWME("losers_he_sp", this.view.losers_he_sp);
		view.CreateIntWME("losers_he_di", this.view.losers_he_di);
		view.CreateIntWME("losers_he_cl", this.view.losers_he_cl);
		view.CreateIntWME("losers_he_ha", this.view.losers_he_ha);
		view.CreateIntWME("losers_di_sp", this.view.losers_di_sp);
		view.CreateIntWME("losers_di_he", this.view.losers_di_he);
		view.CreateIntWME("losers_di_cl", this.view.losers_di_cl);
		view.CreateIntWME("losers_di_ha", this.view.losers_di_ha);
		view.CreateIntWME("losers_cl_sp", this.view.losers_cl_sp);
		view.CreateIntWME("losers_cl_he", this.view.losers_cl_he);
		view.CreateIntWME("losers_cl_di", this.view.losers_cl_di);
		view.CreateIntWME("losers_cl_ha", this.view.losers_cl_ha);
		
		view.CreateFloatWME("stopper_sp", this.view.stopper_sp);
		view.CreateFloatWME("stopper_he", this.view.stopper_he);
		view.CreateFloatWME("stopper_di", this.view.stopper_di);
		view.CreateFloatWME("stopper_cl", this.view.stopper_cl);

		view.CreateIntWME("tr_stopper_sp_ha", this.view.tr_stopper_sp_sp);
		view.CreateIntWME("tr_stopper_sp_he", this.view.tr_stopper_sp_he); 
		view.CreateIntWME("tr_stopper_sp_di", this.view.tr_stopper_sp_di);
		view.CreateIntWME("tr_stopper_sp_cl", this.view.tr_stopper_sp_cl);
		view.CreateIntWME("tr_stopper_he_sp", this.view.tr_stopper_he_sp);
		view.CreateIntWME("tr_stopper_he_ha", this.view.tr_stopper_he_he);
		view.CreateIntWME("tr_stopper_he_di", this.view.tr_stopper_he_di);
		view.CreateIntWME("tr_stopper_he_cl", this.view.tr_stopper_he_cl);
		view.CreateIntWME("tr_stopper_di_sp", this.view.tr_stopper_di_sp);
		view.CreateIntWME("tr_stopper_di_he", this.view.tr_stopper_di_he);
		view.CreateIntWME("tr_stopper_di_ha", this.view.tr_stopper_di_di);
		view.CreateIntWME("tr_stopper_di_cl", this.view.tr_stopper_di_cl);
		view.CreateIntWME("tr_stopper_cl_sp", this.view.tr_stopper_cl_sp);
		view.CreateIntWME("tr_stopper_cl_he", this.view.tr_stopper_cl_he);
		view.CreateIntWME("tr_stopper_cl_di", this.view.tr_stopper_cl_di);
		view.CreateIntWME("tr_stopper_cl_ha", this.view.tr_stopper_cl_cl);
		
		view.CreateFloatWME("quality_sp", this.view.quality_sp);
		view.CreateFloatWME("quality_he", this.view.quality_he);
		view.CreateFloatWME("quality_di", this.view.quality_di);
		view.CreateFloatWME("quality_cl", this.view.quality_cl);
		
		view.CreateFloatWME("ratio", this.view.ratio);
	}
	
}
