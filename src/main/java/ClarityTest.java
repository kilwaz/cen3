import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.instance.InstancedFormula;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClarityTest {
    private static Logger log = AppLogger.logger();

    private static ClarityTest clarityTest;
    private static int testCount = 0;
    private static int passedCount = 0;

    public static void main(String[] args) {
        clarityTest = new ClarityTest();

//        log.info("Cores = " + Runtime.getRuntime().availableProcessors());

        clarityTest.runStandardTests();

        log.info("Tests passed = " + passedCount + "/" + testCount);
    }

    private static String getInnerBracketsContent(String input) {
        Stack<Character> stack = new Stack<>();
        int firstOpenBracketIndex = -1;
        int lastCloseBracketIndex = -1;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(') {
                stack.push(ch);
                if (firstOpenBracketIndex == -1) {
                    firstOpenBracketIndex = i;
                }
            } else if (ch == ')') {
                if (stack.empty()) {
                    throw new RuntimeException("Unbalanced parentheses");
                }
                stack.pop();
                if (stack.empty()) {
                    lastCloseBracketIndex = i;
                }
            }
        }

        if (!stack.empty()) {
            throw new RuntimeException("Unbalanced parentheses");
        }

        if (firstOpenBracketIndex != -1 && lastCloseBracketIndex != -1) {
            // Extract the content within the inner parentheses.
            return input.substring(firstOpenBracketIndex + 1, lastCloseBracketIndex);
        } else {
            // No parentheses found, return the content within the first pair of single quotes.
            Pattern pattern = Pattern.compile("'(.*?)'");
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                return matcher.group(1);
            } else {
                throw new RuntimeException("No parentheses and no single quotes found");
            }
        }
    }

    private static String extractInnermostParens(String expression) {
        Stack<Integer> stack = new Stack<>();
        String result = "";
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                stack.push(i);
            } else if (expression.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    int start = stack.pop();
                    // If stack is now empty, this is the innermost parentheses pair.
                    if (stack.isEmpty()) {
                        result += expression.substring(start + 1, i);
                    }
                }
            }
        }
        return result;
    }

    private void runStandardTests() {
        testFormula("proper('alexander brown')", "Alexander Brown");
        testFormula("concat('1','2','3')", "123");
        testFormula("proper(concat('alexander',' ','brown'))", "Alexander Brown");
        testFormula("proper(concat('alex,ander',' ','brown'))", "Alex,ander Brown");
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
        testFormula("if(3=3,'A','B')", "A");
        testFormula("if(3=4,'A','B')", "B");
    }

    private void testFormula(String expression, Object expectedResult) {
        Expression expressionResult = runFormula(expression);

        testCount++;
        Boolean result = testResult(expressionResult, expectedResult);
        log.info("Test " + testCount + ": " + expression + " => " + expressionResult.getStringRepresentation() + " Expected: " + expectedResult + " Result: " + (result ? "Passed" : "Failed"));

        if (result) {
            passedCount++;
        }
    }

    private Expression runFormula(String expression) {
        Formula formula = Formula.create(expression);
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