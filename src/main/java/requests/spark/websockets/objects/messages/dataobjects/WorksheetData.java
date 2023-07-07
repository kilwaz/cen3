package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.SortFilterDataItem;
import requests.spark.websockets.objects.messages.dataitems.WebRecordDataItem;
import requests.spark.websockets.objects.messages.dataitems.WebWorksheetConfigDataItem;
import requests.spark.websockets.objects.messages.dataitems.WorksheetStatusDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class WorksheetData extends WebSocketData {
    // Incoming
    @WSDataOutgoing @WSDataIncoming
    private String nodeReference = null;

    // OUTGOING
    @WSDataOutgoing
    @WSDataJSONArrayClass(WebRecordDataItem.class)
    @WSDataTypeScriptClass(WebRecordDataItem.class)
    private List<WebRecordDataItem> worksheetRecords = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(WebWorksheetConfigDataItem.class)
    @WSDataTypeScriptClass(WebWorksheetConfigDataItem.class)
    private List<WebWorksheetConfigDataItem> worksheetConfig = null;

    @WSDataOutgoing @WSDataIncoming
    @WSDataTypeScriptClass(SortFilterDataItem.class)
    private SortFilterDataItem sortFilter = null;

    @WSDataOutgoing @WSDataIncoming
    @WSDataTypeScriptClass(WorksheetStatusDataItem.class)
    private WorksheetStatusDataItem worksheetStatus = null;

    public String getNodeReference() {
        return nodeReference;
    }

    public void setNodeReference(String nodeReference) {
        this.nodeReference = nodeReference;
    }

    public List<WebRecordDataItem> getWorksheetRecords() {
        return worksheetRecords;
    }

    public void setWorksheetRecords(List<WebRecordDataItem> worksheetRecords) {
        this.worksheetRecords = worksheetRecords;
    }

    public List<WebWorksheetConfigDataItem> getWorksheetConfig() {
        return worksheetConfig;
    }

    public void setWorksheetConfig(List<WebWorksheetConfigDataItem> worksheetConfig) {
        this.worksheetConfig = worksheetConfig;
    }

    public SortFilterDataItem getSortFilter() {
        return sortFilter;
    }

    public void setSortFilter(SortFilterDataItem sortFilter) {
        this.sortFilter = sortFilter;
    }

    public WorksheetStatusDataItem getWorksheetStatus() {
        return worksheetStatus;
    }

    public void setWorksheetStatus(WorksheetStatusDataItem worksheetStatus) {
        this.worksheetStatus = worksheetStatus;
    }
}
