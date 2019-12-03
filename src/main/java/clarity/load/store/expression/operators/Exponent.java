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
        return new Number(Math.pow((Double) ((Number) a).getValue(), (Double) ((Number) b).getValue()));
    }

    public String getStringRepresentation() {
        return "^";
    }
}
