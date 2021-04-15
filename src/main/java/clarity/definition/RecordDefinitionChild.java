package clarity.definition;

import data.model.DatabaseObject;

import java.util.UUID;

public class RecordDefinitionChild extends DatabaseObject {

    private RecordDefinition recordDefinitionParent;
    private RecordDefinition recordDefinitionChild;

    public RecordDefinitionChild() {
    }

    public static RecordDefinitionChild define(RecordDefinition recordDefinitionParent, RecordDefinition recordDefinitionChild) {
        RecordDefinitionChild recordDefinitionChild1 = RecordDefinitionChild.create(RecordDefinitionChild.class);
        recordDefinitionChild1.setRecordDefinitionChild(recordDefinitionChild);
        recordDefinitionChild1.setRecordDefinitionParent(recordDefinitionParent);
        recordDefinitionChild1.save();
        return recordDefinitionChild1;
    }

    public RecordDefinition getRecordDefinitionParent() {
        return recordDefinitionParent;
    }

    public UUID getRecordDefinitionParentUUID() {
        return recordDefinitionParent.getUuid();
    }

    public void setRecordDefinitionParent(RecordDefinition recordDefinitionParent) {
        this.recordDefinitionParent = recordDefinitionParent;
    }

    public RecordDefinition getRecordDefinitionChild() {
        return recordDefinitionChild;
    }

    public UUID getRecordDefinitionChildUUID() {
        return recordDefinitionChild.getUuid();
    }

    public void setRecordDefinitionChild(RecordDefinition recordDefinitionChild) {
        this.recordDefinitionChild = recordDefinitionChild;
    }
}
