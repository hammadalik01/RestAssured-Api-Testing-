package stepDefinitionFile;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import resourses.APIResources;
import resourses.Utils;
import java.io.FileNotFoundException;
import static stepDefinitionFile.SharedContext.*;
import static io.restassured.RestAssured.given;

public class CommonSteps extends Utils {
	
    @Given("Board ID is stored from previous response")
    public void board_id_stored() throws Exception {
        if (SharedContext.boardId == null) {
            throw new RuntimeException("Board ID is null! Cannot fetch board.");
        }
        SharedContext.request = given().spec(requestSpecification());
        System.out.println("Using stored Board ID: " + SharedContext.boardId);
    }
    
    @When("User call {string} by using {string} request")
    public void user_call_api(String apiName, String method) throws FileNotFoundException {
        System.out.println("Calling API: " + apiName + " using method: " + method);

        APIResources resourceAPI = APIResources.valueOf(apiName);
        String endpoint = resourceAPI.getResource();
        
   
        if (endpoint.contains("{id}")) {

            // --- List related APIs ---
            if (apiName.equalsIgnoreCase("GET_LIST_API") || 
                apiName.equalsIgnoreCase("UPDATE_LIST_API") || 
                apiName.equalsIgnoreCase("DELETE_LIST_API")) {

                if (SharedContext.listId == null) 
                    throw new RuntimeException("List ID is null! Cannot call " + apiName);
                endpoint = endpoint.replace("{id}", SharedContext.listId);

            } 
         
            else if (apiName.equalsIgnoreCase("GetBoardAPI") || 
                     apiName.equalsIgnoreCase("UpdateBoardAPI") || 
                     apiName.equalsIgnoreCase("DeleteBoardAPI")) {

                if (SharedContext.boardId == null) 
                    throw new RuntimeException("Board ID is null! Cannot call " + apiName);
                endpoint = endpoint.replace("{id}", SharedContext.boardId);

            } 
            
            else if (apiName.equalsIgnoreCase("MOVE_LIST_TO_BOARD_API")) {

                if (SharedContext.listId == null) 
                    throw new RuntimeException("List ID is null! Cannot move list.");
                endpoint = endpoint.replace("{id}", SharedContext.listId);
                SharedContext.request = SharedContext.request.queryParam("value", SharedContext.newBoardId);

            } 
          
            else if (apiName.equalsIgnoreCase("UPDATE_CARD_API") || 
                     apiName.equalsIgnoreCase("DELETE_CARD_API")) {

                if (SharedContext.cardId == null) 
                    throw new RuntimeException("Card ID is null! Cannot call " + apiName);
                endpoint = endpoint.replace("{id}", SharedContext.cardId);

            } 
           
            else if (apiName.equalsIgnoreCase("UPDATE_LABEL_API") || 
                     apiName.equalsIgnoreCase("GET_LABEL_API") || 
                     apiName.equalsIgnoreCase("DELETE_LABEL_API")) {

                if (SharedContext.labelId == null) 
                    throw new RuntimeException("Label ID is null! Cannot call " + apiName);
                endpoint = endpoint.replace("{id}", SharedContext.labelId);

            }
            else {
                throw new RuntimeException("API name does not require ID replacement or is invalid: " + apiName);
            }
        }
         
        method = method.toUpperCase();

        if ("POST".equals(method)) {
            SharedContext.response = SharedContext.request.when().post(endpoint);
        } else if ("GET".equals(method)) {
            SharedContext.response = SharedContext.request.when().get(endpoint);
        } else if ("PUT".equals(method)) {
            SharedContext.response = SharedContext.request.when().put(endpoint);
        } else if ("DELETE".equals(method)) {
            SharedContext.response = SharedContext.request.when().delete(endpoint);
        } else {
            throw new RuntimeException("Unsupported HTTP method: " + method);
        }

        System.out.println("API call completed. Endpoint: " + endpoint + ", Status: " + SharedContext.response.getStatusCode());
    }

    @Then("API call is successfully with 200 status code")
    public void api_call_is_successfully_with_200_status_code() {
        if (response == null) throw new RuntimeException("Response is null!");
        response.then().spec(responseSpecification());
        Assert.assertEquals(200, response.getStatusCode());
    }
    
}
