package clarity.load.store.expression;

public class Expression {
    public static final int PRECEDENCE_MINUS = 2;
    public static final int PRECEDENCE_ADD = 2;
    public static final int PRECEDENCE_MULTIPLY = 4;
    public static final int PRECEDENCE_DIVIDE = 4;

    public static final int PRECEDENCE_NUMBER = 10;

    private int precedence = 0;

    public Expression(int precedence) {
        this.precedence = precedence;
    }

    public int getPrecedence() {
        return precedence;
    }

    public String getStringRepresentation() {
        return "";
    }
}
