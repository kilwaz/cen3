package clarity.load.store.expression.operators.function;

import clarity.definition.Matrices;
import clarity.load.store.DefinedMatrix;
import clarity.load.store.MatrixEntry;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.operators.FunctionParameters;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Textual;

import java.util.ArrayList;

@OperatorRepresentation(stringRepresentation = "matrix")
@FunctionParameters(parameterCount = 2)
public class Matrix extends Expression implements Function {
    public Matrix() {
        super(Expression.PRECEDENCE_FUNCTION, Expression.LEFT_ASSOCIATIVE);
    }

    @Override
    public Expression apply(ArrayList<Expression> parameters) {
        if (OperatorDictionary.validateParameterCount(this, parameters)) {
            DefinedMatrix definedMatrix = Matrices.getInstance().findDefinedMatrix(parameters.get(0).getStringRepresentation());
            if (definedMatrix != null) {
                MatrixEntry matrixEntry = definedMatrix.findItem(parameters.get(1).getStringRepresentation());
                if (matrixEntry != null) {
                    return new Textual(matrixEntry.getValue());
                }
            }
        }

        return null;
    }
}
