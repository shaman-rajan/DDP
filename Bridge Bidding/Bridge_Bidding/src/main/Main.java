package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import rep.Deal;
import sconn.TestAgent;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		
		File f = new File("out.txt");
		try {
			System.setOut(new PrintStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Deal d = new Deal();
		d.askForNextBid();
		
		//TestAgent.createAndRunAgent();
	}
}
