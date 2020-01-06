package clarity.load.store.expression.values;

import clarity.Record;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

public class Text extends Expression implements Value {
    private String value;

    public Text() {
        super(Expression.PRECEDENCE_TEXT, Expression.NON_ASSOCIATIVE);
    }

    public Text(String value) {
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
