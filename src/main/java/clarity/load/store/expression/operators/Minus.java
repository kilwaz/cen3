package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;
import org.apache.commons.math3.analysis.function.Exp;

public class Minus extends Expression implements Operator {
    public Minus() {
        super(Expression.PRECEDENCE_MINUS);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Number((Double) ((Number) a).getValue() - (Double) ((Number) b).getValue());
    }

    public String getStringRepresentation() {
        return "-";
    }
}
