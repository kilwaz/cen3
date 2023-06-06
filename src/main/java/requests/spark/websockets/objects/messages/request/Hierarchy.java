package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.HierarchyListItem;
import requests.spark.websockets.objects.messages.dataobjects.HierarchyData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("Hierarchy")
@WebSocketDataClass(HierarchyData.class)
public class Hierarchy extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        HierarchyData hierarchyData = (HierarchyData) this.getWebSocketData();
        JSONArray hierarchyJSON = new JSONArray();

        HierarchyListItem childItem = new HierarchyListItem("With child");
        JSONArray children = new JSONArray();
        HierarchyListItem childItem2 = new HierarchyListItem("A child");

        JSONArray children2 = new JSONArray();
        children2.put(new HierarchyListItem("A child 2").prepareForJSON());
        childItem2.setChildren(children2);

        children.put(childItem2.prepareForJSON());
        childItem.setChildren(children);


        hierarchyJSON.put(childItem.prepareForJSON());


        hierarchyJSON.put(new HierarchyListItem("Item 1a").prepareForJSON());
        hierarchyJSON.put(new HierarchyListItem("Item 2b").prepareForJSON());

        hierarchyData.setHierarchyItems(hierarchyJSON);
    }
}
