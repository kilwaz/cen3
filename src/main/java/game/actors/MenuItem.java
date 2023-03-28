package game.actors;

import org.json.JSONArray;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class MenuItem extends JSONWeb {
    // OUTGOING
    @WSDataReference(WSData.MENU_ITEM_ROUTELINK)
    private String routeLink = null;
    @WSDataReference(WSData.MENU_ITEM_TITLE)
    private String title = null;
    @WSDataReference(WSData.MENU_ITEM_ICON)
    private String icon = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(MenuItem.class)
    private JSONArray children = null;

    public MenuItem(String routeLink, String title, String icon) {
        this.routeLink = routeLink;
        this.title = title;
        this.icon = icon;
    }

    public String getRouteLink() {
        return routeLink;
    }

    public void setRouteLink(String routeLink) {
        this.routeLink = routeLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public JSONArray getChildren() {
        return children;
    }

    public void setChildren(JSONArray children) {
        this.children = children;
    }
}
