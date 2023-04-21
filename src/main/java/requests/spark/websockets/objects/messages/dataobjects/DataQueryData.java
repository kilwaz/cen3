package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.WebEntry;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class DataQueryData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    @WSDataJSONArrayClass(WebEntry.class)
    private JSONArray requestedEntries = null;

    @WSDataIncoming
    private String recordToCheck = null;

    // OUTGOING
    @WSDataOutgoing
    @WSDataJSONArrayClass(WebEntry.class)
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

    public JSONArray getRequestedEntries() {
        return requestedEntries;
    }

    public void setRequestedEntries(JSONArray requestedEntries) {
        this.requestedEntries = requestedEntries;
    }
}
