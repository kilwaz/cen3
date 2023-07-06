package clarity.definition;

import data.model.DatabaseObject;

public class FormulaContext extends DatabaseObject {
    private String name = "";

    public FormulaContext() {
        super();
    }

    public String getName() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }
}