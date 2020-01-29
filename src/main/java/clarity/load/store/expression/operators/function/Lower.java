package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;
import error.Error;

import java.util.ArrayList;

@OperatorRepresentation(stringRepresentation = "lower")
public class Lower extends Expression implements Function {
    public Lower() {
        super(Expression.PRECEDENCE_LOWER, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (parameters.size() == 1) {
            return new Textual(parameters.get(0).getStringRepresentation().toLowerCase());
        } else {
            Error.CLARITY_INCORRECT_NUMBER_OF_PARAMETERS.record()
                    .additionalInformation(this.getClass().getSimpleName())
                    .additionalInformation("Expects 1 parameter")
                    .additionalInformation("Given " + parameters.size() + " parameters").create();
        }

        return null;
    }
}
