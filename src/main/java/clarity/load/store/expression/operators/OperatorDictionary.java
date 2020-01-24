package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

public class OperatorDictionary {
    private static Logger log = AppLogger.logger();

    private HashMap<String, Class<? extends Expression>> operatorDictionary = new HashMap<>();
    private static OperatorDictionary instance;

    public OperatorDictionary() {
        instance = this;

        Set<Class<? extends Expression>> operators = new Reflections("clarity.load.store.expression.operators").getSubTypesOf(Expression.class);

        for (Class<? extends Expression> clazz : operators) {
            OperatorRepresentation operatorRepresentation = (OperatorRepresentation) clazz.getDeclaredAnnotation(OperatorRepresentation.class);
            if (operatorRepresentation != null) {
                operatorDictionary.put(operatorRepresentation.stringRepresentation(), clazz);
            }
        }
    }

    public Expression createExpressionFromReference(String reference) {
        Class<? extends Expression> expressionClass = operatorDictionary.get(reference);
        if (expressionClass != null) {
            try {
                Constructor<?> ctor = expressionClass.getConstructor();
                return (Expression) ctor.newInstance();
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //TODO: I feel this like is an error you can't recover from
        return null;
    }

    public static OperatorDictionary getInstance() {
        if (instance == null) {
            instance = new OperatorDictionary();
        }
        return instance;
    }
}