package clarity.load.data;

public class DoubleValue extends Value {
    private Double value;

    public DoubleValue(Double value, int column, int row) {
        super(Value.TYPE_DOUBLE, column,row);
        this.value = value;
    }

    public Double getValue() {
        return this.value;
    }
}
