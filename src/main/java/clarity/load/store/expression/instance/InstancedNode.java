package clarity.load.store.expression.instance;

import clarity.Record;
import clarity.load.store.expression.*;
import clarity.load.store.expression.operators.OperatorRepresentation;
import clarity.load.store.expression.values.Number;
import clarity.load.store.expression.values.*;
import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class InstancedNode {
    private static Logger log = AppLogger.logger();

    private InstancedNode left = null;
    private InstancedNode right = null;
    private InstancedNode parent = null;

    private int nodeType;

    private ArrayList<InstancedNode> nodeList = null;
    private Boolean referenceNode = false;

    private Boolean solved = false;

    private InstancedFormula instancedFormula;
    private Record record;
    private InstancedFormulaView instancedFormulaView;

    private Expression expression;
    private Expression solvedExpression;

    public InstancedNode(Expression expression, int nodeType) {
        this.expression = expression;
        this.nodeType = nodeType;
    }

    public InstancedNode instancedFormula(InstancedFormula instancedFormula) {
        this.instancedFormula = instancedFormula;

        // Pass the root instanced formula down to any children
        if (left != null) {
            left.instancedFormula(instancedFormula);
        }
        if (right != null) {
            right.instancedFormula(instancedFormula);
        }
        if (nodeList != null) {
            for (InstancedNode instancedNode : nodeList) {
                instancedNode.instancedFormula(instancedFormula);
            }
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

    public String getStringRepresentation() {
        String rightRepresentation = "";
        String listRepresentation = "";
        String leftRepresentation = "";
        if (right != null) {
            rightRepresentation += right.getStringRepresentation();
        }
        if (nodeList != null) {
            int commaCounter = 0;
            for (InstancedNode node : nodeList) {
                if (commaCounter > 0) {
                    listRepresentation += ",";
                }
                listRepresentation += node.getStringRepresentation();
                commaCounter++;
            }
        }
        if (left != null) {
            leftRepresentation += left.getStringRepresentation();
        }

        String representation;
        if (expression instanceof Function || expression instanceof AggFunction) {
            OperatorRepresentation operatorRepresentation = expression.getClass().getDeclaredAnnotation(OperatorRepresentation.class);
            representation = rightRepresentation + operatorRepresentation.formulaRepresentation().toLowerCase() + "(" + listRepresentation + ")" + leftRepresentation;
        } else if (expression instanceof Textual) {
            representation = rightRepresentation + "'" + expression.getStringRepresentation() + "'" + leftRepresentation;
        } else if (expression instanceof Operator) {
            representation = rightRepresentation + expression.getStringRepresentation() + leftRepresentation;
        } else if (expression instanceof Reference) {
            representation = rightRepresentation + "[" + ((Reference) expression).getValue().getName() + "]" + leftRepresentation;
        } else {
            representation = rightRepresentation + expression.getStringRepresentation() + leftRepresentation;
        }

        return representation;
    }

    public Expression solve() {
        try {
            if (expression instanceof Number || expression instanceof Textual || expression instanceof Bool || expression instanceof Evaluation) {
                solved = true;
                solvedExpression = expression;
                return solvedExpression;
            } else if (expression instanceof FormulaNode) {
                Formula formula = Formula.create(((FormulaNode) expression).getValue());
                InstancedFormula instancedFormula = formula.createInstance();
                instancedFormula.record(record);
                return solvedExpression = instancedFormula.solve();
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
            } else if (expression instanceof AggFunction) { // Aggregate functions
                solved = true;

                List<Record> records = getInstancedFormula().getRecords();

                ArrayList<Expression> expressions = new ArrayList<>();
                if (nodeList != null) {
                    StringBuilder formulaBuilder = new StringBuilder();
                    for (InstancedNode instancedNode : nodeList) { // Get just the expressions out of the instanced to pass on to the function
                        formulaBuilder.append(instancedNode.getStringRepresentation());
                    }

//                    log.info("Base formula => " + formulaBuilder.toString());

                    // Create the base formula
                    Formula formula = Formula.create(formulaBuilder.toString());

                    for (Record record : records) { // Loop through each record creating an instance and then passing in each record
                        InstancedFormula instancedFormula = formula.createInstance();
                        instancedFormula.record(record);

                        Expression result = instancedFormula.solve();

                        expressions.add(result); // Add each result into the aggregate function
                    }
                }

                solvedExpression = ((AggFunction) expression).apply(expressions);

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

    public InstancedNode referenceNode(Boolean referenceNode) {
        this.referenceNode = referenceNode;
        return this;
    }

    public Boolean getReferenceNode() {
        return referenceNode;
    }

    public InstancedNode record(Record record) {
        this.record = record;

        // Pass the root instanced formula down to any children
        if (left != null) {
            left.record(record);
        }
        if (right != null) {
            right.record(record);
        }
        if (nodeList != null) {
            for (InstancedNode instancedNode : nodeList) {
                instancedNode.record(record);
            }
        }

        return this;
    }

    public InstancedNode instancedFormulaView(InstancedFormulaView instancedFormulaView) {
        this.instancedFormulaView = instancedFormulaView;

        // Pass the root instanced formula down to any children
        if (left != null) {
            left.instancedFormulaView(instancedFormulaView);
        }
        if (right != null) {
            right.instancedFormulaView(instancedFormulaView);
        }
        if (nodeList != null) {
            for (InstancedNode instancedNode : nodeList) {
                instancedNode.instancedFormulaView(instancedFormulaView);
            }
        }

        return this;
    }
}
