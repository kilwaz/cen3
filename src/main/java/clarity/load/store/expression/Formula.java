package clarity.load.store.expression;

import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.load.store.expression.instance.InstancedFormula;
import clarity.load.store.expression.operators.OperatorDictionary;
import clarity.load.store.expression.operators.grouping.CloseBracket;
import clarity.load.store.expression.operators.grouping.Comma;
import clarity.load.store.expression.operators.grouping.OpenBracket;
import clarity.load.store.expression.values.Number;
import clarity.load.store.expression.values.*;
import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {
    private static Logger log = AppLogger.logger();
    private Node root = null;
    private String strExpression = "";
    private Expression result = null;
    private Boolean isBuilt = false;
    private Boolean isFormulaView = false;

    public static Formula createForFormulaView(String strExpression) {
        Formula formula = new Formula(strExpression, false).formulaView(true);
        formula.build();
        return formula;
    }

    public static Formula createWithoutBuilding(String strExpression) {
        return new Formula(strExpression, false);
    }

    public static Formula create(String strExpression) {
        return new Formula(strExpression);
    }

    private Formula(String strExpression, Boolean dontBuild) {
        if (strExpression != null) {
            this.strExpression = strExpression;
        }
    }

    private Formula(String strExpression) {
        if (strExpression != null) {
            this.strExpression = strExpression;
            build();
        }
    }

    public Boolean build() {
        Node node = build(null, strExpression);
        while (node.getParent() != null) {
            node = node.getParent();
        }
        if (node.getExpression() instanceof OpenBracket) {
            root = node.getRight();
        } else {
            root = node;
        }

        isBuilt = true;
        return true;
    }

    public InstancedFormula createInstance() {
        return new InstancedFormula(this);
    }

    public InstancedFormula createAggInstance() {
        InstancedFormula instancedFormula = new InstancedFormula(this);
        instancedFormula.type(1);
        return instancedFormula;
    }

    private Node build(Node current, String expressionStr) {
        if (expressionStr.length() == 0) { // If an empty string is passed in handle this by making a textual element with empty string
            return new Node(new Textual(expressionStr), Node.NODE_CHILD_TYPE_BINARY);
        }

        String currentLetter = expressionStr.substring(0, 1);
        Expression expression = null;

        // Expression Parsing
        if (current != null && current.getExpression() instanceof Function) {

            current.getExpression().getStringRepresentation();

            String result = extractInnermostParens(current.getExpression().getStringRepresentation() + "(" + expressionStr);
            if (!result.contains("(")) {  // If no functions are present then pick the first item
                result = beforeFirstComma(result);
            }

            currentLetter = result;

            expression = new FormulaNode(result);
        } else if ("'".equals(currentLetter)) { // Beginning of a string literal
            Pattern pattern = Pattern.compile("'([^']*)'"); // Find the rest of the text (next single quote)
            Matcher matcher = pattern.matcher(expressionStr);
            String textual = "";
            if (matcher.find()) {
                currentLetter = matcher.group();
                textual = currentLetter.substring(1, currentLetter.length() - 1);  // Trim off single quotes
            }
            expression = new Textual(textual);
        } else if (currentLetter.matches("-?\\d+(\\.\\d+)?")) { // Beginning of a number
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?"); // Find the rest of the number
            Matcher matcher = pattern.matcher(expressionStr);
            if (matcher.find()) {
                currentLetter = matcher.group();
            }
            expression = new Number(currentLetter);
        } else if ("[".equals(currentLetter)) { // Beginning of a reference
            Pattern pattern = Pattern.compile("\\[[^\\[]*\\]"); // Find the rest of the reference name
            Matcher matcher = pattern.matcher(expressionStr);

            if (matcher.find()) {
                currentLetter = matcher.group();

                String referenceName = currentLetter.substring(1, currentLetter.length() - 1);

                if (isFormulaView) {
                    expression = new ReferenceView(referenceName);
                } else {
                    Definition referenceDefinition = Definitions.getInstance().findDefinition(referenceName);

                    if (referenceDefinition != null) {
                        if (!referenceDefinition.getFormula().isBuilt()) {
                            referenceDefinition.getFormula().build();
                        }
                        expression = new Reference(referenceDefinition);
                    } else {
                        log.info("Could not find formula reference " + referenceName);
                    }
                }
            }
        } else { // Find any other defined expressions
            String functionName = "";
            if (currentLetter.matches("[a-zA-Z]")) { // If a function name
                Pattern pattern = Pattern.compile("[a-zA-Z]+\\("); // Find the rest of the function name
                Matcher matcher = pattern.matcher(expressionStr);

                if (matcher.find()) {
                    currentLetter = matcher.group();
                    functionName = currentLetter.substring(0, currentLetter.length() - 1); // Removes additional opening bracket
                }
                if (functionName.isEmpty()) { // This means we didn't find the end of the function, so we might have a boolean expression instead
                    Pattern patternEval = Pattern.compile("(?i)^(true|false)");
                    Matcher matcherEval = patternEval.matcher(expressionStr);
                    if (matcherEval.find()) {
                        currentLetter = matcherEval.group();
                        expression = new Bool(Boolean.valueOf(currentLetter));
                    }
                }
            } else { // Single character operators like + - / *
                functionName = currentLetter;
            }

            if (!functionName.isEmpty()) {
                expression = OperatorDictionary.getInstance().createExpressionFromReference(functionName);
            }
        }

        if (expression == null) {
            Error.CLARITY_REFERENCE_NOT_FOUND.record().additionalInformation("Unable to find reference " + currentLetter + " for " + expressionStr).create();
        }

        // Tree positioning
        if (current == null) {
            // Creating the root node only once for the first item of the tree
            if (expression instanceof Function || expression instanceof AggFunction) {
                current = new Node(expression, Node.NODE_CHILD_TYPE_VARIABLE);
            } else if (expression instanceof Operator || expression instanceof Value) {
                current = new Node(expression, Node.NODE_CHILD_TYPE_BINARY);
            }
        } else {
            Node newCurrent = current;
            while (continueTreeClimb(current.getExpression(), expression)) {
                newCurrent = current.getParent();

                if (newCurrent == null) { // Top of the tree has been reached so just stop
                    break;
                }

                current = newCurrent;
            }

            if (newCurrent == null) { // Top of the tree has been reached, create a new node here
                if (expression instanceof Function || expression instanceof AggFunction) {
                    newCurrent = new Node(expression, Node.NODE_CHILD_TYPE_VARIABLE);
                    newCurrent.addToList(current);
                } else if (expression instanceof Operator || expression instanceof Value) {
                    newCurrent = new Node(expression, Node.NODE_CHILD_TYPE_BINARY);
                    newCurrent.left(current);
                }
            } else {
                if (current.getExpression() instanceof Function || current.getExpression() instanceof AggFunction) { // Children of functions are always treated separately and as a list
                    if (expression instanceof Function || expression instanceof AggFunction) {
                        newCurrent = new Node(expression, Node.NODE_CHILD_TYPE_VARIABLE);
                    } else if (expression instanceof Operator || expression instanceof Value) {
                        newCurrent = new Node(expression, Node.NODE_CHILD_TYPE_BINARY);
                    }
                    if (!(expression instanceof CloseBracket)) { // This is the ending bracket of the function, not a mathematical close bracket
                        current.addToList(newCurrent);
                    }
                } else {
                    if (expression instanceof CloseBracket) { // Brackets cancelling each other out
                        if (current.getParent() == null) { // Open Bracket is at the top of the tree
                            newCurrent = current.getRight();
                        } else { // Remove brackets
                            current.getParent().right(current.getRight());
                            newCurrent = current.getParent();
                        }
                    } else if (expression instanceof Comma) { // Used in functions to separate parameters
                        newCurrent = current.getParent();
                    } else {
                        newCurrent = new Node(expression, Node.NODE_CHILD_TYPE_BINARY);
                        Node oldRight = current.getRight();
                        current.right(newCurrent);
                        newCurrent.left(oldRight);
                    }
                }
            }
            current = newCurrent;
        }

        if (expressionStr.length() > currentLetter.length()) {
            expressionStr = expressionStr.substring(currentLetter.length());
            build(current, expressionStr);
        }

        return current;
    }

    public Node getRoot() {
        return root;
    }

    private Boolean continueTreeClimb(Expression current, Expression newExpression) {
        if (newExpression instanceof OpenBracket || newExpression instanceof Comma || newExpression instanceof Function || newExpression instanceof AggFunction) {
            return false;
        }
        switch (newExpression.getAssociative()) {
            case Expression.LEFT_ASSOCIATIVE:
            case Expression.NON_ASSOCIATIVE:
                return current.getPrecedence() >= newExpression.getPrecedence();
            case Expression.RIGHT_ASSOCIATIVE:
                return current.getPrecedence() > newExpression.getPrecedence();
            default:
                return false;
        }
    }

    public String getStrExpression() {
        return strExpression;
    }

    public Expression getResult() {
        return result;
    }

    public boolean isBuilt() {
        return isBuilt;
    }

    public Formula formulaView(Boolean isFormulaView) {
        this.isFormulaView = isFormulaView;
        return this;
    }

    public Boolean isFormulaView() {
        return isFormulaView;
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

    private static String beforeFirstComma(String input) {
        boolean inQuotes = false;
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c == '\'') {
                inQuotes = !inQuotes;  // Flip the inQuotes boolean
            } else if (c == ',' && !inQuotes) {
                break;  // Stop when finding a comma not in quotes
            }
            result.append(c);
        }
        return result.toString();
    }
}
