package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

public class FormulaNode extends Expression implements Value {
    private String expression;

    public FormulaNode() {
        super(Expression.PRECEDENCE_REFERENCE, Expression.NON_ASSOCIATIVE);
    }

    public FormulaNode(String expression) {
        super(Expression.PRECEDENCE_REFERENCE, Expression.NON_ASSOCIATIVE);
        this.expression = expression;
    }

    public String getValue() {
        return expression;
    }

    public String getStringRepresentation() {
        return expression;
    }
}
