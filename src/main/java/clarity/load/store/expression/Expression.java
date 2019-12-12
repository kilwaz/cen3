package clarity.load.store.expression;

public class Expression {
    public static final int PRECEDENCE_OPEN_BRACKET = 1;
    public static final int PRECEDENCE_CLOSE_BRACKET = 1;
    public static final int PRECEDENCE_MINUS = 2;
    public static final int PRECEDENCE_ADD = 2;
    public static final int PRECEDENCE_MULTIPLY = 4;
    public static final int PRECEDENCE_DIVIDE = 4;
    public static final int PRECEDENCE_EXPONENT = 5;
    public static final int PRECEDENCE_NUMBER = 10;
    public static final int PRECEDENCE_REFERENCE = 10;

    public static final int NON_ASSOCIATIVE = 1;
    public static final int LEFT_ASSOCIATIVE = 2;
    public static final int RIGHT_ASSOCIATIVE = 3;

    private int precedence = 0;
    private int associative = 0;

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

    public String getStringRepresentation() {
        return "";
    }
}
