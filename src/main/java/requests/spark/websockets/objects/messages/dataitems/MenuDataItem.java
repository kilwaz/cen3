package requests.spark.websockets.objects.messages.dataitems;

import org.json.JSONArray;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class MenuDataItem extends JSONWeb {
    // OUTGOING
    @WSDataReference()
    private String routeLink = null;
    @WSDataReference()
    private String title = null;
    @WSDataReference()
    private String icon = null;
    @WSDataReference()
    private String type = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(MenuDataItem.class)
    private JSONArray children = null;

    public MenuDataItem(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public MenuDataItem(String title, String routeLink, String icon, String type) {
        this.routeLink = routeLink;
        this.title = title;
        this.icon = icon;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONArray getChildren() {
        return children;
    }

    public void setChildren(JSONArray children) {
        this.children = children;
    }
}
