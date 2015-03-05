package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import sconn.BiddingAgent;
import sml.Identifier;
import sml.IntElement;
import sml.smlRunStepSize;

public class Main {

	public static void main(String[] args) {
		
		File f = new File("out.txt");
		try {
			System.setOut(new PrintStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// TODO: Randomly deal cards or load from a file and start the bidding
		BiddingAgent a = new BiddingAgent();
		
		Identifier inLink = a.getInputLink();
		
		Identifier myView = inLink.CreateIdWME("myview");
		IntElement hcp = myView.CreateIntWME("hcp", 15);
		IntElement balanced = myView.CreateIntWME("balanced", 2);
		IntElement numhearts = myView.CreateIntWME("num-hearts", 6);
		
		Identifier partnerView = inLink.CreateIdWME("pview");
		Identifier leftView = inLink.CreateIdWME("lview");
		Identifier rightView = inLink.CreateIdWME("rview");
		
		Identifier tView = inLink.CreateIdWME("tview");
		Identifier oView = inLink.CreateIdWME("oview");
		
		Identifier history = inLink.CreateIdWME("bidhistory");
		
		a.getAgent().RunSelf(2, smlRunStepSize.sml_PHASE);
		System.out.println(a.executeCommand("print --depth 10 s1"));
		a.getAgent().RunSelfForever();
	}
}
