package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.MenuItem;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class MenuData extends WebSocketData {
    @WSDataOutgoing
    private String username = null;

    // OUTGOING
    @WSDataJSONArrayClass(MenuItem.class) @WSDataOutgoing
    private JSONArray menuItems = null;

    public JSONArray getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(JSONArray menuItems) {
        this.menuItems = menuItems;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
