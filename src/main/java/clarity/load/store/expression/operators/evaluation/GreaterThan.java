package clarity.load.store.expression.operators.evaluation;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Evaluation;

@OperatorRepresentation(formulaRepresentation = ">")
public class GreaterThan extends Expression implements Operator {
    public GreaterThan() {
        super(Expression.PRECEDENCE_EVALUATION, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression calculate(Expression a, Expression b) {
        return new Evaluation(a.compareTo(b) > 0);
    }
}
