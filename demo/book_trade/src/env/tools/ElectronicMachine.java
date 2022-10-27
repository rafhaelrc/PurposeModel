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
		defineObsProperty("receivedPaperNote", "bob");
		//System.out.println("Apenas testando...  "  + seller);
		// propriedade observ�vel holdBook(multiAgentSystem);
		// Quem coloca o holdBook pra valer.
	}
	
	@OPERATION
	public void deliverBook(String buyer) {
		System.out.println("Nome do comprador:  "  + buyer);
		defineObsProperty("deliverBook", buyer);
		
	}

}
