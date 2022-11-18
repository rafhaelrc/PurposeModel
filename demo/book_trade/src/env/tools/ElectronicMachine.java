package tools;

import cartago.Artifact;
import cartago.OPERATION;
import java.io.IOException;
import java.util.logging.Logger;


public class ElectronicMachine extends Artifact{
	private Logger logger = Logger.getLogger(ElectronicMachine.class.getName());
	
	@OPERATION
	public void init()  {
		defineObsProperty("task", 0);
		
	}

	@OPERATION
	public void deliver_paper_note(String seller) {
		//defineObsProperty("deliver_paper_note", "bob"); // case 01
		System.out.println("Ação feita...");
		defineObsProperty("payment", "bob"); // case 02
		// implement the action..
		
		
	}
	@OPERATION
	public void deliverBook(String buyer) {
		defineObsProperty("deliverBook");
		// implement the action..
	}
}
