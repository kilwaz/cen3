package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class Formula extends JSONWeb {
    @WSDataReference(WSData.FORMULA_NODE)
    @WSDataTypeScriptClass(Node.class)
    private Node rootNode = null;

    public Formula() {

    }

    public void convertClarityNode(clarity.load.store.expression.Node node) {
        rootNode = processClarityNode(node);
    }

    private Node processClarityNode(clarity.load.store.expression.Node node) {
        Node newNode = new Node();
        newNode.setValue(node.getExpression().getStringRepresentation());
        newNode.setPrecedence(node.getExpression().getPrecedence());
        if (node.getLeft() != null) {
            newNode.setLeft(processClarityNode(node.getLeft()));
        }
        if (node.getRight() != null) {
            newNode.setRight(processClarityNode(node.getRight()));
        }

        return newNode;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }
}
