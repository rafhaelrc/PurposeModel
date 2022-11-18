anti_goal(soldier_killed_from_allied_basee).
cause(forcing_an_attack, territory_conquered).
cause(forcing_an_attack, soldier_killed_from_allied_base).
constitutive_rule(broadcast_a_message,forcing_an_attack,_,_).
		   
!territory_conquered. // agent goal

+!territory_conquered 
		<- if(anti_goal(AG) & cause(forcing_an_attack,AG)){
				.fail;
		   } else{
		   		?constitutive_rule(Action,forcing_an_attack,_,_);
		   		Action;
		   }.


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }