package clarity.load.store.expression;

public interface Function {
    Expression apply(Expression... parameters);
}
