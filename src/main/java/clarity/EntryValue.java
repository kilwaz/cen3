package clarity;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.values.Number;
import clarity.load.store.expression.values.Textual;
import error.Error;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class EntryValue {
    private static Logger log = AppLogger.logger();

    private Object value;

    public EntryValue() {

    }

    public EntryValue(Expression expression) {
        this.value = expression.getStringRepresentation();
    }

    public EntryValue(Object value) {
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public Expression toExpression() {
        if (value instanceof Double) {
            return new Number((Double) value);
        } else if (value instanceof Integer) {
            return new Number((Integer) value);
        } else if (value instanceof BigDecimal) {
            return new Number((BigDecimal) value);
        } else if (value instanceof String) {
            return new Textual((String) value);
        } else {
            Error.CLARITY_TYPE_NOT_HANDLED.record()
                    .additionalInformation("Not handled: " + value.getClass().getCanonicalName())
                    .create();
        }
        return null;
    }
}
