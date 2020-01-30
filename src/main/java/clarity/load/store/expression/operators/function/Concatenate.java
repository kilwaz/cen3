package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;

import java.util.ArrayList;

@OperatorRepresentation(stringRepresentation = "concat")
@FunctionParameters(parameterCount = 2)
public class Concatenate extends Expression implements Function {
    public Concatenate() {
        super(Expression.PRECEDENCE_LOWER, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {
            return new Textual(parameters.get(0).getStringRepresentation() + parameters.get(1).getStringRepresentation());
        }

        return null;
    }
}
