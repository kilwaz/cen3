package clarity.load.store.expression.operators.logic;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;
import org.apache.commons.text.WordUtils;

import java.util.ArrayList;

@OperatorRepresentation(stringRepresentation = "if")
@FunctionParameters(parameterCount = 3)
public class If extends Expression implements Function {
    public If() {
        super(Expression.PRECEDENCE_FUNCTION, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {


            return new Textual(WordUtils.capitalizeFully(parameters.get(0).getStringRepresentation()));
        }

        return null;
    }
}
