package clarity.load.store.expression.operators.grouping;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;

@OperatorRepresentation(formulaRepresentation = ",")
public class Comma extends Expression implements Operator {
    public Comma() {
        super(Expression.PRECEDENCE_COMMA, Expression.RIGHT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number(0);
    }
}
