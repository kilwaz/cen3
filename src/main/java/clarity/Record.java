package clarity;

import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.definition.RecordDefinition;
import clarity.load.store.Records;
import data.model.ConfigurableDatabaseObject;
import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Record extends ConfigurableDatabaseObject {
    private static Logger log = AppLogger.logger();

    private UUID uuid = UUID.randomUUID();

    private RecordDefinition recordDefinition;
    private HashMap<String, Entry> entryHashMap = new HashMap<>();
    private HashMap<RecordDefinition, List<Record>> children = new HashMap<>();

    public Record(RecordDefinition recordDefinition) {
        super();
        this.recordDefinition = recordDefinition;
    }

    public static Record load(RecordDefinition recordDefinition, UUID uuid) {
        Record record = new Record(recordDefinition);
        record.setRecordDefinition(recordDefinition);
        record.setUuid(uuid);

        List<Definition> definitions = recordDefinition.getDefinitions();
        for (Definition definition : definitions) {
            record.set(new Entry(record, definition));
        }

        return record;
    }

    public static Record create(String reference) {
        return new Record(reference);
    }

    private Record(String reference) {
        super();
        this.recordDefinition = Definitions.getInstance().findRecordDefinition(reference);
        super.setRecordDefinition(this.recordDefinition);

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

    public Record addChild(Record childRecord) {
        RecordDefinition childRecordDefinition = childRecord.getRecordDefinition();

        if (!this.recordDefinition.getChildRecordDefinitions().containsKey(childRecordDefinition.getName())) {
            log.info("CLARITY WARNING: Tired to add child '" + childRecordDefinition.getName() + "' to record '" + this.recordDefinition.getName() + "' which is not configured to support that child");
            return this;
        }

        List<Record> records;
        if (children.containsKey(childRecordDefinition)) {
            records = children.get(childRecordDefinition);
        } else {
            records = new ArrayList<>();
        }

        records.add(childRecord);
        children.put(childRecordDefinition, records);

        return this;
    }

    public List<Record> getChildren(RecordDefinition recordDefinition) {
        if (children.containsKey(recordDefinition)) {
            return children.get(recordDefinition);
        } else {
            return new ArrayList<>();
        }
    }

    public Record set(List<Entry> entries) {
        for (Entry entry : entries) {
            set(entry);
        }
        return this;
    }

    public Record set(Entry entry) {
        if (recordDefinition.hasDefinition(entry.getDefinition())) {
            entry.record(this);
            entryHashMap.put(entry.getReference().toLowerCase(), entry);
        } else {
            log.info("CLARITY WARNING: Tired to add definition '" + entry.getDefinition().getName() + "' to record '" + recordDefinition.getName() + "' which is not configured to support that entry");
        }

        return this;
    }

    public List<Entry> get(List<String> references) {
        List<Entry> entries = new ArrayList<>();

        for (String reference : references) {
            Entry entry = entryHashMap.get(reference.toLowerCase());
            if (entry != null) {
                entries.add(entry);
            }
        }

        return entries;
    }

    public Entry get(String reference) {
        return entryHashMap.get(reference.toLowerCase());
    }

    public Entry get(Definition definition) {
        return entryHashMap.get(definition.getName().toLowerCase());
    }

    public HashMap<RecordDefinition, List<Record>> getChildren() {
        return children;
    }

    public RecordDefinition getRecordDefinition() {
        return recordDefinition;
    }

    public UUID getUuid() {
        return uuid;
    }
}
