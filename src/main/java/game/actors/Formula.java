package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class Formula extends JSONWeb {
//    @WSDataReference(WSData.PLAYER_UUID)
//    private UUID uuid;
//    @WSDataReference(WSData.PLAYER_ID)
//    private Integer id;
//    @WSDataReference(WSData.PLAYER_NAME)
//    private String name;

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
