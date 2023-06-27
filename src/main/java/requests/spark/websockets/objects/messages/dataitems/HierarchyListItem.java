package requests.spark.websockets.objects.messages.dataitems;

import org.json.JSONArray;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class HierarchyListItem extends JSONWeb {
    // OUTGOING
    @WSDataOutgoing
    @WSDataReference(WSData.HIERARCHY_LIST_ITEM_TITLE)
    private String title = null;

    @WSDataOutgoing
    @WSDataReference(WSData.HIERARCHY_LIST_ITEM_NODE_REFERENCE)
    private String nodeReference = null;

    @WSDataOutgoing
    @WSDataReference(WSData.HIERARCHY_LIST_ITEM_EXPANDED)
    private Boolean expanded = false;

    @WSDataOutgoing
    @WSDataJSONArrayClass(String.class)
    @WSDataReference(WSData.HIERARCHY_LIST_ITEM_CHILDREN_ID)
    private JSONArray childrenIds = null;

    public HierarchyListItem(String title, String nodeReference) {
        this.title = title;
        this.nodeReference = nodeReference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNodeReference() {
        return nodeReference;
    }

    public void setNodeReference(String nodeReference) {
        this.nodeReference = nodeReference;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public JSONArray getChildrenIds() {
        return childrenIds;
    }

    public void setChildrenIds(JSONArray childrenIds) {
        this.childrenIds = childrenIds;
    }
}
