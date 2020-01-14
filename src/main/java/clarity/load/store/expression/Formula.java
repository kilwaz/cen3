package clarity.load.store.expression;

import clarity.definition.Definition;
import clarity.definition.Definitions;
import clarity.load.store.expression.instance.InstancedFormula;
import clarity.load.store.expression.operators.*;
import clarity.load.store.expression.values.Number;
import clarity.load.store.expression.values.Reference;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {
    private static Logger log = AppLogger.logger();
    private Node root = null;
    private String strExpression = "";
    private Expression result = null;

    public Formula(String strExpression) {
        this.strExpression = strExpression;
        Node node = build(null, strExpression);
        while (node.getParent() != null) {
            node = node.getParent();
        }
        if (node.getExpression() instanceof OpenBracket) {
            root = node.getRight();
        } else {
            root = node;
        }
    }

    public InstancedFormula createInstance() {
        return new InstancedFormula(this);
    }

    private Node build(Node current, String expressionStr) {
        String currentLetter = expressionStr.substring(0, 1);
        Expression expression = null;

        if (currentLetter.matches("-?\\d+(\\.\\d+)?")) { // Checks to see if next character is the start of a number
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?"); // Find the rest of the number
            Matcher matcher = pattern.matcher(expressionStr);
            if (matcher.find()) {
                currentLetter = matcher.group();
            }
            double currentNumber = Double.parseDouble(currentLetter);
            expression = new Number(currentNumber);
//        } else if(" ".equals(currentLetter)) {

        } else if ("+".equals(currentLetter)) {
            expression = new Add();
        } else if ("-".equals(currentLetter)) {
            expression = new Minus();
        } else if ("/".equals(currentLetter)) {
            expression = new Divide();
        } else if ("*".equals(currentLetter)) {
            expression = new Multiply();
        } else if ("^".equals(currentLetter)) {
            expression = new Exponent();
        } else if ("(".equals(currentLetter)) {
            expression = new OpenBracket();
        } else if (")".equals(currentLetter)) {
            expression = new CloseBracket();
        } else if ("[".equals(currentLetter)) {
            Pattern pattern = Pattern.compile("\\[[^\\[]*\\]"); // Find the rest of the number
            Matcher matcher = pattern.matcher(expressionStr);

            if (matcher.find()) {
                currentLetter = matcher.group();

                String referenceName = currentLetter.substring(1, currentLetter.length() - 1);
                Definition referenceDefinition = Definitions.getInstance().findDefinition(referenceName);

                if (referenceDefinition != null) {
                    expression = new Reference(referenceDefinition);
                } else {
                    log.info("Could not find formula reference " + referenceName);
                    //expression = new Reference(new Formula("0"));
                }
            }
        }

        if (current == null) {
            // Creating the root node only once for the first item of the tree
            current = new Node(expression);
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
                newCurrent = new Node(expression);
                newCurrent.left(current);
            } else {
                if (expression instanceof CloseBracket) { // Brackets cancelling each other out
                    if (current.getParent() == null) { // Open Bracket is at the top of the tree
                        newCurrent = current.getRight();
                        //newCurrent.parent(null);
                    } else { // Remove brackets
                        current.getParent().right(current.getRight());
                        newCurrent = current.getParent();
                    }
                } else {
                    newCurrent = new Node(expression);

                    Node oldRight = current.getRight();
                    current.right(newCurrent);
                    newCurrent.left(oldRight);
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
        if (newExpression instanceof OpenBracket) {
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
}
