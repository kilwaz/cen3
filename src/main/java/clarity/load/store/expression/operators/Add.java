package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;

public class Add extends Expression implements Operator {
    public Add() {
        super(Expression.PRECEDENCE_ADD, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number(((Number) a).getValue().add(((Number) b).getValue()));
    }

    public String getStringRepresentation() {
        return "+";
    }
}
