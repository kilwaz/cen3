package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;

public class Add extends Expression implements Operator {
    public Add() {
        super(Expression.PRECEDENCE_ADD);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number((Double) ((Number) a).getValue() + (Double) ((Number) b).getValue());
    }

    public String getStringRepresentation() {
        return "+";
    }
}
