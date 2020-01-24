package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.Set;

public class OperatorDictionary {
    private static Logger log = AppLogger.logger();
    public static OperatorDictionary instance;

    public OperatorDictionary() {
        instance = this;

        Set<Class<? extends Expression>> operators = new Reflections("clarity.load.store.expression.operators").getSubTypesOf(Expression.class);

        for (Class clazz : operators) {
            OperatorRepresentation operatorRepresentation = (OperatorRepresentation) clazz.getDeclaredAnnotation(OperatorRepresentation.class);
            if (operatorRepresentation != null) {
                log.info(operatorRepresentation.stringRepresentation());
            }
        }
    }

    public static OperatorDictionary getInstance() {
        if (instance == null) {
            instance = new OperatorDictionary();
        }
        return instance;
    }
}