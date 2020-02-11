package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Entry;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class DataQueryData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String valueToCheck = null;

    @WSDataIncoming
    private String recordToCheck = null;

    // OUTGOING
    @WSDataOutgoing
    @WSDataTypeScriptClass(Entry.class)
    private Entry entry = null;

    public String getRecordToCheck() {
        return recordToCheck;
    }

    public void setRecordToCheck(String recordToCheck) {
        this.recordToCheck = recordToCheck;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public String getValueToCheck() {
        return valueToCheck;
    }

    public void setValueToCheck(String valueToCheck) {
        this.valueToCheck = valueToCheck;
    }
}
