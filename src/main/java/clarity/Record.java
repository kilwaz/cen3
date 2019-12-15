package clarity;

import clarity.definition.Definition;
import clarity.definition.RecordDefinition;

public class Record {
    private RecordDefinition recordDefinition;

    public Record(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
    }

    public Record set(Definition definition) {

        return this;
    }

    public Object get() {
        return "";
    }
}
