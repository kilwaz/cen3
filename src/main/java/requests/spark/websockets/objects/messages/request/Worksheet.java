package requests.spark.websockets.objects.messages.request;

import clarity.Entry;
import clarity.Record;
import clarity.definition.Definitions;
import clarity.definition.HierarchyNode;
import clarity.definition.RecordState;
import clarity.definition.WorksheetConfig;
import data.model.DatabaseCollect;
import data.model.DatabaseSortFilter;
import data.model.dao.HierarchyNodeDAO;
import data.model.dao.WorksheetConfigDAO;
import log.AppLogger;
import log.EventLogCreator;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataitems.WebPropertyDataItem;
import requests.spark.websockets.objects.messages.dataitems.WebRecordDataItem;
import requests.spark.websockets.objects.messages.dataitems.WebWorksheetConfigDataItem;
import requests.spark.websockets.objects.messages.dataitems.WorksheetStatusDataItem;
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

        WorksheetConfigDAO worksheetConfigDAO = new WorksheetConfigDAO();
        List<WorksheetConfig> webWorksheetConfigs = worksheetConfigDAO.getAllWorksheetConfigs();
        List<String> includeColumnCompareReference = new ArrayList<>();

        List<WebWorksheetConfigDataItem> webWorksheetWebConfigs = new ArrayList<>();
        for (WorksheetConfig worksheetConfig : webWorksheetConfigs) {
            webWorksheetWebConfigs.add(worksheetConfig.getAsWebWorksheetConfig());
            includeColumnCompareReference.add(worksheetConfig.getDefinition().getUuidString());
        }

        worksheetData.setWorksheetConfig(webWorksheetWebConfigs);

        WorksheetStatusDataItem worksheetStatus = worksheetData.getWorksheetStatus();

        List<Record> empRecords = DatabaseCollect
                .create()
                .recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"))
                .sortFilter(new DatabaseSortFilter(worksheetData.getSortFilter()))
                .state(RecordState.STATIC)
                .nodeReference(worksheetData.getRequestID())
                .pageNumber(worksheetStatus != null && worksheetStatus.getCurrentPageNumber() != null ? worksheetStatus.getCurrentPageNumber() : 1)
                .pageSize(worksheetStatus != null && worksheetStatus.getPageSize() != null ? worksheetStatus.getPageSize() : 25)
                .collect();

        List<WebRecordDataItem> worksheetRecords = new ArrayList<>();
        int counter = 0;
        for (Record record : empRecords) {
            WebRecordDataItem webRecord = new WebRecordDataItem();
            webRecord.setUuid(record.getUuidString());

            Entry[] entriesToShow = new Entry[includeColumnCompareReference.size()];
            for (Entry entry : record.getEntries()) {
                if (includeColumnCompareReference.contains(entry.getDefinition().getUuidString())) {
                    entriesToShow[includeColumnCompareReference.indexOf(entry.getDefinition().getUuidString())] = entry;
                }
            }

            webRecord.setEntriesFromClarity(Arrays.asList(entriesToShow));
            worksheetRecords.add(webRecord);

            if (counter == 2) {
                List<WebPropertyDataItem> properties = new ArrayList<>();
                properties.add(new WebPropertyDataItem("worksheetRowColor", "iaColor"));
                webRecord.setProperties(properties);
            }

            counter++;
        }

        worksheetData.setWorksheetRecords(worksheetRecords);
        worksheetStatus();

        EventLogCreator.init()
                .pageLoadEvent("worksheet")
                .comment("This is a log message")
                .log();
    }

    private void worksheetStatus() {
        WorksheetData worksheetData = (WorksheetData) this.getWebSocketData();

        WorksheetStatusDataItem worksheetStatus = worksheetData.getWorksheetStatus();
        if (worksheetStatus == null) {
            worksheetStatus = new WorksheetStatusDataItem();
        }

        HierarchyNodeDAO hierarchyNodeDAO = new HierarchyNodeDAO();
        HierarchyNode hierarchyNode = hierarchyNodeDAO.getNodeByReference(worksheetData.getRequestID());

        if (worksheetStatus.getPageSize() == null) {
            worksheetStatus.setPageSize(25);
        }

        if (worksheetStatus.getCurrentPageNumber() == null) {
            worksheetStatus.setCurrentPageNumber(1);
        }

        if (hierarchyNode != null) {
            worksheetStatus.setWorksheetName(hierarchyNode.getNodeName());
            worksheetStatus.setHeadCount(hierarchyNodeDAO.getHeadCount(hierarchyNode));
            worksheetStatus.setTotalPages(worksheetStatus.getHeadCount() / worksheetStatus.getPageSize());
        }

        worksheetData.setWorksheetStatus(worksheetStatus);
    }
}
