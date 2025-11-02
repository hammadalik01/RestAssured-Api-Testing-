Feature: Validation Of Trello Boards 

Scenario Outline:
Verify a new Board is successfully created and fetched using API 

	Given Add Board Payload with Board name "<Name>" 
	When User call "AddBoardAPI" by using "post" request 
	Then API call is successfully with 200 status code 
	And Verify all important details of the created Board with name "<Name>" and URL containing "<url>" 
	
	Given Board ID is stored from previous response 
	When User call "GetBoardAPI" by using "get" request 
	Then API call is successfully with 200 status code 
	And Verify all important details of the created Board with name "<Name>" and URL containing "<url>" 
	
	Examples: 
		| Name  | url                  |
		| First | https://trello.com/b/ |
		
Scenario: Update a board's name using UpdateBoardAPI 

	Given Update Board Payload with Board name "hammad868_Updated" 
	When User call "UpdateBoardAPI" by using "put" request 
	Then API call is successfully with 200 status code 
	And Verify Board name is updated to "hammad868_Updated" 
	
Scenario: Verify the updated board name is reflected when fetching board details 

	Given Board ID is stored from previous response 
	When User call "GetBoardAPI" by using "get" request 
	Then API call is successfully with 200 status code 
	And Verify all important details of the updated Board with name "hammad868_Updated" 
