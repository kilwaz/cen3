package requests.spark.websockets.objects.messages.request;

import clarity.Entry;
import clarity.Record;
import clarity.definition.Definitions;
import clarity.definition.RecordState;
import clarity.definition.WorksheetConfig;
import data.model.DatabaseCollect;
import data.model.dao.WorksheetConfigDAO;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.WebRecord;
import requests.spark.websockets.objects.messages.dataitems.WebWorksheetConfig;
import requests.spark.websockets.objects.messages.dataobjects.WorksheetData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MessageType("Worksheet")
@WebSocketDataClass(WorksheetData.class)
public class Worksheet extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        WorksheetData worksheetData = (WorksheetData) this.getWebSocketData();
        log.info("This is what was got from client: " + worksheetData.getRequestID());

        WorksheetConfigDAO worksheetConfigDAO = new WorksheetConfigDAO();
        List<WorksheetConfig> webWorksheetConfigs = worksheetConfigDAO.getAllWorksheetConfigs();
        List<String> includeColumnCompareReference = new ArrayList<>();

        List<WebWorksheetConfig> webWorksheetWebConfigs = new ArrayList<>();
        for (WorksheetConfig worksheetConfig : webWorksheetConfigs) {
            webWorksheetWebConfigs.add(worksheetConfig.getAsWebWorksheetConfig());
            includeColumnCompareReference.add(worksheetConfig.getDefinition().getUuidString());
        }

        worksheetData.setWorksheetConfig(webWorksheetWebConfigs);

        List<Record> empRecords = DatabaseCollect
                .create()
                .recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"))
                .state(RecordState.STATIC)
                .collect();

//        int count = 0;
        List<WebRecord> worksheetRecords = new ArrayList<>();
        for (Record record : empRecords) {
            WebRecord webRecord = new WebRecord();
            webRecord.setUuid(record.getUuid().toString());

            Entry[] entriesToShow = new Entry[includeColumnCompareReference.size()];
            for (Entry entry : record.getEntries()) {
                if (includeColumnCompareReference.contains(entry.getDefinition().getUuidString())) {
                    entriesToShow[includeColumnCompareReference.indexOf(entry.getDefinition().getUuidString())] = entry;
                }
            }

            webRecord.setEntriesFromClarity(Arrays.asList(entriesToShow));

            worksheetRecords.add(webRecord);
//            count++;
//            if (count > 5) {
//                break;
//            }
        }

        worksheetData.setWorksheetRecords(worksheetRecords);
    }
}
