package game.actors;

import clarity.load.store.expression.values.Reference;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class Formula extends JSONWeb {
    @WSDataReference(WSData.FORMULA_NODE)
    @WSDataTypeScriptClass(Node.class)
    private Node rootNode = null;

    @WSDataReference(WSData.FORMULA_STR_EXPRESSION)
    private String strExpression = "";

    public Formula() {
    }

    public void convertClarityNode(clarity.load.store.expression.Formula formula) {
        strExpression = formula.getStrExpression();
        rootNode = processClarityNode(formula.getRoot());
    }

    private Node processClarityNode(clarity.load.store.expression.Node node) {
        if (node.getExpression() instanceof Reference) { // If node is a reference, skip straight to it
            return processClarityNode(((Reference) node.getExpression()).getValue().getFormula().getRoot());
        } else {
            Node newNode = new Node();
            newNode.setValue(node.getExpression().getStringRepresentation() + " " + node.getExpression());
            newNode.setPrecedence(node.getExpression().getPrecedence());
            newNode.setNodeType(node.getNodeType());

            if (newNode.getNodeType() == clarity.load.store.expression.Node.NODE_CHILD_TYPE_BINARY) {
                if (node.getLeft() != null) {
                    newNode.setLeft(processClarityNode(node.getLeft()));
                }
                if (node.getRight() != null) {
                    newNode.setRight(processClarityNode(node.getRight()));
                }
            } else {
                if (node.getNodeList() != null) {
                    for (clarity.load.store.expression.Node listNode : node.getNodeList()) {
                        newNode.addToList(processClarityNode(listNode));
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
