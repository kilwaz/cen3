package clarity.load.store.expression.operators.function;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;
import error.Error;

import java.util.ArrayList;

@OperatorRepresentation(stringRepresentation = "upper")
public class Upper extends Expression implements Function {
    public Upper() {
        super(Expression.PRECEDENCE_UPPER, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (parameters.size() == 1) {
            return new Textual(parameters.get(0).getStringRepresentation().toUpperCase());
        } else {
            Error.CLARITY_INCORRECT_NUMBER_OF_PARAMETERS.record()
                    .additionalInformation(this.getClass().getSimpleName())
                    .additionalInformation("Expects 1 parameter")
                    .additionalInformation("Given " + parameters.size() + " parameters").create();
        }

        return null;
    }
}
