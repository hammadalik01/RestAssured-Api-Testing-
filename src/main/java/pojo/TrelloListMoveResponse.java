package pojo;

public class TrelloListMoveResponse {
    private String id;
    private String name;
    private boolean closed;
    private String color;
    private String idBoard;
    private long pos;
    private String type;
    private Datasource datasource;

    // Inner class for datasource object
    public static class Datasource {
        private boolean filter;

        public boolean isFilter() {
            return filter;
        }

        public void setFilter(boolean filter) {
            this.filter = filter;
        }
    }

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isClosed() {
        return closed;
    }
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getIdBoard() {
        return idBoard;
    }
    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

    public long getPos() {
        return pos;
    }
    public void setPos(long pos) {
        this.pos = pos;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Datasource getDatasource() {
        return datasource;
    }
    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }
}
