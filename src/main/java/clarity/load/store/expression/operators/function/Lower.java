package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;
import error.Error;

@OperatorRepresentation(stringRepresentation = "lower")
public class Lower extends Expression implements Function {
    public Lower() {
        super(Expression.PRECEDENCE_LOWER, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(Expression... parameters) {
        if (parameters.length == 1) {
            return new Textual(parameters[0].getStringRepresentation().toLowerCase());
        } else {
            Error.CLARITY_INCORRECT_NUMBER_OF_PARAMETERS.record()
                    .additionalInformation("Lower")
                    .additionalInformation("Expects 1 parameter")
                    .additionalInformation("Given " + parameters.length + " parameters").create();
        }

        return null;
    }
}
