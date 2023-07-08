package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.List;

public class ConfigurableUiDataItem extends JSONWeb {
    @WSDataReference()
    private String type = null;

    @WSDataReference()
    @WSDataTypeScriptClass(WebRecordDataItem.class)
    private WebRecordDataItem headers = null;

    @WSDataReference()
    @WSDataJSONArrayClass(WebRecordDataItem.class)
    @WSDataTypeScriptClass(WebRecordDataItem.class)
    private List<WebRecordDataItem> records = null;

    public ConfigurableUiDataItem(String type, List<WebRecordDataItem> records) {
        this.type = type;
        this.records = records;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<WebRecordDataItem> getRecords() {
        return records;
    }

    public void setRecords(List<WebRecordDataItem> records) {
        this.records = records;
    }

    public WebRecordDataItem getHeaders() {
        return headers;
    }

    public void setHeaders(WebRecordDataItem headers) {
        this.headers = headers;
    }
}