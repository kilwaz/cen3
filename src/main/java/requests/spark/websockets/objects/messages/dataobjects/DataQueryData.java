package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class DataQueryData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String recordToCheck = null;

    @WSDataIncoming
    private String value = null;

    // OUTGOING
    @WSDataOutgoing
    private String result = null;

    public String getRecordToCheck() {
        return recordToCheck;
    }

    public void setRecordToCheck(String recordToCheck) {
        this.recordToCheck = recordToCheck;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
