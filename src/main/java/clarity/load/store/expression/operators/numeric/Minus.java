package clarity.load.store.expression.operators.numeric;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;

@OperatorRepresentation(stringRepresentation = "-")
public class Minus extends Expression implements Operator {
    public Minus() {
        super(Expression.PRECEDENCE_MINUS, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number(((Number) a).getValue().subtract(((Number) b).getValue()));
    }
}
