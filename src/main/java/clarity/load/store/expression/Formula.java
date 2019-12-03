package clarity.load.store.expression;

import clarity.load.store.expression.operators.*;
import clarity.load.store.expression.values.Number;
import log.AppLogger;
import org.apache.log4j.Logger;

public class Formula {
    private static Logger log = AppLogger.logger();
    private Node root = null;

    public Formula(String strExpression) {
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

    public Node build(Node current, String expressionStr) {
        String currentLetter = expressionStr.substring(0, 1);

        Expression expression = null;

        if (currentLetter.matches("-?\\d+(\\.\\d+)?")) {
            Double currentNumber = Double.parseDouble(currentLetter);
            expression = new Number(currentNumber);
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

        if (expressionStr.length() > 1) {
            expressionStr = expressionStr.substring(1);
            build(current, expressionStr);
        }

        return current;
    }

    public Object solve() {
        return ((Number) root.solve()).getValue().toString();
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
}
