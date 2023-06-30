package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.RecordDefinitionDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class RequestRecordDefinitionsData extends WebSocketData {
    @WSDataOutgoing
    @WSDataJSONArrayClass(RecordDefinitionDataItem.class)
    @WSDataTypeScriptClass(RecordDefinitionDataItem.class)
    private List<RecordDefinitionDataItem> recordDefinitions = null;

    public List<RecordDefinitionDataItem> getRecordDefinitions() {
        return recordDefinitions;
    }

    public void setRecordDefinitions(List<RecordDefinitionDataItem> recordDefinitions) {
        this.recordDefinitions = recordDefinitions;
    }
}
