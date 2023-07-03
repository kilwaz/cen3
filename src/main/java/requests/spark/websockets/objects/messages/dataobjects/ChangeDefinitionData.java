package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.DefinitionDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class ChangeDefinitionData extends WebSocketData {
    @WSDataIncoming
    @WSDataTypeScriptClass(DefinitionDataItem.class)
    private DefinitionDataItem definition = null;

    public DefinitionDataItem getDefinition() {
        return definition;
    }

    public void setDefinition(DefinitionDataItem definition) {
        this.definition = definition;
    }
}
