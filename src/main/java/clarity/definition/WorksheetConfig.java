package clarity.definition;

import data.model.DatabaseObject;

public class WorksheetConfig extends DatabaseObject {

    private String name = "";

    public WorksheetConfig() {
        super();
    }

    public String getName() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }
}
