package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.MenuItem;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class MenuData extends WebSocketData {
    // OUTGOING
    @WSDataJSONArrayClass(MenuItem.class) @WSDataOutgoing
    private JSONArray menuItems = null;

    public JSONArray getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(JSONArray menuItems) {
        this.menuItems = menuItems;
    }
}
