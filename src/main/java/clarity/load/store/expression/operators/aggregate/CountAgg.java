package clarity.load.store.expression.operators.aggregate;

import clarity.load.store.expression.AggFunction;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@OperatorRepresentation(formulaRepresentation = "countAgg")
@FunctionParameters(unlimitedParameters = true)
public class CountAgg extends Expression implements AggFunction {
    private static Logger log = AppLogger.logger();

    public CountAgg() {
        super(Expression.PRECEDENCE_FUNCTION, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {
            return new Number(parameters.size());
        }

        return null;
    }
}
