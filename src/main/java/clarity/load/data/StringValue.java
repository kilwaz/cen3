package clarity.load.data;

public class StringValue extends Value {
    private String value;

    public StringValue(String value, int column, int row) {
        super(Value.TYPE_STRING, column, row);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
