package clarity.load.store.expression;

import clarity.Record;

public class InstancedFormula {
    private Record record;
    private Formula formula;

    private Node instancedRoot = null;

    public InstancedFormula(Formula formula) {
        this.formula = formula;
        this.instancedRoot = formula.getRoot().duplicate();
    }

    public InstancedFormula record(Record record) {
        this.record = record;
        return this;
    }
}
