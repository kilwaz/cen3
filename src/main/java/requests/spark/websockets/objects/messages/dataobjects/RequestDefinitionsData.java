package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.DefinitionDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class RequestDefinitionsData extends WebSocketData {
    @WSDataIncoming
    private String requestedRecordDefinitionName = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(DefinitionDataItem.class)
    @WSDataTypeScriptClass(DefinitionDataItem.class)
    private List<DefinitionDataItem> definitions = null;

    public String getRequestedRecordDefinitionName() {
        return requestedRecordDefinitionName;
    }

    public void setRequestedRecordDefinitionName(String requestedRecordDefinitionName) {
        this.requestedRecordDefinitionName = requestedRecordDefinitionName;
    }

    public List<DefinitionDataItem> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<DefinitionDataItem> definitions) {
        this.definitions = definitions;
    }
}
