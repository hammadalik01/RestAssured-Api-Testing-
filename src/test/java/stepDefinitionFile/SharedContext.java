package stepDefinitionFile;

import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import pojo.Board;
import pojo.TrelloList;
import io.restassured.response.Response;
import org.junit.Assert;
import resourses.Utils;

public class SharedContext extends Utils {

    public static String boardId;
    public static String listId;
    public static String cardId;
    public static String labelId;
    public static String newBoardId;
    public static RequestSpecification request;
    public static Response response;
    public static TrelloList list;
    public static Board board;
    public static String actualListName;
	



    public static void prepareLabelPayload(String name, String color, boolean isCreate) throws Exception {
        if (isCreate && boardId == null)
            throw new RuntimeException("Board ID is null! Cannot create label.");

        if (!isCreate && labelId == null)
            throw new RuntimeException("Label ID is null! Cannot update label.");

        request = given()
                .spec(new SharedContext().requestSpecification())
                .queryParam("name", name)
                .queryParam("color", color);

        if (isCreate)
            request = request.queryParam("idBoard", boardId);

        System.out.println((isCreate ? "Prepared payload to create" : "Prepared payload to update") 
                           + " label: " + name + ", color: " + color);
    }

    public static void validateLabel(String expectedName, String expectedColor) {
        String responseBody = response.asString();
        String actualName = new SharedContext().getJsonValue(responseBody, "name");
        String actualColor = new SharedContext().getJsonValue(responseBody, "color");
        labelId = new SharedContext().getJsonValue(responseBody, "id");

        Assert.assertEquals("Label name mismatch!", expectedName, actualName);
        Assert.assertEquals("Label color mismatch!", expectedColor, actualColor);
        System.out.println("Label verified successfully: ID=" + labelId + ", Name=" + actualName + ", Color=" + actualColor);
    }
}
