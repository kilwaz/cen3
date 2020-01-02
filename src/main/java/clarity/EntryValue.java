package clarity;

public class EntryValue {
    private Object value;

    public EntryValue() {

    }

    public EntryValue(Object value) {
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }
}
