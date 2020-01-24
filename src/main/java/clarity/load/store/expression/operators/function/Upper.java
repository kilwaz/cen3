package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;

@OperatorRepresentation(stringRepresentation = "upper")
public class Upper extends Expression implements Operator {
    public Upper() {
        super(Expression.PRECEDENCE_UPPER, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Textual(a.getStringRepresentation().toUpperCase());
    }
}
