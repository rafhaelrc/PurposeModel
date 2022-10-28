bookOwner(tom). // Case 1 
statusFunction(payment(tom)). // Case 2 


!holdBook.
/* 
 *  * Case 1 - with artifact. 
 *
+!holdBook : bookOwner(Seller) 
	<- deliverPaperNote(Seller); // Seller is a string
	   .wait(1000);
	   .my_name(Me);
	   .term2string(Me, Name); 
	   ?deliveredBook(Name);
	   .
*/

/**
 * Case 2 - with institution
*/
+!holdBook : statusFunction(SF) & bookOwner(Seller)
			<- ?constitutive_rule(Action,SF,_,_);
			   println("Ação: ", Action);
			   //Action(Seller);
			   //deliverPaperNote(Seller);
			   Action;
			   .wait(1000);
			   ?deliveredBook
			   .
 

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
