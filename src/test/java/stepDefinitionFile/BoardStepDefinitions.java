package stepDefinitionFile;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pojo.Board;
import static io.restassured.RestAssured.given;
import org.junit.Assert;
import resourses.Utils;
import static stepDefinitionFile.SharedContext.*;

import java.io.FileNotFoundException;

public class BoardStepDefinitions extends Utils {

    @Given("Add Board Payload with Board name {string}")
    public void add_board_payload(String name) throws Exception {
        if (boardId == null || boardId.isEmpty()) {
        	
            request = given().spec(requestSpecification()).queryParam("name", name);
            System.out.println("Creating new board payload: " + name);
        } else if (board != null && !board.getName().equals(name)) {
   
            request = given().spec(requestSpecification()).queryParam("name", name);
            System.out.println("Board exists with different name. Preparing update payload: " + name);
        } else {
            System.out.println("Board already exists with same name: " + name + ". Skipping creation payload.");
        }
    }

    @Given("Update Board Payload with Board name {string}")
    public void update_board_payload(String updatedName) throws Exception {
        request = given().spec(requestSpecification()).queryParam("name", updatedName);
        System.out.println("Prepared payload to update Board name to: " + updatedName);
    }

    @Then("Verify all important details of the created Board with name {string} and URL containing {string}")
    public void verify_created_board(String expectedName, String expectedUrl) {
        board = response.as(Board.class);

        Assert.assertEquals("Board name mismatch!", expectedName, board.getName());
        Assert.assertNotNull("Board ID is null!", board.getId());
        Assert.assertFalse("Board should not be closed!", board.isClosed());
        Assert.assertTrue("Board URL does not contain expected value!", board.getUrl().contains(expectedUrl));
        Assert.assertEquals("Board permission level mismatch!", "private", board.getPrefs().getPermissionLevel());

        boardId = board.getId();
        System.out.println("Board verified successfully: ID=" + boardId + ", Name=" + expectedName);
    }

    @Then("Verify all important details of the updated Board with name {string}")
    public void verify_updated_board(String expectedUpdatedName) {
        board = response.as(Board.class);
        Assert.assertEquals(expectedUpdatedName, board.getName());
        Assert.assertNotNull(board.getId());
        System.out.println("Updated Board verified: Name=" + expectedUpdatedName);
    }

    @Then("Verify Board name is updated to {string}")
    public void verify_board_name_updated(String expectedUpdatedName) {
        board = response.as(Board.class);
        Assert.assertEquals(expectedUpdatedName, board.getName());
        boardId = board.getId();
        System.out.println("Board Name Updated: " + expectedUpdatedName);
    }
    
    @Then("Verify the board is deleted successfully by checking it no longer exists when fetched again")
    public void verify_board_deleted_successfully() throws Exception {
        if (SharedContext.boardId == null)
            throw new RuntimeException("No Board ID found to verify deletion!");

        Response response = given()
                .spec(requestSpecification())
                .when()
                .get("/boards/" + SharedContext.boardId);

        Assert.assertEquals("Expected board to be deleted but found!", 404, response.getStatusCode());
        System.out.println("Verified: Board has been deleted successfully.");
    }
}
