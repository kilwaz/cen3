package requests.spark.websockets.objects.messages.request;

import game.actors.MenuItem;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.MenuData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("Menu")
@WebSocketDataClass(MenuData.class)
public class Menu extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        MenuData menuData = (MenuData) this.getWebSocketData();

        JSONArray menuItemJSON = new JSONArray();

        menuItemJSON.put(new MenuItem("dashboard", "Dashboard", "./assets/media/icons/duotune/general/gen025.svg").prepareForJSON());
        menuItemJSON.put(new MenuItem("builder", "Layout Builder", "./assets/media/icons/duotune/general/gen019.svg").prepareForJSON());
        menuItemJSON.put(new MenuItem("text-cases", "Text Cases", "./assets/media/icons/duotune/arrows/arr001.svg").prepareForJSON());

        menuData.setMenuItems(menuItemJSON);
        menuData.setUsername("WayHey!");
    }
}