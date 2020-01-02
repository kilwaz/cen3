package clarity;

import clarity.definition.Definition;

public class Entry {
    private Definition definition;
    private EntryValue entryValue;

    public Entry(Definition definition) {
        this.definition = definition;
        this.entryValue = new EntryValue();
    }

    public void set(EntryValue entryValue) {
        this.entryValue = entryValue;
    }

    public EntryValue get() {
        return entryValue;
    }
}
