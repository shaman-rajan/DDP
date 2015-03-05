package sconn;

import sml.Agent;
import sml.Identifier;
import sml.Kernel;
import sml.smlPrintEventId;
import sml.Agent.PrintEventInterface;

public class BiddingAgent {
	
	private Kernel k;
	private Agent a;
	
	public BiddingAgent() {
		k = Kernel.CreateKernelInNewThread();
		a = k.CreateAgent("soar");
		
		a.RegisterForPrintEvent(smlPrintEventId.smlEVENT_PRINT, myPrinter, null);
		a.LoadProductions("C:/Users/Rajan/Desktop/acad stuff/sem10/DDP/SOAR/bidder/bidder.soar");
		
		a.ExecuteCommandLine("watch 5");
		a.ExecuteCommandLine("watch --fullwmes");
	}
	
	public String executeCommand(String command) {
		return this.a.ExecuteCommandLine(command);
	}
	
	public Identifier getInputLink() {
		return this.a.GetInputLink();
	}
	
	public Kernel getKernel() {
		return this.k;
	}
	
	public Agent getAgent() {
		return this.a;
	}
	
	public static final PrintEventInterface myPrinter = new PrintEventInterface() {
		public void printEventHandler(int eventID, Object data, Agent agent, String message) {
			System.out.println("Soar said:" + message);
		}
	};
}
