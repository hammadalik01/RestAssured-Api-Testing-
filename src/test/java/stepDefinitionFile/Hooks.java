package stepDefinitionFile;

import io.cucumber.java.AfterAll;
import static io.restassured.RestAssured.given;
import resourses.Utils;

public class Hooks extends Utils {

    @AfterAll
    public static void deleteBoards() throws Exception {
        Utils utils = new Utils();

        if (SharedContext.boardId != null) {
            given().spec(utils.requestSpecification())
                .delete("/boards/" + SharedContext.boardId)
                .then().statusCode(200);
            System.out.println("Deleted main board: " + SharedContext.boardId);
        }

        if (SharedContext.newBoardId != null && !SharedContext.newBoardId.equals(SharedContext.boardId)) {
            given().spec(utils.requestSpecification())
                .delete("/boards/" + SharedContext.newBoardId)
                .then().statusCode(200);
            System.out.println("Deleted new board: " + SharedContext.newBoardId);
        }
    }
}
