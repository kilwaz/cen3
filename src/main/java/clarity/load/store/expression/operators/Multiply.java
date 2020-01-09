package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;

public class Multiply extends Expression implements Operator {
    public Multiply() {
        super(Expression.PRECEDENCE_MULTIPLY, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number(((Number) a).getValue().multiply(((Number) b).getValue()));
    }

    public String getStringRepresentation() {
        return "*";
    }
}
