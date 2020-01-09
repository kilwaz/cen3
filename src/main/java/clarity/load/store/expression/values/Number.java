package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

import java.math.BigDecimal;

public class Number extends Expression implements Value {
    private BigDecimal value;

    public Number() {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
    }

    public Number(BigDecimal value) {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
        this.value = value;
    }

    public Number(Integer value) {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
        this.value = new BigDecimal(value);
    }

    public Number(Double value) {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getStringRepresentation() {
        return value.toString();
    }
}
