package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

public class ReferenceView extends Expression implements Value {
    private String reference;

    public ReferenceView() {
        super(Expression.PRECEDENCE_REFERENCE, Expression.NON_ASSOCIATIVE);
    }

    public ReferenceView(String reference) {
        super(Expression.PRECEDENCE_REFERENCE, Expression.NON_ASSOCIATIVE);
        this.reference = reference;
    }

    public String getValue() {
        return reference;
    }

    public String getStringRepresentation() {
        return reference;
    }
}
