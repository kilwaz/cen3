package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@OperatorRepresentation(stringRepresentation = "average")
@FunctionParameters(unlimitedParameters = true)
public class Average extends Expression implements Function {
    public Average() {
        super(Expression.PRECEDENCE_FUNCTION, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {
            if (parameters.size() == 0) { // Return zero if no parameters are passed in
                return new Number(0);
            }
            List<BigDecimal> bigDecimalList = parameters.stream().map(Expression::getNumericRepresentation).collect(Collectors.toList());

            BigDecimal total = bigDecimalList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal mean = total.divide(new BigDecimal(parameters.size()), 30, RoundingMode.HALF_UP);

            return new Number(mean);
        }

        return null;
    }
}
