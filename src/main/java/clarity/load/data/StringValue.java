package clarity.load.data;

public class StringValue extends Value {
    private String value;

    public StringValue(String value, int column, int row, String columnName) {
        super(Value.TYPE_STRING, column, row, columnName);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
