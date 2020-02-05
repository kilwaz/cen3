package clarity.load.store.expression;

import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;

import java.math.BigDecimal;
import java.util.Objects;

public class Expression {
    public static final int PRECEDENCE_OPEN_BRACKET = 1;
    public static final int PRECEDENCE_CLOSE_BRACKET = 1;
    public static final int PRECEDENCE_COMMA = 1;
    public static final int PRECEDENCE_EVALUATION = 1;
    public static final int PRECEDENCE_FUNCTION = 1;
    public static final int PRECEDENCE_MINUS = 2;
    public static final int PRECEDENCE_ADD = 2;
    public static final int PRECEDENCE_MULTIPLY = 4;
    public static final int PRECEDENCE_DIVIDE = 4;
    public static final int PRECEDENCE_EXPONENT = 5;
    public static final int PRECEDENCE_NUMBER = 10;
    public static final int PRECEDENCE_TEXT = 10;
    public static final int PRECEDENCE_REFERENCE = 10;

    public static final int NON_ASSOCIATIVE = 1;
    public static final int LEFT_ASSOCIATIVE = 2;
    public static final int RIGHT_ASSOCIATIVE = 3;

    private int precedence = 0;
    private int associative = 0;

    private String stringRepresentation = null;

    public Expression(int precedence, int associative) {
        this.precedence = precedence;
        this.associative = associative;
    }

    public int getPrecedence() {
        return precedence;
    }

    public int getAssociative() {
        return associative;
    }

    public BigDecimal getNumericRepresentation() {
        if (this instanceof Number) {
            return ((Number) this).getValue();
        } else {
            return new BigDecimal(getStringRepresentation());
        }
    }

    public String getStringRepresentation() {
        if (stringRepresentation == null) {
            OperatorRepresentation operatorRepresentation = this.getClass().getDeclaredAnnotation(OperatorRepresentation.class);
            if (operatorRepresentation != null) {
                stringRepresentation = operatorRepresentation.stringRepresentation();
            } else {
                stringRepresentation = "";
            }
        }

        return stringRepresentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return getPrecedence() == that.getPrecedence() &&
                getAssociative() == that.getAssociative() &&
                Objects.equals(getStringRepresentation(), that.getStringRepresentation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrecedence(), getAssociative(), getStringRepresentation());
    }
}
