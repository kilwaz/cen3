package clarity;

import clarity.load.store.expression.Formula;

public class Definition {

    private String name = "";
    private Formula formula;

    public Definition() {

    }

    public static Definition define() {
        return new Definition();
    }

    public Definition name(String name) {
        this.name = name;
        return this;
    }

    public Definition formula(String expression) {
        this.formula = new Formula(expression);
        return this;
    }

    public Formula getFormula() {
        return formula;
    }

    public String getName() {
        return this.name;
    }

    public Object calculate() {
        formula.solve();

        return formula.getResult();
    }
}
