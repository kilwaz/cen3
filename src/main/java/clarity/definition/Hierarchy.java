package clarity.definition;

import clarity.Record;
import data.model.DatabaseObject;

public class Hierarchy extends DatabaseObject {

    private String nodeReference = "";
    private String parentReference = "";
    private HierarchyTree hierarchyTree;
    private Record employee;

    public Hierarchy() {
        super();
    }

    public Hierarchy(String nodeReference, String parentReference, HierarchyTree hierarchyTree) {
        this.nodeReference = nodeReference;
        this.parentReference = parentReference;
        this.hierarchyTree = hierarchyTree;
    }

    public String getNodeReference() {
        return nodeReference;
    }

    public void nodeReference(String nodeReference) {
        this.nodeReference = nodeReference;
    }

    public String getParentReference() {
        return parentReference;
    }

    public void parentReference(String parentReference) {
        this.parentReference = parentReference;
    }

    public HierarchyTree getHierarchyTree() {
        return hierarchyTree;
    }

    public String getHierarchyTreeUUID() {
        return hierarchyTree.getUuidString();
    }

    public void hierarchyTree(HierarchyTree hierarchyTree) {
        this.hierarchyTree = hierarchyTree;
    }

    public Record getEmployee() {
        return employee;
    }

    public String getEmployeeUUID() {
        if (employee == null) {
            return null;
        } else {
            return employee.getUuidString();
        }
    }

    public void employee(Record employee) {
        this.employee = employee;
    }
}
