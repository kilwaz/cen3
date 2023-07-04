package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.HierarchyListDataItem;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class HierarchyData extends WebSocketData {
    // OUTGOING
    @WSDataOutgoing
    @WSDataJSONArrayClass(HierarchyListDataItem.class)
    private JSONArray hierarchyItems = null;

    public JSONArray getHierarchyItems() {
        return hierarchyItems;
    }

    public void setHierarchyItems(JSONArray hierarchyItems) {
        this.hierarchyItems = hierarchyItems;
    }
}
