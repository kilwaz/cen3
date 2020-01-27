package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

public class Textual extends Expression implements Value {
    private String value;

    public Textual() {
        super(Expression.PRECEDENCE_TEXT, Expression.NON_ASSOCIATIVE);
    }

    public Textual(String value) {
        super(Expression.PRECEDENCE_TEXT, Expression.NON_ASSOCIATIVE);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getStringRepresentation() {
        return value;
    }
}
