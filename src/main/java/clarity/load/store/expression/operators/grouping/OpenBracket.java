package clarity.load.store.expression.operators.grouping;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;

@OperatorRepresentation(stringRepresentation = "(")
public class OpenBracket extends Expression implements Operator {
    public OpenBracket() {
        super(Expression.PRECEDENCE_OPEN_BRACKET, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number(0);
    }
}
