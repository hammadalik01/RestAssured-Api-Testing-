package stepDefinitionFile;

import static io.restassured.RestAssured.given;
import io.cucumber.java.en.*;
import org.junit.Assert;
import resourses.Utils;

public class CardStepDefinitions extends Utils {

    @Given("Add Card Payload with Card name {string}")
    public void add_card_payload(String cardName) throws Exception {
        if (SharedContext.listId == null)
            throw new RuntimeException("List ID is null. Please create a list first.");

        SharedContext.request = given()
                .spec(requestSpecification())
                .queryParam("idList", SharedContext.listId)
                .queryParam("name", cardName);

        System.out.println("Prepared card payload: " + cardName);
    }

    @Then("Verify Card details with name {string} and associated List ID")
    public void verify_card_details(String expectedCardName) {
        String responseBody = SharedContext.response.asString();
        String actualName = getJsonValue(responseBody, "name");
        String actualListId = getJsonValue(responseBody, "idList");
        SharedContext.cardId = getJsonValue(responseBody, "id");

        Assert.assertEquals(expectedCardName, actualName);
        Assert.assertEquals(SharedContext.listId, actualListId);
        System.out.println("Card verified successfully: " + actualName);
    }

    @Given("Card ID is stored from previous response")
    public void card_id_stored() {
        if (SharedContext.cardId == null)
            throw new RuntimeException("Card ID is null. Create a card first.");
        System.out.println("Using Card ID: " + SharedContext.cardId);
    }

    @Given("Update Card Payload with Card name {string}")
    public void update_card_payload(String updatedCardName) throws Exception {
        if (SharedContext.cardId == null)
            throw new RuntimeException("Card ID is null! Cannot update.");

        SharedContext.request = given()
                .spec(requestSpecification())
                .queryParam("name", updatedCardName);

        System.out.println("Prepared payload to update card name to: " + updatedCardName);
    }

    @Given("Delete Card Payload is ready")
    public void delete_card_payload_is_ready() throws Exception {
        if (SharedContext.cardId == null)
            throw new RuntimeException("Card ID is null! Cannot delete card.");
        SharedContext.request = given().spec(requestSpecification());
        System.out.println("Prepared payload for deleting card ID: " + SharedContext.cardId);
    }

    @Then("Verify the card is deleted successfully")
    public void verify_card_deleted() {
        int statusCode = SharedContext.response.getStatusCode();
        Assert.assertEquals(200, statusCode); // Corrected from 200 to 404
        System.out.println("Card deleted successfully. Status: " + statusCode);
    }
    
}
