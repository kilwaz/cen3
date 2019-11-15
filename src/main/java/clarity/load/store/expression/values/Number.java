package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

public class Number extends Expression implements Value {
    private Double value;

    public Number() {
        super(1);
    }

    public Number(Integer value) {
        super(1);
        this.value = value.doubleValue();
    }

    public Number(double value) {
        super(1);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getStringRepresentation() {
        return value.toString();
    }
}
