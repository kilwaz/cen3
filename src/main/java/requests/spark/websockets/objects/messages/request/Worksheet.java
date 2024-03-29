package requests.spark.websockets.objects.messages.request;

import clarity.Entry;
import clarity.Record;
import clarity.definition.*;
import clarity.load.store.expression.Expression;
import clarity.load.store.expression.Formula;
import clarity.load.store.expression.instance.InstancedFormula;
import data.model.DatabaseCollect;
import data.model.DatabaseSortFilter;
import data.model.dao.HierarchyNodeDAO;
import data.model.dao.WorksheetConfigDAO;
import data.model.dao.WorksheetConfigDetailsDAO;
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
        WorksheetConfig worksheetConfig = worksheetConfigDAO.getWorksheetConfig("Worksheet");
        WorksheetConfigDetailsDAO worksheetConfigDetailsDAO = new WorksheetConfigDetailsDAO();
        List<WorksheetConfigDetails> webWorksheetConfigDetails = worksheetConfigDetailsDAO.getWorksheetConfigDetails(worksheetConfig);

        List<String> includeColumnCompareReference = new ArrayList<>();

        List<WebWorksheetConfigDataItem> webWorksheetWebConfigs = new ArrayList<>();
        for (WorksheetConfigDetails worksheetConfigDetails : webWorksheetConfigDetails) {
            webWorksheetWebConfigs.add(worksheetConfigDetails.getAsWebWorksheetConfig());
            includeColumnCompareReference.add(worksheetConfigDetails.getDefinition().getUuidString());
        }

        worksheetData.setWorksheetConfig(webWorksheetWebConfigs);

        WorksheetStatusDataItem worksheetStatus = worksheetData.getWorksheetStatus();

        List<Record> empRecords = DatabaseCollect
                .create()
                .recordDefinition(Definitions.getInstance().getRecordDefinition("Employee"))
                .sortFilter(new DatabaseSortFilter(worksheetData.getSortFilter()))
                .state(RecordState.STATIC)
                .nodeReference(worksheetData.getNodeReference())
                .pageNumber(worksheetStatus != null && worksheetStatus.getCurrentPageNumber() != null ? worksheetStatus.getCurrentPageNumber() : 1)
                .pageSize(worksheetStatus != null && worksheetStatus.getPageSize() != null ? worksheetStatus.getPageSize() : 25)
                .collect();


        Formula formula = Formula.create("if('International Assignment'=[Assignment_Status],'iaColor','noColor')");

        List<WebRecordDataItem> worksheetRecords = new ArrayList<>();
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

            InstancedFormula instancedFormula = formula.createInstance();
            instancedFormula.record(record);
            Expression result = instancedFormula.solve();

            List<WebPropertyDataItem> properties = new ArrayList<>();
            properties.add(new WebPropertyDataItem("worksheetRowColor", result.getStringRepresentation()));
            webRecord.setProperties(properties);
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
        HierarchyNode hierarchyNode = hierarchyNodeDAO.getNodeByReference(worksheetData.getNodeReference());

        if (worksheetStatus.getPageSize() == null) {
            worksheetStatus.setPageSize(25);
        }

        if (worksheetStatus.getCurrentPageNumber() == null) {
            worksheetStatus.setCurrentPageNumber(1);
        }

        if (hierarchyNode != null) {
            worksheetStatus.setWorksheetName(hierarchyNode.getNodeName());
            worksheetStatus.setHeadCount(hierarchyNodeDAO.getHeadCount(hierarchyNode));
            worksheetStatus.setTotalPages(Double.valueOf(Math.ceil(worksheetStatus.getHeadCount().doubleValue() / worksheetStatus.getPageSize().doubleValue())).intValue()); // This typing is fair silly...
        }

        worksheetData.setWorksheetStatus(worksheetStatus);
    }
}
