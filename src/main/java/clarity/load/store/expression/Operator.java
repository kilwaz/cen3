package clarity.load.store.expression;

import clarity.load.store.expression.values.Number;

public interface Operator {
    public Expression calculate(Expression a, Expression b);
}
