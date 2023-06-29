package requests.spark.websockets.objects.messages.dataitems;

import clarity.Entry;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.ArrayList;
import java.util.List;

public class WebRecord extends JSONWeb {
    @WSDataReference(WSData.RECORD_UUID)
    private String uuid = null;

    @WSDataReference(WSData.RECORD_ENTRIES)
    @WSDataJSONArrayClass(WebEntry.class)
    @WSDataTypeScriptClass(WebEntry.class)
    private List<WebEntry> entries = null;

    @WSDataReference(WSData.RECORD_PROPERTIES)
    @WSDataJSONArrayClass(WebProperty.class)
    @WSDataTypeScriptClass(WebProperty.class)
    private List<WebProperty> properties = null;

    public WebRecord() {

    }

    public void setEntriesFromClarity(List<Entry> entries) {
        List<WebEntry> webEntries = new ArrayList<>();

        for (Entry entry : entries) {
            WebEntry webEntry = new WebEntry(entry);
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

    public List<WebEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<WebEntry> entries) {
        this.entries = entries;
    }

    public List<WebProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<WebProperty> properties) {
        this.properties = properties;
    }
}
