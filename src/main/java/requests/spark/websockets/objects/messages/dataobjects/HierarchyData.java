package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.HierarchyListItem;
import requests.spark.websockets.objects.messages.dataitems.MenuItem;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class HierarchyData extends WebSocketData {
    // OUTGOING
    @WSDataOutgoing
    @WSDataJSONArrayClass(HierarchyListItem.class)
    private JSONArray hierarchyItems = null;

    public JSONArray getHierarchyItems() {
        return hierarchyItems;
    }

    public void setHierarchyItems(JSONArray hierarchyItems) {
        this.hierarchyItems = hierarchyItems;
    }
}
