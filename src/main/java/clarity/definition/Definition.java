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

    public static Definition define(String name) {
        Definition definition;
        Definitions definitions = Definitions.getInstance();
        if (definitions.findDefinition(name) == null) {
            definition = Definition.create(Definition.class);
            definition.name(name);
            definitions.addDefinition(definition);
        } else { // If definition already exists, get the already defined version
            definition = definitions.findDefinition(name);
        }

        return definition;
    }

    public Definition name(String name) {
        String oldName = this.name;
        this.name = name;
        Definitions.getInstance().updateDefinitionName(this, oldName);
        this.save();
        return this;
    }

    public Definition expression(String expression) {
        this.formula = new Formula(expression);
        calculated(true);
        this.save();
        return this;
    }

    public Boolean build() {
        return formula.build();
    }

    public String getExpression() {
        if (formula != null) {
            return formula.getStrExpression();
        } else {
            return "";
        }
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

    public Definition calculated(Boolean calculated) {
        this.calculated = calculated;
        this.save();
        return this;
    }
}
