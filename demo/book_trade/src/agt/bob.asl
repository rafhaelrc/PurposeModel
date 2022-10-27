bookOwner(tom).


!holdBook.
/* 
 * Version with artefact. 
 *
+!holdBook : bookOwner(Seller) 
	<- deliverPaperNote(Seller); // Seller is a string
	   .wait(1000);
	   .my_name(Me);
	   .term2string(Me, Name); // vem string do artefato
	   ?deliverBook(Name);
	   .
*/


+!holdBook : bookOwner(Seller)
	<- .my_name(Me);
	   .send(tom,tell,deliverPaperNote(Me));
	   .wait(1000);
	   ?deliverBook(Me);
	   .



{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
