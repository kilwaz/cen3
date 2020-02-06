package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;

import java.util.ArrayList;

@OperatorRepresentation(formulaRepresentation = "upper")
@FunctionParameters(parameterCount = 1)
public class Upper extends Expression implements Function {
    public Upper() {
        super(Expression.PRECEDENCE_FUNCTION, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {
            return new Textual(parameters.get(0).getStringRepresentation().toUpperCase());
        }

        return null;
    }
}
