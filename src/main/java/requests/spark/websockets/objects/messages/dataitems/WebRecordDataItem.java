package requests.spark.websockets.objects.messages.dataitems;

import clarity.Entry;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.ArrayList;
import java.util.List;

public class WebRecordDataItem extends JSONWeb {
    @WSDataReference()
    private String uuid = null;

    @WSDataReference()
    @WSDataJSONArrayClass(WebEntryDataItem.class)
    @WSDataTypeScriptClass(WebEntryDataItem.class)
    private List<WebEntryDataItem> entries = null;

    @WSDataReference()
    @WSDataJSONArrayClass(WebPropertyDataItem.class)
    @WSDataTypeScriptClass(WebPropertyDataItem.class)
    private List<WebPropertyDataItem> properties = null;

    public WebRecordDataItem() {

    }

    public void setEntriesFromClarity(List<Entry> entries) {
        List<WebEntryDataItem> webEntries = new ArrayList<>();

        for (Entry entry : entries) {
            WebEntryDataItem webEntry = new WebEntryDataItem(entry);
            webEntries.add(webEntry);
        }

        setEntries(webEntries);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<WebEntryDataItem> getEntries() {
        return entries;
    }

    public void setEntries(List<WebEntryDataItem> entries) {
        this.entries = entries;
    }

    public List<WebPropertyDataItem> getProperties() {
        return properties;
    }

    public void setProperties(List<WebPropertyDataItem> properties) {
        this.properties = properties;
    }
}
