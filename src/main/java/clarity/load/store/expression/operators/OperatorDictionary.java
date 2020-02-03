package clarity.load.store.expression.operators;

import clarity.load.store.expression.Expression;
import error.Error;
import error.RecordedError;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

        //TODO: I feel this like is an error you can't recover from, maybe this should be reported instead of just throwing null?
        return null;
    }

    public static Boolean validateParameterCount(Expression expression, ArrayList<Expression> parameters) {
        FunctionParameters functionParameters = expression.getClass().getDeclaredAnnotation(FunctionParameters.class);

        if (functionParameters != null) {
            int parameterCount = functionParameters.parameterCount();
            boolean unlimitedParameters = functionParameters.unlimitedParameters();
            if (!unlimitedParameters) { // Unlimited parameters is returned as true
                if (parameterCount != parameters.size()) { // If equals parameter size return true otherwise show error
                    RecordedError recordedError = Error.CLARITY_INCORRECT_NUMBER_OF_PARAMETERS.record()
                            .additionalInformation("Function " + expression.getStringRepresentation() + "\t\t(Class " + expression.getClass().getSimpleName() + ")")
                            .additionalInformation("Expects " + parameterCount + " parameters")
                            .additionalInformation("Given " + parameters.size() + " parameters");
                    for (Expression param : parameters) {
                        recordedError.additionalInformation("\t" + param.getStringRepresentation() + "\t\t(Class " + param.getClass().getSimpleName() + ")");
                    }
                    recordedError.create();
                }
            }
            
            return true;
        } else {
            Error.CLARITY_MISSING_FUNCTION_PARAMETER_NUMBER.record()
                    .additionalInformation("Function " + expression.getStringRepresentation() + "\t\t(Class " + expression.getClass().getSimpleName() + ")")
                    .create();
        }

        return false;
    }

    public static OperatorDictionary getInstance() {
        if (instance == null) {
            instance = new OperatorDictionary();
        }
        return instance;
    }
}