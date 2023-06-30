package requests.spark.websockets.objects.messages.dataobjects;

import org.json.JSONArray;
import requests.spark.websockets.objects.messages.dataitems.DefinitionDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class DefinitionLookupData extends WebSocketData {
    @WSDataOutgoing @WSDataIncoming
    private String name = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(DefinitionDataItem.class)
    private JSONArray entries = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getEntries() {
        return entries;
    }

    public void setEntries(JSONArray entries) {
        this.entries = entries;
    }
}
