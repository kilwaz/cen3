package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.ConfigurableUiDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class SummaryData extends WebSocketData {
    @WSDataIncoming
    private String nodeReference = null;

    @WSDataOutgoing
    private String content = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(ConfigurableUiDataItem.class)
    @WSDataTypeScriptClass(ConfigurableUiDataItem.class)
    private List<ConfigurableUiDataItem> configurableUiDataItems = null;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNodeReference() {
        return nodeReference;
    }

    public void setNodeReference(String nodeReference) {
        this.nodeReference = nodeReference;
    }

    public List<ConfigurableUiDataItem> getConfigurableUiDataItems() {
        return configurableUiDataItems;
    }

    public void setConfigurableUiDataItems(List<ConfigurableUiDataItem> configurableUiDataItems) {
        this.configurableUiDataItems = configurableUiDataItems;
    }
}
