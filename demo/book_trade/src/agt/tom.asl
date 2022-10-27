/*
 *
+receivedPaperNote(Buyer) <- println("received a payment from: ", Buyer);
							 deliverBook(Buyer).
*/	


+deliverPaperNote(Agent) <- print("Received a paper note from: " , Agent);
							.send(bob,tell,deliverBook(Agent)).













{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
