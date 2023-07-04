package requests.spark.websockets.objects.messages.request;

import clarity.Record;
import clarity.load.store.Records;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.WebRecordDataItem;
import requests.spark.websockets.objects.messages.dataobjects.SearchData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.List;

@MessageType("Search")
@WebSocketDataClass(SearchData.class)
public class Search extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        SearchData searchData = (SearchData) this.getWebSocketData();
        List<Record> searchResults = Records.getInstance().findRecords(searchData.getSearchItem(), searchData.getSearchValue());

        JSONArray searchResultsJSON = new JSONArray();
        for (Record record : searchResults) {
            WebRecordDataItem recordActor = new WebRecordDataItem();
            recordActor.setUuid(record.getUuid().toString());
            searchResultsJSON.put(recordActor.prepareForJSON());
        }

        searchData.setSearchResults(searchResultsJSON);
    }
}
