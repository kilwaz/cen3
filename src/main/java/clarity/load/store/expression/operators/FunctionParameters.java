package clarity.load.store.expression.operators;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionParameters {
    int parameterCount() default 0;

    boolean unlimitedParameters() default false;
}
