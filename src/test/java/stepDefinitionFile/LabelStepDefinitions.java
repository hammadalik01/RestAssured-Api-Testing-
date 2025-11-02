package stepDefinitionFile;

import io.cucumber.java.en.*;
import org.junit.Assert;
import resourses.Utils;

public class LabelStepDefinitions extends Utils {

   
    @Given("Add Label Payload with name {string} and color {string}")
    public void add_label_payload(String name, String color) throws Exception {
        SharedContext.prepareLabelPayload(name, color, true);
    }

    @Given("Add Label Payload with Label name {string} and color {string}")
    public void add_label_payload_with_label_name_and_color(String name, String color) throws Exception {
        SharedContext.prepareLabelPayload(name, color, true);
    }

    
    @Given("Update Label Payload with name {string} and color {string}")
    public void update_label_payload(String name, String color) throws Exception {
        SharedContext.prepareLabelPayload(name, color, false);
    }

    
    @Given("Label ID is stored from previous response")
    public void label_id_stored() {
        if (SharedContext.labelId == null)
            throw new RuntimeException("Label ID is null! Create a label first.");
        System.out.println("Using Label ID: " + SharedContext.labelId);
    }

  
    @Then("Verify Label details with name {string} and color {string}")
    public void verify_label_details(String expectedName, String expectedColor) {
        SharedContext.validateLabel(expectedName, expectedColor);
    }

    @Then("Verify Label details with name {string}, color {string} and associated Board ID")
    public void verify_label_details_with_name_color_and_associated_board_id(String expectedName, String expectedColor) {
        SharedContext.validateLabel(expectedName, expectedColor);
    }

   
    @Then("Verify Label is deleted successfully")
    public void verify_label_is_deleted_successfully() {
        int statusCode = SharedContext.response.getStatusCode();
        Assert.assertEquals("Label deletion failed!", 200, statusCode);

        System.out.println("Label deleted successfully. ID: " + SharedContext.labelId);
        SharedContext.labelId = null; 
    }
}
