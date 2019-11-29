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
        if (left != null) {
            left.parent(this);
        }
        this.left = left;
        return this;
    }

    public Node right(Node right) {
        if (right != null) {
            right.parent(this);
        }
        this.right = right;
        return this;
    }

    private Node parent(Node parent) {
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
            if (left != null) {
                leftExpression = left.solve();
            }
            if (right != null) {
                rightExpression = right.solve();
            }
            solved = true;
            return ((Operator) expression).calculate(leftExpression, rightExpression);
        } else {
            return null;
        }
    }
}
