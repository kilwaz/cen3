package requests.spark.websockets.objects.messages.request;

import clarity.Record;
import clarity.definition.Definitions;
import clarity.definition.RecordState;
import data.model.DatabaseCollect;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.WebRecord;
import requests.spark.websockets.objects.messages.dataobjects.WorksheetData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.List;

@MessageType("Worksheet")
@WebSocketDataClass(WorksheetData.class)
public class Worksheet extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        WorksheetData worksheetData = (WorksheetData) this.getWebSocketData();
        log.info("This is what was got from client: " + worksheetData.getRequestID());

        List<Record> empRecords = DatabaseCollect
                .create()
                .recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"))
                .state(RecordState.STATIC)
//                .primaryKey(worksheetData.getRequestID())
                .collect();

        int count = 0;
        List<WebRecord> worksheetRecords = new ArrayList<>();
        for (Record record : empRecords) {
            WebRecord webRecord = new WebRecord();
            webRecord.setUuid(record.getUuid().toString());
            webRecord.setEntriesFromClarity(record.getEntries());
            worksheetRecords.add(webRecord);
//            count++;
//            if (count > 120) {
//                break;
//            }
        }

        worksheetData.setWorksheetRecords(worksheetRecords);
    }
}
