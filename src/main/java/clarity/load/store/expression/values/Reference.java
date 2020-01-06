package clarity.load.store.expression.values;

import clarity.definition.Definition;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

public class Reference extends Expression implements Value {
    private Definition definition;

    public Reference() {
        super(Expression.PRECEDENCE_REFERENCE, Expression.NON_ASSOCIATIVE);
    }

    public Reference(Definition definition) {
        super(Expression.PRECEDENCE_REFERENCE, Expression.NON_ASSOCIATIVE);
        this.definition = definition;
    }

    public Expression getValue() {
        if (definition.isCalculated()) {

        } else {

        }

        return formula.solve();
    }

    public String getStringRepresentation() {
        return definition.getFormula().getStrExpression();
    }
}
