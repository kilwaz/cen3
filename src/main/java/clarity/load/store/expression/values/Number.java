package clarity.load.store.expression.values;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Number extends Expression implements Value {
    private BigDecimal value;

    public Number() {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
    }

    public Number(BigDecimal value) {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
        this.value = value;
        setScale();
    }

    public Number(Integer value) {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
        this.value = new BigDecimal(value);
        setScale();
    }

    public Number(Double value) {
        super(Expression.PRECEDENCE_NUMBER, Expression.NON_ASSOCIATIVE);
        this.value = new BigDecimal(value);
        setScale();
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getStringRepresentation() {
        return value.stripTrailingZeros().toPlainString();
    }

    private void setScale() {
        this.value = this.value.setScale(30, RoundingMode.HALF_UP);
    }
}
