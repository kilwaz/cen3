package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.SortFilter;
import requests.spark.websockets.objects.messages.dataitems.WebRecord;
import requests.spark.websockets.objects.messages.dataitems.WebWorksheetConfig;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class WorksheetData extends WebSocketData {
    // OUTGOING
    @WSDataOutgoing @WSDataIncoming
    private String requestID = null;

    // OUTGOING
    @WSDataOutgoing
    @WSDataJSONArrayClass(WebRecord.class)
    @WSDataTypeScriptClass(WebRecord.class)
    private List<WebRecord> worksheetRecords = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(WebWorksheetConfig.class)
    @WSDataTypeScriptClass(WebWorksheetConfig.class)
    private List<WebWorksheetConfig> worksheetConfig = null;

    @WSDataOutgoing @WSDataIncoming
    @WSDataTypeScriptClass(SortFilter.class)
    private SortFilter sortFilter = null;

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public List<WebRecord> getWorksheetRecords() {
        return worksheetRecords;
    }

    public void setWorksheetRecords(List<WebRecord> worksheetRecords) {
        this.worksheetRecords = worksheetRecords;
    }

    public List<WebWorksheetConfig> getWorksheetConfig() {
        return worksheetConfig;
    }

    public void setWorksheetConfig(List<WebWorksheetConfig> worksheetConfig) {
        this.worksheetConfig = worksheetConfig;
    }

    public SortFilter getSortFilter() {
        return sortFilter;
    }

    public void setSortFilter(SortFilter sortFilter) {
        this.sortFilter = sortFilter;
    }
}
