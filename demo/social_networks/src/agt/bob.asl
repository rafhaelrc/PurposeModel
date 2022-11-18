anti_goal(fake_news_spread).


!published_info. // agent goal

+!published_info 
	<- !alg1(published_info,Actions);
	   !try_actions(Actions).
				   
+!try_actions(Actions) <- 
	if(.length(Actions, Size) & Size < 1){
		.fail;	
	}
	.queue.head(Actions,Action);
	!alg2(Action, States);
    if(anti_goal(AG) & .member(AG, States)){
    	.queue.remove(Actions, Action);
        !try_actions(Actions);
	}
    else{
    	Action;             	
	}.



				   
//+!try_actions([]) <-  .fail. // no action to achieve the goal



/*
+!teste <- .print("teste");
		   .send(tom,tell,payment);
		   if(anti_goal(AG) & cause(command_an_attack,AG)){
		   	  .print("entrei no if");	
		   }.

+!testeAlg1
	<-  !alg1(territory_conquered,Actions);
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
		  		.term2string(TermStatusFunction,StatusFunction);
		  		?constitutive_rule(Action,TermStatusFunction,_,_);
		  		.queue.add(Actions, Action);
		  	}
		 }.

+!alg1(Goal, []).

+!percorreList([]).

+!percorreList([H|T])
<-
	.print("Element: " , H);
	.term2string(Te,H);
	!percorreList(T);
	.

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
	   
	   






{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }