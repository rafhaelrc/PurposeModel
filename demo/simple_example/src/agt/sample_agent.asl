// Agent sample_agent in project new_test

/* Initial beliefs and rules */

status(commanding_an_attack).

/* Initial goals */

!start.

/* Plans */

+!start : true <-
	.print("Let's use an ontology");
	/*getInstances("Beagle", Instances);
	.print("Instances: ",Instances);
	!print(Instances);
	*/
	?status(Status);
	.print("Status: ", Status);
	//getPurposesOfStatusFunctions(Status, NamePurposes);
	//!percorreList(NamePurposes);
	.


 +!percorreList([]).

+!percorreList([H|T])
<-
	.print("Purpose: " , H);
	.term2string(Te,H);
	//!searchActionCountAsStatus(Te);
	!percorreList(T);
	.



{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
