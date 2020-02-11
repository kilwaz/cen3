package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Record;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class SearchData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String searchItem = null;

    @WSDataIncoming
    private String searchValue = null;

    // OUTGOING
    @WSDataOutgoing
    @WSDataTypeScriptClass(Record.class)
    private Record record = null;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
