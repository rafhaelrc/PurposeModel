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
		//startTwitter();
		//sendTelegram();
		//sendFacebook();
		//sendInstagram();
		
	}

	@OPERATION
	public void deliverPaperNote(String seller) {
		System.out.println("Apenas testando...  "  + seller);
		defineObsProperty("receivedPaperNote", "bob"); // case 01
		defineObsProperty("payment", "bob"); // case 02
	}
	
	@OPERATION
	public void deliverBook(String buyer) {
		System.out.println("Nome do comprador:  "  + buyer);
		//defineObsProperty("deliveredBook", buyer);
		defineObsProperty("deliveredBook");
		
	}

}
