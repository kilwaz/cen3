package clarity.definition;

import data.model.DatabaseObject;

public class HierarchyTree extends DatabaseObject {

    private String name = "";

    public HierarchyTree() {
        super();
    }

    public HierarchyTree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }
}
