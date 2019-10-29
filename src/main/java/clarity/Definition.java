package clarity;

import org.mariuszgromada.math.mxparser.Expression;

public class Definition {

    private String name = "";
    private String expression = "";

    public Definition() {

    }

    public static Definition define() {
        return new Definition();
    }

    public Definition name(String name) {
        this.name = name;
        return this;
    }

    public Definition expression(String expression) {
        this.expression = expression;
        return this;
    }

    public String getExpression() {
        return this.expression;
    }

    public String getName() {
        return this.name;
    }

    public double calculate() {
        Expression e = new Expression(expression);

        return e.calculate();
    }
}
