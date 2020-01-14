package clarity.load.store.expression.instance;

import clarity.Record;
import clarity.definition.Definition;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.values.Reference;
import log.AppLogger;
import org.apache.log4j.Logger;

public class InstancedFormula {
    private static Logger log = AppLogger.logger();

    private Record record;

    private InstancedNode instancedRoot = null;
    private String baseFormula;

    public InstancedFormula(Formula formula) {
        this.instancedRoot = formula.getRoot().duplicate().instancedFormula(this);
        this.baseFormula = formula.getStrExpression();
    }

    public void substituteRecordValues(InstancedNode node) {
        Expression expression = node.getExpression();
        if (expression instanceof Reference) {
            Definition definition = ((Reference) expression).getValue();
            if (definition.isCalculated()) {
                InstancedFormula instancedFormula = definition.getFormula()
                        .createInstance()
                        .record(this.record);

                Expression solvedExpression = instancedFormula.solve();
                node.expression(solvedExpression);
            } else {
                node.expression(record.get(definition.getName()).get().toExpression());
            }
        }

        if (node.getRight() != null) {
            substituteRecordValues(node.getRight());
        }
        if (node.getLeft() != null) {
            substituteRecordValues(node.getLeft());
        }
    }

    public Expression solve() {
        if (instancedRoot != null) {
            substituteRecordValues(instancedRoot);
            return instancedRoot.solve();
        }

        return null;
    }

    public InstancedFormula record(Record record) {
        this.record = record;
        return this;
    }

    public InstancedNode getInstancedRoot() {
        return instancedRoot;
    }

    public String getBaseFormula() {
        return baseFormula;
    }
}
