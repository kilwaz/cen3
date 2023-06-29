package clarity.load.store.expression.operators.aggregate;

import clarity.load.store.expression.AggFunction;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@OperatorRepresentation(formulaRepresentation = "sumAgg")
@FunctionParameters(unlimitedParameters = true)
public class SumAgg extends Expression implements AggFunction {
    public SumAgg() {
        super(Expression.PRECEDENCE_FUNCTION, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {
            List<BigDecimal> bigDecimalList = parameters.stream().map(Expression::getNumericRepresentation).collect(Collectors.toList());
            return new Number(bigDecimalList.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        return null;
    }
}
