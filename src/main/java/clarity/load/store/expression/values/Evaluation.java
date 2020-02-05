package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

public class Evaluation extends Expression implements Value {
    private Boolean value;

    public Evaluation() {
        super(Expression.PRECEDENCE_EVALUATION, Expression.NON_ASSOCIATIVE);
    }

    public Evaluation(Boolean value) {
        super(Expression.PRECEDENCE_EVALUATION, Expression.NON_ASSOCIATIVE);
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public String getStringRepresentation() {
        return value.toString();
    }
}
