package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@OperatorRepresentation(formulaRepresentation = "max")
@FunctionParameters(unlimitedParameters = true)
public class Max extends Expression implements Function {
    public Max() {
        super(Expression.PRECEDENCE_FUNCTION, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {
            List<BigDecimal> bigDecimalList = parameters.stream().map(Expression::getNumericRepresentation).collect(Collectors.toList());
            return new Number(Collections.max(bigDecimalList));
        }

        return null;
    }
}
