package clarity.load.store.expression;

public class Expression {
    private int precedence = 2;

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
