package clarity.load.store.expression;

import clarity.load.store.expression.values.Number;
import clarity.load.store.expression.values.Reference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

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

    public Node expression(Expression expression) {
        this.expression = expression;
        return this;
    }

    public Expression getExpression() {
        return expression;
    }

    public Expression solve() {
        if (expression instanceof Number) {
            solved = true;
            return expression;
        } else if (expression instanceof Reference) {
            // References should be replaced with the actual formula, right?
            //solved = true;
            //return ((Reference) expression).getValue();
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
        }

        return null;
    }

    public Node duplicate() {
        try {
            Expression expression = this.getExpression();

            Expression newExpression = null;

            Class<?> expressionClass = expression.getClass();
            Class<?>[] interfaces = expressionClass.getInterfaces();
            List<Class<?>> list = Arrays.asList(interfaces);
            if (list.contains(Value.class)) {
                Value expressionValue = (Value) expression;
                Constructor<?> ctor = expression.getClass().getConstructor(expressionValue.getValue().getClass());
                newExpression = (Expression) ctor.newInstance(expressionValue.getValue());
            } else {
                Constructor<?> ctor = expression.getClass().getConstructor();
                newExpression = (Expression) ctor.newInstance();
            }


            Node duplicatedNode = new Node(newExpression);

            if (right != null) {
                duplicatedNode.right(right.duplicate());
            }
            if (left != null) {
                duplicatedNode.left(left.duplicate());
            }
            return duplicatedNode;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
