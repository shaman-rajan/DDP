package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import rep.Deal;
import rep.HandView;
import sconn.TestAgent;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String inp = "";
		
		File f = new File("out.txt");
		try {
			System.setOut(new PrintStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Deal d = new Deal();
		
		HandView h = new HandView(null);
		
		while(!h.matchesHand(d.getNorth().getHand()) && !inp.equals("no")) {
			System.out.println("Did not match HCP: " + d.getNorth().getHand().points_hc + " NumSpade: " + d.getNorth().getHand().num_spade);
			//inp = s.next();
			d = new Deal();
		}
		
		d.askForNextBid();
		
		//TestAgent.createAndRunAgent();
	}
}
