package clarity.definition;

import data.model.DatabaseObject;

public class RecordDefinitionChild extends DatabaseObject {

    private RecordDefinition recordDefinitionParent;
    private RecordDefinition recordDefinitionChild;

    private RecordDefinitionChild() {
    }

    public static RecordDefinitionChild define(RecordDefinition recordDefinitionParent, RecordDefinition recordDefinitionChild) {
        RecordDefinitionChild recordDefinitionChild1 = new RecordDefinitionChild();
        recordDefinitionChild1.setRecordDefinitionChild(recordDefinitionChild);
        recordDefinitionChild1.setRecordDefinitionParent(recordDefinitionParent);
        return recordDefinitionChild1;
    }

    private RecordDefinition getRecordDefinitionParent() {
        return recordDefinitionParent;
    }

    public void setRecordDefinitionParent(RecordDefinition recordDefinitionParent) {
        this.recordDefinitionParent = recordDefinitionParent;
    }

    private RecordDefinition getRecordDefinitionChild() {
        return recordDefinitionChild;
    }

    public void setRecordDefinitionChild(RecordDefinition recordDefinitionChild) {
        this.recordDefinitionChild = recordDefinitionChild;
    }
}
