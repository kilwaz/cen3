package clarity.definition;

import clarity.load.store.expression.Formula;
import data.model.DatabaseObject;

import java.util.UUID;

public class Definition extends DatabaseObject {
    private String name = "";
    private Formula formula;
    private Boolean calculated = false;

    public Definition() {
        super();
        name = hashCode() + "";
    }

    public Definition(UUID uuid, String name, Boolean calculated) {
        super(uuid);
        this.name = name;
        this.calculated = calculated;
    }

    public static Definition define() {
        Definition definition = Definition.create(Definition.class);
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

    public Boolean isCalculated() {
        return calculated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalculated(Boolean calculated) {
        this.calculated = calculated;
    }
}
