package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import rep.Bid;
import rep.BidHistory;
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
		
		GUI.createAndShowGUI(false);
		
		//TestAgent.createAndRunAgent();
	}
}
