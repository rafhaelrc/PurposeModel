bookOwner(tom). // Case 1 
//statusFunction(payment(tom)). // Case 2 


!holdBook.
/* 
 *  * Case 1 - with artifact. 
 */
/* 
+!holdBook : bookOwner(Seller) 
	<- deliver_paper_note(Seller); // Seller is a string
	   .wait(deliverBook);
	   .
*/

/**
 * Case 2 and 3 - with institution
*/

/*
+!holdBook : statusFunction(SF) & bookOwner(Seller)
			<- println("SF: ", SF);
			   println("DONO: ", Seller);
			   ?constitutive_rule(Action,SF,_,_);
			   Action;
			   .wait(deliverBook);
			   .
 */
 
/*
 * Case 4  
 */
 
 +!holdBook : bookOwner(X)  <- !alg1(holdBook, Actions);
 	           .queue.head(Actions, Action);
 	           //Action(X);
 	           println("Action: ", Action);
 	           //Action;
 			   .
 			   
 			   
 			   
/*
+!teste <- .print("teste");
		   .send(tom,tell,payment);
		   if(anti_goal(AG) & cause(command_an_attack,AG)){
		   	  .print("entrei no if");	
		   }.

+!testeAlg1
	<-  !alg1(publishedInformation,Actions);
	    .queue.head(Actions,Action); 
		Action;
		.
	

+!testeAlg2
	<- !alg2(sendMessageByTwitter, States);
	   .print("States: " , States);
	   .
	   
	   
+!testeAlg3
	<- !alg3(publishedInformation,sendMessageByTwitter, R);
	   if(R){
	   	.print("Goal");
	   }
	   .

+!testeAlg4
	<- !alg4(fakeNewsSpread,sendMessageByTwitter, R);
	   if(R){
	   	.print("Anti-Goal");
	   }
	   .
 */
+!alg1(Goal, Actions)
	<- 	.queue.create(Actions); 
		getPurposesOfState(Goal, Purposes);
		for(.member(Purpose,Purposes)){
	   		getStatusFunctionsFromPurpose(Purpose, NameStatusFunctions);
		  	for(.member(StatusFunction, NameStatusFunctions)){
		  		/*
		  		if(.string(StatusFunction)){
		  			.print("SF is STRING...");
		  		}
		  		println("SF ENCONTRADA: ", StatusFunction);
		  		.term2string(TermStatusFunction,StatusFunction);
		  		?constitutive_rule(Action,TermStatusFunction,_,_);
		  		
		  		*/
		  		?constitutive_rule(Action,payment(X),_,_);
		  		.queue.add(Actions,Action);
		  	}
		 } 	
		.


+!alg1(Goal, []).


+!alg2(Action, States) : constitutive_rule(Action,StatusFunction,_,_)
	<- .queue.create(States); // S = {}
	   getPurposesOfStatusFunctions(StatusFunction, Purposes);
		for(.member(Purpose,Purposes)){
			getPredicatesOfStatesRelatedToPurpose(Purpose, Predicates);
			for(.member(Predicate,Predicates)){ // necessário porque um estado pode ser formado por uma lista de predicados
				.queue.add(States, Predicate);
			}
		}
		.


+!alg2(Action, []).


+!alg3(Goal, Action, R)
	<- !alg2(Action,States);
	   R = .member(Goal,States).


+!alg4(AntiGoal, Action, R)
	<- !alg2(Action,States);
	   R = .member(AntiGoal,States).
	   
	   

+!percorreList([]).

+!percorreList([H|T])
<-
	.print("Element: " , H);
	.term2string(Te,H);
	!percorreList(T);
	.




{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
