localhost:8080/account/getAllAccounts

localhost:8080/bcm/addDilyStatus ----> for submit button

localhost:8080/bcm/leader/getLeader/{accountId}

localhost:8080/location/getAllLocation

localhost:8080/project/getProject/{accountId}


Rquest payload for submit action

         Date date;
	int locationId;
	int accountId;
	int leaderId;
	int projectId;
	String status;
	int teamSize;
	int loogedCount;
	String DeliveryOnTrack;
	int targetPercentage;
	int actualPercentage;
	String milestone;
	String deliveryChallenge;
	String mitigationPlan;
	String wfhChallenge;
	String keyDeliverable;

