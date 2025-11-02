Feature: Validation of Trello Lists 

Scenario: Create a new list under an existing board 
	Given Board ID is stored from previous response 
	And Add List Payload with List name "Backlog" 
	When User call "CREATE_LIST_API" by using "post" request 
	Then API call is successfully with 200 status code 
	And Verify List details with name "Backlog" and associated Board ID 
	
Scenario: Update the existing list name 
	Given List ID is stored from previous response 
	And Update List Payload with List name "Backlog_one_Update" 
	When User call "UPDATE_LIST_API" by using "put" request 
	Then API call is successfully with 200 status code 
	And Verify List details with name "Backlog_one_Update" and associated Board ID 
	
Scenario: Fetch an existing list by ID 
	Given List ID is stored from previous response 
	When User call "GET_LIST_API" by using "get" request 
	Then API call is successfully with 200 status code 
	And Verify List details with name "Backlog_one_Update" and associated Board ID 
	And Verify datasource filter is false 
	
Scenario: Move an existing list to another board 
	Given A new board is created for moving list with name "Destination_Board" 
	And Store its Board ID as newBoardId 
	And List ID is stored from previous response 
	When User call "MOVE_LIST_TO_BOARD_API" by using "put" request 
	Then API call is successfully with 200 status code 
	And Verify the list has been moved to new board successfully 