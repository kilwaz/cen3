package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.ArrayList;
import java.util.List;

public class NodeDataItem extends JSONWeb {
    @WSDataReference()
    private String value;

    @WSDataReference()
    @WSDataTypeScriptClass(NodeDataItem.class)
    private NodeDataItem right = null;

    @WSDataReference()
    @WSDataTypeScriptClass(NodeDataItem.class)
    private NodeDataItem left = null;

    @WSDataReference()
    @WSDataTypeScriptClass(NodeDataItem.class)
    private List<NodeDataItem> nodeList = null;

    @WSDataReference()
    private Integer nodeType;

    public NodeDataItem() {
    }

    public NodeDataItem getRight() {
        return right;
    }

    public void setRight(NodeDataItem right) {
        this.right = right;
    }

    public NodeDataItem getLeft() {
        return left;
    }

    public void setLeft(NodeDataItem left) {
        this.left = left;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<NodeDataItem> getNodeList() {
        return nodeList;
    }

    public void addToList(NodeDataItem nodeToAdd) {
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
