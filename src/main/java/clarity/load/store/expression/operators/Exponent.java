package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;

public class Exponent extends Expression implements Operator {
    public Exponent() {
        super(Expression.PRECEDENCE_EXPONENT, Expression.RIGHT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number(((Number) a).getValue().pow(((Number) b).getValue().intValue()));
    }

    public String getStringRepresentation() {
        return "^";
    }
}
