package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.ArrayList;
import java.util.List;

public class Node extends JSONWeb {
    @WSDataReference(WSData.NODE_VALUE)
    private String value;

    @WSDataReference(WSData.NODE_RIGHT)
    @WSDataTypeScriptClass(Node.class)
    private Node right = null;

    @WSDataReference(WSData.NODE_LEFT)
    @WSDataTypeScriptClass(Node.class)
    private Node left = null;

    @WSDataReference(WSData.NODE_PRECEDENCE)
    private Integer precedence = 0;

    @WSDataReference(WSData.NODE_LIST)
    @WSDataTypeScriptClass(Node.class)
    private List<Node> nodeList = null;

    @WSDataReference(WSData.NODE_TYPE)
    private Integer nodeType;

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

    public Integer getPrecedence() {
        return precedence;
    }

    public void setPrecedence(Integer precedence) {
        this.precedence = precedence;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void addToList(Node nodeToAdd) {
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
        nodeList.add(nodeToAdd);
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }
}
