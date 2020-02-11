package requests.spark.websockets.objects.messages.request;

import clarity.Record;
import clarity.load.store.Records;
import game.actors.Entry;
import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.DataQueryData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("DataQuery")
@WebSocketDataClass(DataQueryData.class)
public class DataQuery extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        DataQueryData dataQueryData = (DataQueryData) this.getWebSocketData();

        Record record = Records.getInstance().findRecord();

        String recordToCheck = dataQueryData.getRecordToCheck();
        if (recordToCheck != null) {
            clarity.Entry entryClarity = record.get(recordToCheck);

            Entry entry = new Entry();
            entry.setUuid(entryClarity.getUuid().toString());
            entry.setValue(entryClarity.get().getValue().toString());

            dataQueryData.setEntry(entry);
        }
    }
}
