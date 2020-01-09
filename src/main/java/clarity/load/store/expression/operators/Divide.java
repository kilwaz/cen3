package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;

import java.math.RoundingMode;

public class Divide extends Expression implements Operator {
    public Divide() {
        super(Expression.PRECEDENCE_DIVIDE, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number(((Number) a).getValue().divide(((Number) b).getValue(), RoundingMode.HALF_UP));
    }

    public String getStringRepresentation() {
        return "/";
    }
}
