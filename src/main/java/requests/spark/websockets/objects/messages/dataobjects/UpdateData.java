package requests.spark.websockets.objects.messages.dataobjects;

import clarity.Entry;
import requests.spark.websockets.objects.messages.dataitems.WebEntryDataItem;
import requests.spark.websockets.objects.messages.dataitems.WebRecordDataItem;
import requests.spark.websockets.objects.messages.mapping.*;

import java.util.ArrayList;
import java.util.List;

public class UpdateData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String value = null;

    @WSDataIncoming
    private String definitionName = null;

    @WSDataIncoming
    @WSDataOutgoing
    private String recordUUID = null;

    @WSDataIncoming
    private String updateSource = null;

    @WSDataOutgoing
    @WSDataJSONArrayClass(WebEntryDataItem.class)
    @WSDataTypeScriptClass(WebEntryDataItem.class)
    private List<WebEntryDataItem> entries = null;

    @WSDataOutgoing
    @WSDataTypeScriptClass(WebRecordDataItem.class)
    private WebRecordDataItem webRecord = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefinitionName() {
        return definitionName;
    }

    public void setDefinitionName(String definitionName) {
        this.definitionName = definitionName;
    }

    public String getRecordUUID() {
        return recordUUID;
    }

    public void setRecordUUID(String recordUUID) {
        this.recordUUID = recordUUID;
    }

    public String getUpdateSource() {
        return updateSource;
    }

    public void setUpdateSource(String updateSource) {
        this.updateSource = updateSource;
    }

    public List<WebEntryDataItem> getEntries() {
        return entries;
    }

    public void setEntries(List<WebEntryDataItem> entries) {
        this.entries = entries;
    }

    public void setEntriesFromClarity(List<Entry> entries) {
        List<WebEntryDataItem> webEntries = new ArrayList<>();

        for (Entry entry : entries) {
            WebEntryDataItem webEntry = new WebEntryDataItem(entry);
            webEntries.add(webEntry);
        }

        setEntries(webEntries);
    }

    public WebRecordDataItem getWebRecord() {
        return webRecord;
    }

    public void setWebRecord(WebRecordDataItem webRecord) {
        this.webRecord = webRecord;
    }
}
