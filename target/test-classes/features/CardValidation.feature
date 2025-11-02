Feature: Validation of Trello Cards

Scenario: Create a new card under an existing list
  Given List ID is stored from previous response
  And Add Card Payload with Card name "First Card"
  When User call "CREATE_CARD_API" by using "post" request
  Then API call is successfully with 200 status code
  And Verify Card details with name "First Card" and associated List ID
  
  
Scenario: Update the card name
  Given Card ID is stored from previous response
  And Update Card Payload with Card name "First Card Updated"
  When User call "UPDATE_CARD_API" by using "put" request
  Then API call is successfully with 200 status code
  And Verify Card details with name "First Card Updated" and associated List ID
  
  
Scenario: Delete a card
  Given Card ID is stored from previous response
  And Delete Card Payload is ready
  When User call "DELETE_CARD_API" by using "delete" request
  Then API call is successfully with 200 status code
  And Verify the card is deleted successfully