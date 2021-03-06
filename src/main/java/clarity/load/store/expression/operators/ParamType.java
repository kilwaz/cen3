package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ParamType {
    Class<? extends Expression>[] paramTypes();
}
