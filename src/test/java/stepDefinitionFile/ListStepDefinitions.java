package stepDefinitionFile;

import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pojo.TrelloList;
import pojo.TrelloListMoveResponse;
import resourses.Utils;
import static stepDefinitionFile.SharedContext.*;



public class ListStepDefinitions extends Utils {

    @Given("Add List Payload with List name {string}")
    public void add_list_payload(String listName) throws Exception {
        if (SharedContext.boardId == null) {
            throw new RuntimeException("Board ID is null! Please create a board first.");
        }
        request = given().spec(requestSpecification())
                        .queryParam("name", listName)
                        .queryParam("idBoard", SharedContext.boardId);
        System.out.println("Prepared List payload for board ID: " + SharedContext.boardId);
    }

    
    @Then("Verify List details with name {string} and associated Board ID")
    public void verify_list_details(String expectedListName) {
        SharedContext.list = response.as(TrelloList.class);
        SharedContext.listId = SharedContext.list.getId();
        SharedContext.actualListName = SharedContext.list.getName();

        Assert.assertNotNull("List ID is null!", SharedContext.listId);
        Assert.assertEquals("List name mismatch!", expectedListName, SharedContext.list.getName());
        Assert.assertFalse("List should not be closed!", SharedContext.list.isClosed());
        Assert.assertNull("List color should be null", SharedContext.list.getColor());
        Assert.assertEquals("Board ID mismatch!", SharedContext.boardId, SharedContext.list.getIdBoard());

        System.out.println("Verified List: " + expectedListName + ", List ID: " + SharedContext.listId);
    }
    

    @Given("List ID is stored from previous response")
    public void list_id_is_stored_from_previous_response() throws Exception {
        if (SharedContext.listId == null) {
            System.out.println("No existing list found creating a temporary list");

            if (SharedContext.boardId == null) {
                //Auto-create temporary board
                SharedContext.response = given()
                        .spec(requestSpecification())
                        .queryParam("name", "Auto_Board_For_List_Test")
                        .when().post("/boards/").then().extract().response();

                Assert.assertEquals(200, SharedContext.response.getStatusCode());
                SharedContext.boardId = SharedContext.response.jsonPath().getString("id");
                System.out.println("Created temporary board: " + SharedContext.boardId);
            }

            //Create temporary list
            SharedContext.response = given()
                    .spec(requestSpecification())
                    .queryParam("name", "Auto_List_For_Test")
                    .queryParam("idBoard", SharedContext.boardId)
                    .when().post("/lists")
                    .then().extract().response();

            Assert.assertEquals(200, SharedContext.response.getStatusCode());
            SharedContext.listId = SharedContext.response.jsonPath().getString("id");
            System.out.println("Created temporary list: " + SharedContext.listId);
        } else {
            System.out.println("Using existing list ID: " + SharedContext.listId);
        }

        SharedContext.request = given().spec(requestSpecification());
    }
    

    @Given("Update List Payload with List name {string}")
    public void update_list_payload(String updatedListName) throws Exception {
        if (SharedContext.listId == null) {
            throw new RuntimeException("List ID is null! Cannot update.");
        }
        SharedContext.request = given().spec(requestSpecification())
                                      .queryParam("name", updatedListName);
        System.out.println("Prepared payload to update list ID: " + SharedContext.listId + " with new name: " + updatedListName);
    }
    

    @Then("Verify List details are updated with name {string} and associated Board ID")
    public void verify_updated_list_details(String expectedUpdatedListName) {
        SharedContext.list = response.as(TrelloList.class);

        Assert.assertEquals("List ID should remain same", SharedContext.listId, SharedContext.list.getId());
        Assert.assertEquals("List name did not update correctly", expectedUpdatedListName, SharedContext.list.getName());
        Assert.assertEquals("Board ID mismatch", SharedContext.boardId, SharedContext.list.getIdBoard());

        System.out.println("Updated List Verified: " + expectedUpdatedListName);
    }
    

    @Then("Verify datasource filter is false")
    public void verify_datasource_filter_is_false() {
        Assert.assertFalse("Expected datasource filter to be false", SharedContext.list.getDatasource().isFilter());
        System.out.println("Verified datasource filter is false for List ID: " + SharedContext.list.getId());
    }
    

    @Given("A new board is created for moving list with name {string}")
    public void create_new_board_for_move(String newBoardName) throws Exception {
        SharedContext.response = given()
                .spec(requestSpecification())
                .queryParam("name", newBoardName)
                .when().post("/boards/").then().extract().response();

        Assert.assertEquals(200, SharedContext.response.getStatusCode());
        SharedContext.newBoardId = SharedContext.response.jsonPath().getString("id");
        System.out.println("Created new board for moving list. ID: " + SharedContext.newBoardId);
    }

    @Given("Store its Board ID as newBoardId")
    public void store_new_board_id() {
        Assert.assertNotNull("New Board ID is null!", SharedContext.newBoardId);
        System.out.println("Stored newBoardId: " + SharedContext.newBoardId);
    }

    @Then("Verify the list has been moved to new board successfully")
    public void verify_list_moved_to_new_board() {
        TrelloListMoveResponse movedList = response.as(TrelloListMoveResponse.class);

        Assert.assertEquals("List ID mismatch after move!", SharedContext.listId, movedList.getId());
        Assert.assertEquals("List not moved to correct board!", SharedContext.newBoardId, movedList.getIdBoard());
        Assert.assertEquals("List name changed unexpectedly!", SharedContext.actualListName, movedList.getName());
        Assert.assertFalse("List should not be closed after move!", movedList.isClosed());
        Assert.assertFalse("Datasource filter should be false!", movedList.getDatasource().isFilter());

        System.out.println("List successfully moved to new board: " + SharedContext.newBoardId);
    }
}
