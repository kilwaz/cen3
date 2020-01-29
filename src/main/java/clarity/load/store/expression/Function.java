package clarity.load.store.expression;

import java.util.ArrayList;

public interface Function {
    Expression apply(ArrayList<Expression> parameters);
}
