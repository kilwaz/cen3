package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

public class Number extends Expression implements Value {
    private Double value;

    public Number() {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
    }

    public Number(Integer value) {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
        this.value = value.doubleValue();
    }

    public Number(Double value) {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getStringRepresentation() {
        return value.toString();
    }
}
