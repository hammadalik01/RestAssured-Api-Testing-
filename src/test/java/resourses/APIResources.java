package resourses;

public enum APIResources {
    AddBoardAPI("/boards/"),
    GetBoardAPI("/boards/{id}"),
    UpdateBoardAPI("/boards/{id}"),
    DeleteBoardAPI("/boards/{id}"),
    CREATE_LIST_API("/lists"),
    GET_LIST_API("/lists/{id}"),
    UPDATE_LIST_API("/lists/{id}"),
    MOVE_LIST_TO_BOARD_API("/lists/{id}/idBoard"),
    CREATE_CARD_API("/cards"),
    UPDATE_CARD_API("/cards/{id}"),
    DELETE_CARD_API("/cards/{id}"),
    CREATE_LABEL_API("/labels"),
    UPDATE_LABEL_API("/labels/{id}"),
    GET_LABEL_API("/labels/{id}"),
    DELETE_LABEL_API("/labels/{id}");

    private String resource;
    
    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
