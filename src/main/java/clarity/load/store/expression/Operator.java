package clarity.load.store.expression;

public interface Operator {
    public Expression calculate(Expression a, Expression b);
}
