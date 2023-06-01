package data.model;

public class DatabaseSort {
    private String definition;
    private String direction;

    public DatabaseSort(String definition, String direction) {
        this.definition = definition;
        this.direction = direction;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}