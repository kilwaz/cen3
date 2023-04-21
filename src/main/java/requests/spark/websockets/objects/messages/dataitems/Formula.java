package requests.spark.websockets.objects.messages.dataitems;

import clarity.load.store.expression.Expression;
import clarity.load.store.expression.instance.InstancedFormula;
import clarity.load.store.expression.instance.InstancedNode;
import clarity.load.store.expression.values.Evaluation;
import clarity.load.store.expression.values.Number;
import clarity.load.store.expression.values.Textual;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataIgnore;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class Formula extends JSONWeb {
    @WSDataIgnore
    private static Logger log = AppLogger.logger();

    @WSDataReference(WSData.FORMULA_NODE)
    @WSDataTypeScriptClass(Node.class)
    private Node rootNode = null;

    @WSDataReference(WSData.FORMULA_STR_EXPRESSION)
    private String strExpression = "";

    public Formula() {
    }

    public void convertClarityNode(InstancedFormula instancedFormula) {
        strExpression = instancedFormula.getBaseFormula();
        rootNode = convertToClarityUINode(instancedFormula.getInstancedRoot());
    }

    private Node convertToClarityUINode(InstancedNode instancedNode) {
        if (instancedNode.getReferenceNode() && instancedNode.getInstancedFormula() != null) { // If node is a reference with instanced formula, skip straight to it
            return convertToClarityUINode(instancedNode.getInstancedFormula().getInstancedRoot());
        } else {
            Node newNode = new Node();

            Expression expression = instancedNode.getExpression();
            if (expression instanceof Number || expression instanceof Textual || expression instanceof Evaluation) {
                newNode.setValue(expression.getStringRepresentation());
            } else {
                newNode.setValue(expression.getStringRepresentation() + " " + instancedNode.getSolvedExpression().getStringRepresentation());
            }

            newNode.setNodeType(instancedNode.getNodeType());

            if (newNode.getNodeType() == clarity.load.store.expression.Node.NODE_CHILD_TYPE_BINARY) {
                if (instancedNode.getLeft() != null) {
                    newNode.setLeft(convertToClarityUINode(instancedNode.getLeft()));
                }
                if (instancedNode.getRight() != null) {
                    newNode.setRight(convertToClarityUINode(instancedNode.getRight()));
                }
            } else {
                if (instancedNode.getNodeList() != null) {
                    for (InstancedNode listNode : instancedNode.getNodeList()) {
                        newNode.addToList(convertToClarityUINode(listNode));
                    }
                }
            }

            return newNode;
        }
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public String getStrExpression() {
        return strExpression;
    }

    public void setStrExpression(String strExpression) {
        this.strExpression = strExpression;
    }
}
