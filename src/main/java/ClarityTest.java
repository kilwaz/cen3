import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.instance.InstancedFormula;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

public class ClarityTest {
    private static Logger log = AppLogger.logger();

    private static ClarityTest clarityTest;

    public static void main(String[] args) {
        clarityTest = new ClarityTest();

        clarityTest.runStandardTests();
    }

    private void runStandardTests() {
        testFormula("3=3", true);
        testFormula("3=4", false);
        testFormula("3<4", true);
        testFormula("3>4", false);
        testFormula("3+4", 7);
        testFormula("3-4", -1);
        testFormula("3*4", 12);
        testFormula("6/2", 3);
        testFormula("'A'='B'", false);
        testFormula("if(true,1,0)", 1);
        testFormula("if(true,'A','B')", "A");
        clarityTest.testFormula("if(3=3,'A','B')", "A");
        clarityTest.testFormula("if(3=4,'A','B')", "B");
    }

    private void testFormula(String expression, Object expectedResult) {
        Expression expressionResult = runFormula(expression);

        Boolean result = testResult(expressionResult, expectedResult);
        log.info("Test: " + expression + " => " + expressionResult.getStringRepresentation() + " Expected: " + expectedResult + " Result: " + (result ? "Passed" : "Failed"));
    }

    private Expression runFormula(String expression) {
        Formula formula = new Formula(expression);
        InstancedFormula instancedFormula = formula.createInstance();
        log.info("   Rep: " + instancedFormula.getStringRepresentation());
        return instancedFormula.solve();
    }

    private Boolean testResult(Expression expression, Object expectedResult) {
        if (expectedResult instanceof Boolean) {
            return expression.getEvaluationRepresentation() == expectedResult;
        } else if (expectedResult instanceof Integer) {
            return expression.getNumericRepresentation().intValue() == (Integer) expectedResult;
        } else if (expectedResult instanceof String) {
            return expression.getStringRepresentation().equals(expectedResult);
        }

        return false;
    }
}