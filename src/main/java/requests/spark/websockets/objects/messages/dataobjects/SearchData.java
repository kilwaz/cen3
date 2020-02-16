package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Record;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class SearchData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String searchItem = null;

    @WSDataIncoming
    private String searchValue = null;

    // OUTGOING
    @WSDataOutgoing
    @WSDataJSONArrayClass(Record.class)
    private JSONArray searchResults = null;

    public JSONArray getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(JSONArray searchResults) {
        this.searchResults = searchResults;
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
