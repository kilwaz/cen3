package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.Value;

public class Reference extends Expression implements Value {
    private Formula formula;

    public Reference() {
        super(Expression.PRECEDENCE_REFERENCE, Expression.NON_ASSOCIATIVE);
    }

    public Reference(Formula formula) {
        super(Expression.PRECEDENCE_REFERENCE, Expression.NON_ASSOCIATIVE);
        this.formula = formula;
    }

    public Object getValue() {
        return formula.solve();
    }

    public String getStringRepresentation() {
        return formula.getStrExpression();
    }
}
