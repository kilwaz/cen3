package clarity.load.data;

public class DoubleValue extends Value {
    private Double value;

    public DoubleValue(Double value, int column, int row, String columnName) {
        super(Value.TYPE_DOUBLE, column, row, columnName);
        this.value = value;
    }

    public Double getValue() {
        return this.value;
    }
}
