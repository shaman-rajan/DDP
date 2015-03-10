package sconn;

import java.util.Scanner;

import sml.Agent;
import sml.FloatElement;
import sml.Identifier;
import sml.IntElement;
import sml.Kernel;
import sml.smlPrintEventId;
import sml.smlRunStepSize;
import sml.Agent.PrintEventInterface;

public class TestAgent {
	
	public static void createAndRunAgent() {
		Scanner s = new Scanner(System.in);
		Kernel k = Kernel.CreateKernelInNewThread();
		Agent a = k.CreateAgent("soar");
		
		a.RegisterForPrintEvent(smlPrintEventId.smlEVENT_PRINT, myPrinter, null);
		a.ExecuteCommandLine("watch 5");
		a.ExecuteCommandLine("watch --fullwmes");
		
		a.ExecuteCommandLine("sp {out (state <s> ^io.input-link <in> ^io.output-link <out>) \n" +
				"(<in> ^pi <pival> ^iden.integer <intval>) \n" +
				"--> \n" +
				"(<out> ^misery <one>) \n" +
				"(<one> ^mulval (* <pival> <intval>))}");

		Identifier inLink = a.GetInputLink();
		Identifier iden = inLink.CreateIdWME("iden");
		FloatElement fl = inLink.CreateFloatWME("pi", 3.1416);
		IntElement il = iden.CreateIntWME("integer", 20);
		a.RunSelf(1, smlRunStepSize.sml_PHASE);
		System.out.println(a.ExecuteCommandLine("print --depth 10 s1"));
		
		String inp = "";
		while(!inp.equals("no")) {
			a.RunSelf(1, smlRunStepSize.sml_DECISION);
			
			inp = s.next();
			System.out.println(a.ExecuteCommandLine("print " + inp));
		}
		
		a.RunSelfTilOutput();
		int num_out = a.GetNumberCommands();
		for(int i=0; i<num_out; ++i) {
			Identifier id = a.GetCommand(i);
			int num_child = id.GetNumberChildren();
			System.out.println(id.GetAttribute() + ":");
			for(int j=0; j<num_child; ++j)
				System.out.println(id.GetChild(j).GetAttribute() + ": " + id.GetChild(j).GetValueAsString());
			id.AddStatusComplete();
		}
		
		a.RunSelf(5, smlRunStepSize.sml_PHASE);
		System.out.println(a.ExecuteCommandLine("print --depth 10 s1"));
		
		k.Shutdown();
	}
	
	public static final PrintEventInterface myPrinter = new PrintEventInterface() {
		public void printEventHandler(int eventID, Object data, Agent agent, String message) {
			System.out.println("Soar said:" + message);
		}
	};
}
