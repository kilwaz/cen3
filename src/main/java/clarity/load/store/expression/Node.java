package clarity.load.store.expression;

import clarity.load.store.expression.instance.InstancedNode;
import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
    private static Logger log = AppLogger.logger();

    public static final int NODE_CHILD_TYPE_BINARY = 1;
    public static final int NODE_CHILD_TYPE_VARIABLE = 2;

    private Node left = null;
    private Node right = null;
    private Node parent = null;

    private int nodeType;

    private ArrayList<Node> nodeList = null;
    private Expression expression;

    public Node(Expression expression, int nodeType) {
        this.expression = expression;
        this.nodeType = nodeType;
    }

    public Node left(Node left) {
        if (nodeType != NODE_CHILD_TYPE_BINARY) {
            Error.CLARITY_INCORRECT_CHILD_ASSIGNMENT_TO_NODE_TYPE.record()
                    .additionalInformation("Tried to assign left child to non binary node")
                    .create();
        }
        if (left != null) {
            left.parent(this);
        }
        this.left = left;
        return this;
    }

    public Node right(Node right) {
        if (nodeType != NODE_CHILD_TYPE_BINARY) {
            Error.CLARITY_INCORRECT_CHILD_ASSIGNMENT_TO_NODE_TYPE.record()
                    .additionalInformation("Tried to assign right child to non binary node")
                    .create();
        }
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

    public Node addToList(Node nodeToAdd) {
        if (nodeType != NODE_CHILD_TYPE_VARIABLE) {
            Error.CLARITY_INCORRECT_CHILD_ASSIGNMENT_TO_NODE_TYPE.record()
                    .additionalInformation("Tried to assign list child to non variable node")
                    .create();
        }
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
        nodeList.add(nodeToAdd);
        if (nodeToAdd != null) {
            nodeToAdd.parent(this);
        }
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

            Expression newExpression;

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

            InstancedNode duplicatedNode = null;
            if (nodeType == NODE_CHILD_TYPE_BINARY) {
                duplicatedNode = new InstancedNode(newExpression, NODE_CHILD_TYPE_BINARY);
                if (right != null) {
                    duplicatedNode.right(right.duplicate());
                }
                if (left != null) {
                    duplicatedNode.left(left.duplicate());
                }
            } else if (nodeType == NODE_CHILD_TYPE_VARIABLE) {
                duplicatedNode = new InstancedNode(newExpression, NODE_CHILD_TYPE_VARIABLE);
                for (Node nodeItem : nodeList) {
                    duplicatedNode.addToList(nodeItem.duplicate());
                }
            }

            return duplicatedNode;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
            Error.CLARITY_DUPLICATE.record().create(ex);
        }

        return null;
    }

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public Node nodeType(int nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public int getNodeType() {
        return nodeType;
    }
}
