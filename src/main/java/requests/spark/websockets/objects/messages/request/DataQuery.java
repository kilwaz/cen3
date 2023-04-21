package requests.spark.websockets.objects.messages.request;

import clarity.Record;
import clarity.load.store.Records;
import requests.spark.websockets.objects.messages.dataitems.WebEntry;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.DataQueryData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.List;

@MessageType("DataQuery")
@WebSocketDataClass(DataQueryData.class)
public class DataQuery extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        DataQueryData dataQueryData = (DataQueryData) this.getWebSocketData();
        if(dataQueryData.getRecordToCheck() != null){
            Record record = Records.getInstance().findRecord(dataQueryData.getRecordToCheck());
            JSONArray dataQueryJSON = new JSONArray();
            if (record != null) {
                JSONArray requestedEntries = dataQueryData.getRequestedEntries();

                List<String> references = new ArrayList<>();
                for (int i = 0; i < requestedEntries.length(); i++) {
                    JSONObject jsonObject = requestedEntries.getJSONObject(i);
                    references.add(jsonObject.getString("_name"));
                }

                List<clarity.Entry> entries = record.get(references);
                for (clarity.Entry entry : entries) {
                    dataQueryJSON.put(new WebEntry(entry).prepareForJSON());
                }
            }

            dataQueryData.setEntries(dataQueryJSON);
        }
    }
}
