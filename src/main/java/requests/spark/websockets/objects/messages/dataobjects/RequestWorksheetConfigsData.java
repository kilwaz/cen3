package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.WorksheetConfigDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class RequestWorksheetConfigsData extends WebSocketData {
    @WSDataOutgoing
    @WSDataJSONArrayClass(WorksheetConfigDataItem.class)
    @WSDataTypeScriptClass(WorksheetConfigDataItem.class)
    private List<WorksheetConfigDataItem> worksheetConfigs = null;

    public List<WorksheetConfigDataItem> getWorksheetConfigs() {
        return worksheetConfigs;
    }

    public void setWorksheetConfigs(List<WorksheetConfigDataItem> worksheetConfigs) {
        this.worksheetConfigs = worksheetConfigs;
    }
}
