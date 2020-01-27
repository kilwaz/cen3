package clarity.load.store.expression;

public interface Operator {
    Expression calculate(Expression a, Expression b);
}
