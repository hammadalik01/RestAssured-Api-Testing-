Feature: Validation of Trello Labels

Scenario: Create a new label under an existing board
  Given Board ID is stored from previous response
  And Add Label Payload with Label name "Urgent" and color "yellow"
  When User call "CREATE_LABEL_API" by using "post" request
  Then API call is successfully with 200 status code
  And Verify Label details with name "Urgent", color "yellow" and associated Board ID
  
 Scenario: Update an existing label
  Given Label ID is stored from previous response
  And Update Label Payload with name "Urgent Update" and color "purple"
  When User call "UPDATE_LABEL_API" by using "put" request
  Then API call is successfully with 200 status code
  And Verify Label details with name "Urgent Update" and color "purple"
  
 Scenario: Get an existing label by ID
  Given Label ID is stored from previous response
  When User call "GET_LABEL_API" by using "get" request
  Then API call is successfully with 200 status code
  And Verify Label details with name "Urgent Update" and color "purple"
  
 Scenario: Delete an existing label
  Given Label ID is stored from previous response
  When User call "DELETE_LABEL_API" by using "delete" request
  Then Verify Label is deleted successfully
  