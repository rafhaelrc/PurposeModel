/*
 * Case 1 - with artifact. 
+receivedPaperNote(Buyer) <- println("received a payment from: ", Buyer);
							 deliverBook(Buyer).
*/	

/*
 * Case 2 
 *
+payment(Buyer) <-  println("Teste tooom.");
					deliverBook(Buyer).


*/

/*
 * Case 3
 */

+obligation(Ag,R,Goal,Deadline) //the agent perceives the obligation following the NPL notation
   : .my_name(Ag) 
   <- println("I am obliged to see to me that the state ",Goal," holds");
   	  Goal; 
   	  .
   

//connect norms to institution
+!setup_sai: focusing(ArtSai,inst_test_art,_,_,inst_test,_) & focusing(NplArt,nplArt,_,_,wsp_npl,_) <-
   println("entrei no plano");
   getSaiEngine(SE)[artifact_id(ArtSai)];
   setInstitution(SE)[artifact_id(NplArt)];
   load("src/org/norms.npl").   

+!setup_sai<-
    .wait(focusing(A,_,_,B,inst_test,_)&focusing(ArtSai,inst_test_art,_,_,inst_test,_) & focusing(NplArt,nplArt,_,_,wsp_npl,_));
    !setup_sai.





{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
