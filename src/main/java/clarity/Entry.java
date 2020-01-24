package clarity;

import clarity.definition.Definition;
import clarity.definition.Definitions;

import java.math.BigDecimal;

public class Entry {
    private Definition definition;
    private EntryValue entryValue;
    private Boolean isFresh = false;
    private Record record;

    public static Entry create(String reference, String value) {
        return new Entry(null, reference, new BigDecimal(value));
    }

    public Entry(Record record, String reference, Object value) {
        this.definition = Definitions.getInstance().findDefinition(reference);
        this.entryValue = new EntryValue(value);
        this.record = record;
        infer();
    }

    public Entry(Record record, Definition definition) {
        this.definition = definition;
        this.entryValue = new EntryValue();
        this.record = record;
        infer();
    }

    private void infer() {
        if (definition != null && definition.isCalculated()) {
            Infer.me(this);
        } else {
            isFresh = true;
        }
    }

    public void setStale() {
        isFresh = false;
    }

    public void setFresh() {
        isFresh = true;
    }

    public Entry record(Record record) {
        this.record = record;
        infer();
        return this;
    }

    public Entry set(EntryValue entryValue) {
        this.entryValue = entryValue;
        infer();
        return this;
    }

    public EntryValue get() {
        return entryValue;
    }

    public String getReference() {
        return definition.getName();
    }

    public Boolean isFresh() {
        return isFresh;
    }

    public Definition getDefinition() {
        return definition;
    }

    public Record getRecord() {
        return record;
    }
}
