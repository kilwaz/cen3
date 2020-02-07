package clarity.load.store.expression.instance;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Function;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Evaluation;
import clarity.load.store.expression.values.Number;
import clarity.load.store.expression.values.Reference;
import clarity.load.store.expression.values.Textual;
import error.Error;
import log.AppLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class InstancedNode {
    private static Logger log = AppLogger.logger();

    private InstancedNode left = null;
    private InstancedNode right = null;
    private InstancedNode parent = null;

    private int nodeType;

    private ArrayList<InstancedNode> nodeList = null;

    private Boolean solved = false;

    private InstancedFormula instancedFormula;

    private Expression expression;
    private Expression solvedExpression;

    public InstancedNode(Expression expression, int nodeType) {
        this.expression = expression;
        this.nodeType = nodeType;
    }

    public InstancedNode instancedFormula(InstancedFormula instancedFormula) {
        this.instancedFormula = instancedFormula;

        //TODO: What is this bit doing?
        if (left != null) {
            left.instancedFormula(instancedFormula);
        }
        if (right != null) {
            right.instancedFormula(instancedFormula);
        }
        return this;
    }

    public InstancedNode left(InstancedNode left) {
        if (left != null) {
            left.parent(this);
        }
        this.left = left;
        return this;
    }

    public InstancedNode right(InstancedNode right) {
        if (right != null) {
            right.parent(this);
        }
        this.right = right;
        return this;
    }

    public InstancedNode parent(InstancedNode parent) {
        this.parent = parent;
        return this;
    }

    public InstancedNode nodeType(int nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public InstancedNode addToList(InstancedNode listNode) {
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
        nodeList.add(listNode);
        return this;
    }

    public InstancedNode getLeft() {
        return left;
    }

    public InstancedNode getRight() {
        return right;
    }

    public InstancedNode getParent() {
        return parent;
    }

    public InstancedNode solvedExpression(Expression solvedExpression) {
        this.solvedExpression = solvedExpression;
        return this;
    }

    public InstancedNode expression(Expression expression) {
        this.expression = expression;
        return this;
    }

    public Expression getExpression() {
        return expression;
    }

    public Expression solve() {
        try {
            if (expression instanceof Number || expression instanceof Textual || expression instanceof Evaluation) {
                solved = true;
                solvedExpression = expression;
                return solvedExpression;
            } else if (expression instanceof Reference) {
                instancedFormula.substituteRecordValues(this);
            } else if (expression instanceof Operator || expression instanceof Function) {
                solved = true;
                if (expression instanceof Operator) {
                    Expression rightExpression = null;
                    Expression leftExpression = null;
                    if (left != null) {
                        leftExpression = left.solve();
                    }
                    if (right != null) {
                        rightExpression = right.solve();
                    }

                    solvedExpression = ((Operator) expression).calculate(leftExpression, rightExpression);
                } else {
                    ArrayList<Expression> expressions = new ArrayList<>();
                    for (InstancedNode instancedNode : nodeList) { // Get just the expressions out of the instanced to pass on to the function
                        Expression expression = instancedNode.getExpression();
                        if (!instancedNode.solved) { // Solve any function parameters fully before applying the function
                            expression = instancedNode.solve();
                        }
                        expressions.add(expression);
                    }
                    solvedExpression = ((Function) expression).apply(expressions);
                }
                return solvedExpression;
            }
        } catch (Exception ex) {
            Error.CLARITY_SOLVE_EXPRESSION.record()
                    .additionalInformation("Expression = " + expression.getStringRepresentation())
                    .create(ex);
        }

        log.info("Returning null for a solve " + instancedFormula.getBaseFormula() + "  -  Specific expression " + expression.getStringRepresentation());

        return null;
    }

    public int getNodeType() {
        return nodeType;
    }

    public ArrayList<InstancedNode> getNodeList() {
        return nodeList;
    }

    public Expression getSolvedExpression() {
        return solvedExpression;
    }

    public InstancedFormula getInstancedFormula() {
        return instancedFormula;
    }
}
