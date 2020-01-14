package clarity.load.store.expression.instance;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Operator;
import clarity.load.store.expression.values.Number;
import clarity.load.store.expression.values.Reference;
import log.AppLogger;
import org.apache.log4j.Logger;

public class InstancedNode {
    private static Logger log = AppLogger.logger();

    private InstancedNode left = null;
    private InstancedNode right = null;
    private InstancedNode parent = null;

    private Boolean solved = false;

    private InstancedFormula instancedFormula;

    private Expression expression;

    public InstancedNode(Expression expression) {
        this.expression = expression;
    }

    public InstancedNode instancedFormula(InstancedFormula instancedFormula) {
        this.instancedFormula = instancedFormula;
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

    public InstancedNode getLeft() {
        return left;
    }

    public InstancedNode getRight() {
        return right;
    }

    public InstancedNode getParent() {
        return parent;
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
            if (expression instanceof Number) {
                solved = true;
                return expression;
            } else if (expression instanceof Reference) {
                instancedFormula.substituteRecordValues(this);
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
        } catch (Exception ex) {
            log.info("Error message in solve..", ex);
        }

        log.info("Returning null for a solve " + instancedFormula.getBaseFormula() + "  -  Specific expression " + expression.getStringRepresentation());

        return null;
    }
}
