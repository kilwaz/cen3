package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;
import error.Error;

@OperatorRepresentation(stringRepresentation = "upper")
public class Upper extends Expression implements Function {
    public Upper() {
        super(Expression.PRECEDENCE_UPPER, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(Expression... parameters) {
        if (parameters.length == 1) {
            return new Textual(parameters[0].getStringRepresentation().toUpperCase());
        } else {
            Error.CLARITY_INCORRECT_NUMBER_OF_PARAMETERS.record()
                    .additionalInformation("Upper")
                    .additionalInformation("Expects 1 parameter")
                    .additionalInformation("Given " + parameters.length + " parameters").create();
        }

        return null;
    }
}
