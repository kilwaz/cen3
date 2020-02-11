package clarity;

import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.definition.RecordDefinition;
import clarity.load.store.Records;
import error.Error;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Record {
    private UUID uuid = UUID.randomUUID();

    private RecordDefinition recordDefinition;
    private HashMap<String, Entry> entryHashMap = new HashMap<>();

    public Record(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
        Records.getInstance().addRecord(this);
    }

    public Record(String reference) {
        this.recordDefinition = Definitions.getInstance().findRecordDefinition(reference);

        if (recordDefinition != null) {
            List<Definition> definitions = recordDefinition.getDefinitions();

            for (Definition definition : definitions) {
                if (definition.isCalculated()) {
                    set(new Entry(this, definition));
                }
            }
        } else {
            Error.CLARITY_REFERENCE_NOT_FOUND.record()
                    .additionalInformation("Reference name = " + reference)
                    .create();
        }
        Records.getInstance().addRecord(this);
    }

    public Record set(Entry entry) {
        entry.record(this);
        entryHashMap.put(entry.getReference().toLowerCase(), entry);
        return this;
    }

    public Entry get(String reference) {
        return entryHashMap.get(reference.toLowerCase());
    }

    public UUID getUuid() {
        return uuid;
    }
}
