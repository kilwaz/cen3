package clarity.load.store.expression;

import clarity.load.store.expression.operators.Add;
import clarity.load.store.expression.operators.Minus;
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
        }

        if (current == null) {
            // Creating the root node
            current = new Node(expression1);
        } else {
            Integer newItemPrecedence = expression1.getPrecedence();
            Integer currentPrecedence = current.getExpression().getPrecedence();

            Node nextCheck = current;
            while (currentPrecedence <= newItemPrecedence) {
                nextCheck = current.getParent();

                if (nextCheck == null) { // Top of the tree has been reached
                    break;
                }

                current = nextCheck;
                currentPrecedence = current.getExpression().getPrecedence();
            }

            if (nextCheck == null) { // Top of the tree has been reached, create a new node here
                nextCheck = new Node(expression1);
                nextCheck.left(current);
                current.parent(nextCheck);
                current = nextCheck;
            } else {
                nextCheck = new Node(expression1);
                nextCheck.parent(current);
                current.right(nextCheck);

                current = nextCheck;
            }
        }

        log.info(currentLetter);

        if (expression.length() > 1) {
            expression = expression.substring(1);
            build(current, expression);
        }

        return current;
    }

    public Expression solve() {
        return root.solve();
    }

    public Node getRoot() {
        return root;
    }

    public void print(){
        
    }
}
