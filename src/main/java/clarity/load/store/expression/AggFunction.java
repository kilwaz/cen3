package clarity.load.store.expression;

import java.util.ArrayList;

public interface AggFunction {
    Expression apply(ArrayList<Expression> parameters);
}
