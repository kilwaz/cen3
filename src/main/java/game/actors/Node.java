package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class Node extends JSONWeb {
    @WSDataReference(WSData.NODE_VALUE)
    private String value;

    @WSDataReference(WSData.NODE_RIGHT)
    @WSDataTypeScriptClass(Node.class)
    private Node right = null;

    @WSDataReference(WSData.NODE_LEFT)
    @WSDataTypeScriptClass(Node.class)
    private Node left = null;

    public Node() {

    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
