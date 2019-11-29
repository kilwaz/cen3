package clarity.load.store.expression;

import clarity.load.store.expression.operators.Add;
import clarity.load.store.expression.operators.Divide;
import clarity.load.store.expression.operators.Minus;
import clarity.load.store.expression.operators.Multiply;
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
        root = node;
    }

    public Node build(Node current, String expression) {
        String currentLetter = expression.substring(0, 1);

        Expression expression1 = null;

        if (currentLetter.matches("-?\\d+(\\.\\d+)?")) {
            Double currentNumber = Double.parseDouble(currentLetter);
            expression1 = new Number(currentNumber);
        } else if ("+".equals(currentLetter)) {
            expression1 = new Add();
        } else if ("-".equals(currentLetter)) {
            expression1 = new Minus();
        } else if ("/".equals(currentLetter)) {
            expression1 = new Divide();
        } else if ("*".equals(currentLetter)) {
            expression1 = new Multiply();
        }

        if (current == null) {
            // Creating the root node only once for the first item of the tree
            current = new Node(expression1);
        } else {
            Integer newItemPrecedence = expression1.getPrecedence(); // Precedence of the new item to add
            Integer currentPrecedence = current.getExpression().getPrecedence();

            Node newCurrent = current;
            while (currentPrecedence >= newItemPrecedence) {
                newCurrent = current.getParent();

                if (newCurrent == null) { // Top of the tree has been reached so just stop
                    break;
                }

                current = newCurrent;
                currentPrecedence = current.getExpression().getPrecedence();
            }

            if (newCurrent == null) { // Top of the tree has been reached, create a new node here
                newCurrent = new Node(expression1);
                newCurrent.left(current);
            } else {
                newCurrent = new Node(expression1);

                Node oldRight = current.getRight();
                current.right(newCurrent);
                newCurrent.left(oldRight);
            }
            current = newCurrent;
        }

        if (expression.length() > 1) {
            expression = expression.substring(1);
            build(current, expression);
        }

        return current;
    }

    public Object solve() {
        return ((Number) root.solve()).getValue().toString();
    }

    public Node getRoot() {
        return root;
    }
}
