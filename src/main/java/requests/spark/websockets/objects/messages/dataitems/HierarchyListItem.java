package requests.spark.websockets.objects.messages.dataitems;

import org.json.JSONArray;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class HierarchyListItem extends JSONWeb {
    // OUTGOING
    @WSDataReference(WSData.HIERARCHY_LIST_ITEM_TITLE)
    private String title = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(HierarchyListItem.class)
    @WSDataReference(WSData.HIERARCHY_LIST_ITEM_CHILDREN)
    private JSONArray children = null;

    public HierarchyListItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONArray getChildren() {
        return children;
    }

    public void setChildren(JSONArray children) {
        this.children = children;
    }
}
