package requests.spark.websockets.objects.messages.request;

import clarity.Record;
import clarity.load.store.Records;
import game.actors.Entry;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.json.JSONArray;
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
        Record record = Records.getInstance().findRecord(dataQueryData.getRecordToCheck());
        JSONArray dataQueryJSON = new JSONArray();

        if (record != null) {
            List<String> references = new ArrayList<>();
            references.add("Sum");
            references.add("Num");
            references.add("ID");

            List<clarity.Entry> entries = record.get(references);

            for (clarity.Entry entry : entries) {
                Entry entryActor = new Entry();
                entryActor.setUuid(entry.getUuid().toString());
                entryActor.setValue(entry.get().getValue().toString());
                entryActor.setRecordUUID(record.getUuid().toString());
                entryActor.setName(entry.getReference());

                dataQueryJSON.put(entryActor.prepareForJSON());
            }
        }

        dataQueryData.setEntries(dataQueryJSON);
    }
}
