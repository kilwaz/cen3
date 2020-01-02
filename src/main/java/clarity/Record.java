package clarity;

import clarity.definition.RecordDefinition;

import java.util.HashMap;

public class Record {
    private RecordDefinition recordDefinition;
    private HashMap<String, Entry> entryHashMap = new HashMap<>();

    public Record(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
    }

    public Record set(Entry entry) {
        return this;
    }

    public Object get() {
        return "";
    }
}
