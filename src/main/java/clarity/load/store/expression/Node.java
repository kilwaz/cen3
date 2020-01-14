package clarity.load.store.expression;

import clarity.load.store.expression.instance.InstancedNode;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Node {
    private static Logger log = AppLogger.logger();

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

    public InstancedNode duplicate() {
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


            InstancedNode duplicatedNode = new InstancedNode(newExpression);

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
