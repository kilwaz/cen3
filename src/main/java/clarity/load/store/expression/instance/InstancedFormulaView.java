package clarity.load.store.expression.instance;

import clarity.Record;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InstancedFormulaView {
    private HashMap<String, Formula> formulas;
    private HashMap<String, InstancedFormula> instancedFormulas;
    private HashMap<String, Expression> solvedFormulas;

    private List<Record> records = new ArrayList<>();

    public InstancedFormulaView(HashMap<String, Formula> formulas) {
        this.formulas = formulas;
    }

    public InstancedFormulaView records(List<Record> records) {
        this.records = records;
        this.instancedFormulas = new HashMap<>();
        this.solvedFormulas = new HashMap<>();
        return this;
    }

    public InstancedFormulaView solve() {
        for (String key : formulas.keySet()) {
            Formula formula = formulas.get(key);
            if (!formula.isFormulaView()) {
                InstancedFormula instancedFormula = formulas.get(key).createAggInstance();
                instancedFormula.records(records);
                instancedFormula.solve();
                instancedFormulas.put(key, instancedFormula);
                solvedFormulas.put(key, instancedFormula.solve());
            }
        }

        for (String key : formulas.keySet()) {
            Formula formula = formulas.get(key);
            if (formula.isFormulaView()) {
                InstancedFormula instancedFormula = formulas.get(key).createInstance();
                instancedFormula.instancedFormulaView(this);
                instancedFormula.solve();
                instancedFormulas.put(key, instancedFormula);
                solvedFormulas.put(key, instancedFormula.solve());
            }
        }

        return this;
    }

    public Expression get(String reference) {
        return solvedFormulas.get(reference);
    }
}