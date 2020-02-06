package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Evaluation;

import java.util.ArrayList;

@OperatorRepresentation(formulaRepresentation = "if")
@FunctionParameters(parameterCount = 3)
public class If extends Expression implements Function {
    public If() {
        super(Expression.PRECEDENCE_LOGIC, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {
            if (parameters.get(0) instanceof Evaluation) {
                if (((Evaluation) parameters.get(0)).getValue()) {
                    return parameters.get(1);
                } else {
                    return parameters.get(2);
                }
            }
        }

        return null;
    }
}
