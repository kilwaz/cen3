package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

public class Bool extends Expression implements Value {
    private Boolean value;

    public Bool() {
        super(Expression.PRECEDENCE_BOOLEAN, Expression.NON_ASSOCIATIVE);
    }

    public Bool(Boolean value) {
        super(Expression.PRECEDENCE_BOOLEAN, Expression.NON_ASSOCIATIVE);
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public String getStringRepresentation() {
        return value.toString();
    }
}
