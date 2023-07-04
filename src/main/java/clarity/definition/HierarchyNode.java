package clarity.definition;

import data.model.DatabaseObject;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.dataitems.HierarchyListDataItem;

import java.util.ArrayList;
import java.util.List;

public class HierarchyNode extends DatabaseObject {

    private String nodeReference = "";
    private String nodeName = "";
    private String parentReference = "";
    private String pathFromTop = "";
    private String nodeType = "";

    private List<HierarchyNode> children = new ArrayList<>();

    public HierarchyNode() {
        super();
    }

    public HierarchyNode(String nodeReference, String nodeName, String parentReference, String pathFromTop, String nodeType) {
        this.nodeReference = nodeReference;
        this.nodeName = nodeName;
        this.parentReference = parentReference;
        this.pathFromTop = pathFromTop;
        this.nodeType = nodeType;
    }

    public String getNodeReference() {
        return nodeReference;
    }

    public HierarchyNode nodeReference(String nodeReference) {
        this.nodeReference = nodeReference;
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public HierarchyNode nodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public String getParentReference() {
        return parentReference;
    }

    public HierarchyNode parentReference(String parentReference) {
        this.parentReference = parentReference;
        return this;
    }

    public String getPathFromTop() {
        return pathFromTop;
    }

    public HierarchyNode pathFromTop(String pathFromTop) {
        this.pathFromTop = pathFromTop;
        return this;
    }

    public String getNodeType() {
        return nodeType;
    }

    public HierarchyNode nodeType(String nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public List<HierarchyNode> getChildren() {
        return children;
    }

    public void addChild(HierarchyNode child) {
        this.children.add(child);
    }

    public HierarchyNode children(List<HierarchyNode> children) {
        this.children = children;
        return this;
    }

    public HierarchyListDataItem getAsHierarchyListItem(Boolean followChildren) {
        HierarchyListDataItem hierarchyListItem = new HierarchyListDataItem(this.nodeName, this.nodeReference);

        if (this.children.size() > 0) {
            JSONArray children = new JSONArray();
            for (HierarchyNode hierarchyNode : this.children) {
                if (followChildren) {
                    children.put(hierarchyNode.getAsHierarchyListItem(followChildren).prepareForJSON());
                } else {
                    children.put(hierarchyNode.getNodeReference());
                }
            }

            if (followChildren) {
//                hierarchyListItem.setChildren(children);
            } else {
                hierarchyListItem.setChildrenIds(children);
            }
        }

        return hierarchyListItem;
    }
}
