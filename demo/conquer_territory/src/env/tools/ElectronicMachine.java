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
	public void broadcast_a_message() {
		System.out.println("broadcast_a_message...");
		// implement the action..
	}
	
	@OPERATION
	public void posting_on_a_webservice() {
		System.out.println("posting_on_a_webservice...");
		// implement the action..
	}
}
