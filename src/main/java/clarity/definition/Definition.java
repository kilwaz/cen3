package clarity.definition;

import clarity.load.store.expression.Formula;
import data.model.DatabaseObject;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Definition extends DatabaseObject {
    public static final Integer DEFINITION_TYPE_UNDEFINED = 0;
    public static final Integer DEFINITION_TYPE_NUMBER = 1;
    public static final Integer DEFINITION_TYPE_STRING = 2;
    public static final Integer DEFINITION_TYPE_DURATION = 3;

    public static final Integer CONTEXT_TYPE_UNDEFINED = 0;
    public static final Integer CONTEXT_TYPE_RECORD = 1;
    public static final Integer CONTEXT_TYPE_AGGREGATE = 2;
    public static final Integer CONTEXT_TYPE_VIEW = 3;

    private String name = "";
    private Formula formula;
    private Boolean calculated = false;
    private Integer definitionType = DEFINITION_TYPE_UNDEFINED;
    private Integer contextType = CONTEXT_TYPE_UNDEFINED;
    private List<Definition> dependants = new ArrayList<>();

    private static Logger log = AppLogger.logger();

    public Definition() {
        super();
        name = hashCode() + "";
    }

    public Definition(UUID uuid, String name, Boolean calculated, Integer definitionType) {
        super(uuid);
        this.name = name;
        this.calculated = calculated;
        this.definitionType = definitionType;
    }

    public static Definition define(String name, Integer type, Integer contextType) {
        Definition definition;
        Definitions definitions = Definitions.getInstance();
        if (definitions.findDefinition(name) == null) {
            definition = Definition.create(Definition.class);
            definition.name(name);
            definition.contextType(contextType);
            definition.definitionType(type);
            definitions.addDefinition(definition);
        } else { // If definition already exists, get the already defined version
            definition = definitions.findDefinition(name);
        }

        return definition;
    }

    public Definition asTypeString() {
        definitionType = DEFINITION_TYPE_STRING;
        this.save();
        return this;
    }

    public Definition asTypeNumber() {
        definitionType = DEFINITION_TYPE_NUMBER;
        this.save();
        return this;
    }

    public Definition asTypeDuration() {
        definitionType = DEFINITION_TYPE_DURATION;
        this.save();
        return this;
    }

    public Definition name(String name) {
        String oldName = this.name;
        this.name = name;
        Definitions.getInstance().updateDefinitionName(this, oldName);
        this.save();
        return this;
    }

    public Definition expression(String expression) {
        this.formula = Formula.createWithoutBuilding(expression);
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

    public Boolean isContextTypeRecord() {
        return Objects.equals(this.contextType, CONTEXT_TYPE_RECORD);
    }

    public Boolean isContextTypeAggregate() {
        return Objects.equals(this.contextType, CONTEXT_TYPE_AGGREGATE);
    }

    public Boolean isContextTypeView() {
        return Objects.equals(this.contextType, CONTEXT_TYPE_VIEW);
    }

    public Definition calculated(Boolean calculated) {
        this.calculated = calculated;
        this.save();
        return this;
    }

    public int getDefinitionType() {
        return definitionType;
    }

    public Integer getContextType() {
        return contextType;
    }

    public void clearDependants() {
        dependants.clear();
    }

    public void addDependant(Definition definition) {
        dependants.add(definition);
    }

    public List<Definition> getDependants() {
        return dependants;
    }

    public Definition definitionType(Integer definitionType) {
        this.definitionType = definitionType;
        this.save();
        return this;
    }

    public Definition contextType(Integer contextType) {
        this.contextType = contextType;
        this.save();
        return this;
    }
}
