package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;

public class OpenBracket extends Expression implements Operator {
    public OpenBracket() {
        super(Expression.PRECEDENCE_OPEN_BRACKET, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number(0);
    }

    public String getStringRepresentation() {
        return "(";
    }
}
