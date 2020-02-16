package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Entry;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class DataQueryData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String valueToCheck = null;

    @WSDataIncoming
    private String recordToCheck = null;

    // OUTGOING
    @WSDataOutgoing
    @WSDataJSONArrayClass(Entry.class)
    private JSONArray entries = null;

    public String getRecordToCheck() {
        return recordToCheck;
    }

    public void setRecordToCheck(String recordToCheck) {
        this.recordToCheck = recordToCheck;
    }

    public JSONArray getEntries() {
        return entries;
    }

    public void setEntries(JSONArray entries) {
        this.entries = entries;
    }

    public String getValueToCheck() {
        return valueToCheck;
    }

    public void setValueToCheck(String valueToCheck) {
        this.valueToCheck = valueToCheck;
    }
}
