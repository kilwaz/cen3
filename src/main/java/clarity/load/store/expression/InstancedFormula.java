package clarity.load.store.expression;

import clarity.Record;
import clarity.definition.Definition;
import clarity.load.store.expression.values.Reference;

public class InstancedFormula {
    private Record record;

    private Node instancedRoot = null;

    public InstancedFormula(Formula formula) {
        this.instancedRoot = formula.getRoot().duplicate();
    }

    private void substituteRecordValues(Node node) {
        Expression expression = node.getExpression();
        if (expression instanceof Reference) {
            Definition definition = ((Reference) expression).getValue();
            if (definition.isCalculated()) {
                // This value is another calculated value, go figure that shit out...
                // InstancedFormula instancedFormula = ((Reference) expression).getValue().getFormula()
                //    .createInstance()
                //    .record(record);
            } else {
                node.expression(record.get(definition.getName()).get().toExpression());
                // Search for this value in the record and sub it in
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

    public Node getInstancedRoot() {
        return instancedRoot;
    }
}
