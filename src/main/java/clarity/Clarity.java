package clarity;

import clarity.load.Load;
import clarity.load.store.expression.Formula;

public class Clarity {
    public static Load load() {
        return new Load();
    }

    public static Formula formula(String expression) {
        return Formula.create(expression);
    }
}
