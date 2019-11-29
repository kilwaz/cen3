package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;

public class Divide extends Expression implements Operator {
    public Divide() {
        super(Expression.PRECEDENCE_DIVIDE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number((Double) ((Number) a).getValue() / (Double) ((Number) b).getValue());
    }

    public String getStringRepresentation() {
        return "/";
    }
}
