package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;

public class CloseBracket extends Expression implements Operator {
    public CloseBracket() {
        super(Expression.PRECEDENCE_CLOSE_BRACKET, Expression.RIGHT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number(0);
    }

    public String getStringRepresentation() {
        return ")";
    }
}
