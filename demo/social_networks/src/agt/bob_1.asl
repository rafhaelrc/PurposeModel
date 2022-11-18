anti_goal(fake_news_spread).
cause(sendMessageByTwitter, fake_news_spread).
cause(talkWithBot, fake_news_spread).
cause(uploadAPicture, fake_news_spread).
cause(uploadAMessage, fake_news_spread).

//knet(telegram).	
		   
!published_info. // agent goal

+!published_info : knet(twitter)
		<- if(anti_goal(AG) & cause(sendMessageByTwitter,AG)){
				.fail;
		   } else{
		   		sendMessageByTwitter;
		   }.

+!published_info : knet(telegram)
		<- if(anti_goal(AG) & cause(talkWithBot,AG)){
				.fail;
		   } else{
		   		talkWithBot;
		   }.

+!published_info : knet(instagram)
		<- if(anti_goal(AG) & cause(uploadAPicture,AG)){
				.fail;
		   } else{
		   		uploadAPicture;
		   }.
		   
+!published_info : knet(facebook)
		<- if(anti_goal(AG) & cause(uploadAMessage,AG)){
				.fail;
		   } else{
		   		uploadAMessage;
		   }.
		   
{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
