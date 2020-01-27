package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;
import error.Error;

@OperatorRepresentation(stringRepresentation = "concat")
public class Concatenate extends Expression implements Function {
    public Concatenate() {
        super(Expression.PRECEDENCE_LOWER, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(Expression... parameters) {
        if (parameters.length == 2) {
            return new Textual(parameters[0].getStringRepresentation() + parameters[1].getStringRepresentation());
        } else {
            Error.CLARITY_INCORRECT_NUMBER_OF_PARAMETERS.record()
                    .additionalInformation(this.getClass().getSimpleName())
                    .additionalInformation("Expects 2 parameters")
                    .additionalInformation("Given " + parameters.length + " parameters").create();
        }

        return null;
    }
}
