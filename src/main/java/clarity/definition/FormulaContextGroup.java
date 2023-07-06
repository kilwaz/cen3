package clarity.definition;

import data.model.DatabaseObject;

public class FormulaContextGroup extends DatabaseObject {
    private Definition definition;
    private FormulaContext formulaContext;

    public FormulaContextGroup() {
        super();
    }

    public FormulaContextGroup(Definition definition, FormulaContext formulaContext) {
        this.definition = definition;
        this.formulaContext = formulaContext;
    }

    public Definition getDefinition() {
        return definition;
    }

    public String getDefinitionUUID() {
        return definition.getUuidString();
    }

    public void definition(Definition definition) {
        this.definition = definition;
    }

    public FormulaContext getFormulaContext() {
        return formulaContext;
    }

    public String getFormulaContextUUID() {
        return formulaContext.getUuidString();
    }

    public void formulaContext(FormulaContext formulaContext) {
        this.formulaContext = formulaContext;
    }
}