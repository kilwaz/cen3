package requests.spark.websockets.objects.messages.request;

import requests.spark.websockets.objects.messages.dataitems.MenuItem;
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

        menuItemJSON.put(new MenuItem("Dashboard", "dashboard", "./assets/media/icons/duotune/general/gen025.svg", "menuItem").prepareForJSON());
        menuItemJSON.put(new MenuItem("Layout Builder", "builder", "./assets/media/icons/duotune/general/gen019.svg", "menuItem").prepareForJSON());
        menuItemJSON.put(new MenuItem("Separate", "separator").prepareForJSON());
        menuItemJSON.put(new MenuItem("Text Cases", "text-cases", "./assets/media/icons/duotune/arrows/arr001.svg", "menuItem").prepareForJSON());
        menuItemJSON.put(new MenuItem("Hierarchy", "hierarchy", "./assets/media/icons/duotune/arrows/arr001.svg", "menuItem").prepareForJSON());
        menuItemJSON.put(new MenuItem("Worksheet", "worksheet", "./assets/media/icons/duotune/arrows/arr001.svg", "menuItem").prepareForJSON());
        menuItemJSON.put(new MenuItem("Management", "management", "./assets/media/icons/duotune/arrows/arr001.svg", "menuItem").prepareForJSON());
        menuItemJSON.put(new MenuItem("Configuration", "configuration", "./assets/media/icons/duotune/arrows/arr001.svg", "menuItem").prepareForJSON());
        menuItemJSON.put(new MenuItem("Crafted", "separator").prepareForJSON());

        menuData.setMenuItems(menuItemJSON);
    }
}
