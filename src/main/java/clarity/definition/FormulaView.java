package clarity.definition;

import clarity.load.store.expression.Formula;
import clarity.load.store.expression.instance.InstancedFormulaView;

import java.util.HashMap;

public class FormulaView {
    private HashMap<String, Formula> formulas = new HashMap<>();

    public FormulaView() {

    }

    public FormulaView add(String reference, Formula formula) {
        this.formulas.put(reference, formula);
        return this;
    }

    public InstancedFormulaView createInstance() {
        InstancedFormulaView instancedFormula = new InstancedFormulaView(formulas);

        return instancedFormula;
    }
}