package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;

import java.math.RoundingMode;
import java.util.ArrayList;

@OperatorRepresentation(formulaRepresentation = "round")
@FunctionParameters(parameterCount = 2)
public class Round extends Expression implements Function {
    public Round() {
        super(Expression.PRECEDENCE_FUNCTION, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {
            return new Number(parameters.get(0).getNumericRepresentation().setScale(parameters.get(1).getNumericRepresentation().intValue(), RoundingMode.HALF_UP));
        }

        return null;
    }
}
