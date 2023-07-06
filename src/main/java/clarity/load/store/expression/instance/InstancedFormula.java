package clarity.load.store.expression.instance;

import clarity.Record;
import clarity.definition.Definition;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.Node;
import clarity.load.store.expression.values.Number;
import clarity.load.store.expression.values.Reference;
import clarity.load.store.expression.values.ReferenceView;
import log.AppLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class InstancedFormula {
    private static Logger log = AppLogger.logger();

    private Record record;
    private InstancedFormulaView instancedFormulaView;
    private InstancedNode instancedRoot = null;
    private String baseFormula;
    private int type = 0; // Explain these types 0 and 1, 0 is normal formula, 1 is Agg formula
    private List<Record> records = new ArrayList<>();

    public InstancedFormula(Formula formula) {
        this.instancedRoot = formula.getRoot().duplicate().instancedFormula(this);
        this.baseFormula = formula.getStrExpression();
    }

    public void substituteRecordValues(InstancedNode node) {
        Expression expression = node.getExpression();
        if (expression instanceof Reference || expression instanceof ReferenceView) {
            if (type == 1) {
                node.referenceNode(true);
                node.expression(expression);
            } else {
                Definition definition = null;
                Boolean formulaNeeded = false;
                String reference = null;
                Formula formula = null;
                if (expression instanceof Reference) {
                    definition = ((Reference) expression).getValue();
                    formulaNeeded = definition.isCalculated();
                    reference = definition.getName();
                    formula = definition.getFormula();
                } else if (expression instanceof ReferenceView) {
                    reference = ((ReferenceView) expression).getValue();
                    formulaNeeded = false;
                    node.expression(instancedFormulaView.get(reference));
                }

                node.referenceNode(true);
                if (formulaNeeded) {
                    InstancedFormula instancedFormula = formula
                            .createInstance()
                            .record(this.record)
                            .instancedFormulaView(instancedFormulaView);

                    Expression solvedExpression = instancedFormula.solve();
                    node.instancedFormula(instancedFormula);
                    node.expression(solvedExpression);
                } else {
                    if (expression instanceof Reference) {
                        if (record.get(reference) != null) {
                            if (record != null) {
                                node.expression(record.get(reference).get().toExpression());
                            } else if (instancedFormulaView != null) {
                                node.expression(instancedFormulaView.get(""));
                            } else {
                                throw new NullPointerException("Cannot find reference - Null");
                            }
                        } else { // If node cannot be found as it is probably null
                            node.expression(new Number(0));
                        }
                    }
                }
            }
        }

        if (node.getNodeType() == Node.NODE_CHILD_TYPE_BINARY) {
            if (node.getRight() != null) {
                substituteRecordValues(node.getRight());
            }
            if (node.getLeft() != null) {
                substituteRecordValues(node.getLeft());
            }
        } else {
            if (node.getNodeList() != null) {
                for (InstancedNode instancedNode : node.getNodeList()) {
                    substituteRecordValues(instancedNode);
                }
            }
        }
    }

    public Expression solve() {
        if (instancedRoot != null) {
            if (type == 0) {
                substituteRecordValues(instancedRoot);
            }
            return instancedRoot.solve();
        }

        return null;
    }

    public InstancedFormula record(Record record) {
        this.record = record;
        instancedRoot.record(record);
        return this;
    }

    public InstancedFormula records(List<Record> records) {
        this.records = records;
        return this;
    }

    public InstancedFormula type(int type) {
        this.type = type;
        return this;
    }

    public InstancedFormula instancedFormulaView(InstancedFormulaView instancedFormulaView) {
        this.instancedFormulaView = instancedFormulaView;
        return this;
    }

    public InstancedNode getInstancedRoot() {
        return instancedRoot;
    }

    public String getBaseFormula() {
        return baseFormula;
    }

    public Record getRecord() {
        return record;
    }

    public List<Record> getRecords() {
        return records;
    }

    public String getStringRepresentation() {
        return instancedRoot.getStringRepresentation();
    }
}
