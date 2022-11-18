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
	public void sendMessageByTwitter() {
		System.out.println("sendMessageByTwitter...");
		// implement the action..
	}
	
	@OPERATION
	public void talkWithBot() {
		System.out.println("talkWithBot...");
		// implement the action..
	}
	
	@OPERATION
	public void uploadAPicture() {
		System.out.println("uploadAPicture...");
		// implement the action..
	}
	
	@OPERATION
	public void uploadAMessage() {
		System.out.println("uploadAMessage...");
		// implement the action..
	}
}
