package clarity;

import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.definition.RecordDefinition;
import error.Error;

import java.util.HashMap;
import java.util.List;

public class Record {
    private RecordDefinition recordDefinition;
    private HashMap<String, Entry> entryHashMap = new HashMap<>();

    public Record(RecordDefinition recordDefinition) {
        this.recordDefinition = recordDefinition;
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
    }

    public Record set(Entry entry) {
        entryHashMap.put(entry.getReference(), entry);
        return this;
    }

    public Entry get(String reference) {
        return entryHashMap.get(reference);
    }
}
