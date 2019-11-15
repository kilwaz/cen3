package clarity.load.store.expression;

import clarity.load.store.expression.values.Number;

public class Node {
    private Node left = null;
    private Node right = null;
    private Node parent = null;

    private Boolean solved = false;

    private Expression expression;

    public Node(Expression expression) {
        this.expression = expression;
    }

    public Node left(Node left) {
        this.left = left;
        return this;
    }

    public Node right(Node right) {
        this.right = right;
        return this;
    }

    public Node parent(Node parent) {
        this.parent = parent;
        return this;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    public Expression getExpression() {
        return expression;
    }

    public Expression solve() {
        if (expression instanceof Number) {
            solved = true;
            return expression;
        } else if (expression instanceof Operator) {
            Expression rightExpression = null;
            Expression leftExpression = null;
            if (right != null) {
                rightExpression = right.solve();
            }
            if (left != null) {
                leftExpression = left.solve();
            }
            
            solved = true;
            return ((Operator) expression).calculate(rightExpression, leftExpression);
        } else {
            return null;
        }
    }
}
