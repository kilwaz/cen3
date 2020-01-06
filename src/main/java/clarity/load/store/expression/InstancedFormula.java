package clarity.load.store.expression;

import clarity.Record;

public class InstancedFormula {

    private Record record;
    private Formula formula;

    public InstancedFormula(Formula formula) {
        this.formula = formula;
    }

    public InstancedFormula record(Record record) {
        this.record = record;
        return this;
    }

}
