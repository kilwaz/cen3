package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;

@OperatorRepresentation(stringRepresentation = "lower")
public class Lower extends Expression implements Operator {
    public Lower() {
        super(Expression.PRECEDENCE_LOWER, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Textual(a.getStringRepresentation().toLowerCase());
    }
}
