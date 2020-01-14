package clarity.definition;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Formula;

public class Definition {
    private String name = "";
    private Formula formula;
    private Boolean calculated = false;

    public Definition() {
        name = hashCode() + "";
    }

    public static Definition define() {
        Definition definition = new Definition();
        Definitions.getInstance().addDefinition(definition);
        return definition;
    }

    public Definition name(String name) {
        String oldName = this.name;
        this.name = name;
        Definitions.getInstance().updateDefinitionName(this, oldName);
        return this;
    }

    public Definition formula(String expression) {
        this.formula = new Formula(expression);
        calculated = true;
        return this;
    }

    public Formula getFormula() {
        return formula;
    }

    public String getName() {
        return this.name;
    }

    public Expression calculate() {
        //formula.solve();

        return formula.getResult();
    }

    public Boolean isCalculated() {
        return calculated;
    }
}
