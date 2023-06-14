package requests.spark.websockets.objects.messages.request;

import clarity.Entry;
import clarity.Infer;
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
import requests.spark.websockets.objects.messages.dataobjects.UpdateData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MessageType("Update")
@WebSocketDataClass(UpdateData.class)
public class Update extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        UpdateData updateData = (UpdateData) this.getWebSocketData();

        Record updateRecord = DatabaseCollect
                .create()
                .recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"))
                .uuid(updateData.getRecordUUID())
                .state(RecordState.STATIC)
                .singleResult();

        if (updateRecord != null) {
            Entry updateEntry = updateRecord.get(updateData.getDefinitionName());
            updateEntry.get().setValue(Double.parseDouble(updateData.getValue()));

            updateRecord.set(updateEntry);
            updateRecord.save(RecordState.RAW);

            Infer.me(updateEntry);

//            List<Entry> unsavedEntries = updateRecord.getUnsavedEntries();

            updateRecord.save(RecordState.STATIC);

            // Response from update?
            WorksheetConfigDAO worksheetConfigDAO = new WorksheetConfigDAO();
            List<WorksheetConfig> webWorksheetConfigs = worksheetConfigDAO.getAllWorksheetConfigs();
            List<String> includeColumnCompareReference = new ArrayList<>();

            List<WebWorksheetConfig> webWorksheetWebConfigs = new ArrayList<>();
            for (WorksheetConfig worksheetConfig : webWorksheetConfigs) {
                webWorksheetWebConfigs.add(worksheetConfig.getAsWebWorksheetConfig());
                includeColumnCompareReference.add(worksheetConfig.getDefinition().getUuidString());
            }

            WebRecord webRecord = new WebRecord();
            webRecord.setUuid(updateRecord.getUuidString());

            Entry[] entriesToShow = new Entry[includeColumnCompareReference.size()];
            for (Entry entry : updateRecord.getEntries()) {
                if (includeColumnCompareReference.contains(entry.getDefinition().getUuidString())) {
                    entriesToShow[includeColumnCompareReference.indexOf(entry.getDefinition().getUuidString())] = entry;
                }
            }

            webRecord.setEntriesFromClarity(Arrays.asList(entriesToShow));
            updateData.setWebRecord(webRecord);
        }
    }
}


