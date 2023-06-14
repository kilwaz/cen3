package requests.spark.websockets.objects.messages.dataobjects;

import clarity.Entry;
import requests.spark.websockets.objects.messages.dataitems.WebEntry;
import requests.spark.websockets.objects.messages.dataitems.WebRecord;
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
    @WSDataReference(WSData.RECORD_ENTRIES)
    @WSDataJSONArrayClass(WebEntry.class)
    @WSDataTypeScriptClass(WebEntry.class)
    private List<WebEntry> entries = null;

    @WSDataOutgoing
    @WSDataReference(WSData.RECORD_ENTRIES)
    @WSDataTypeScriptClass(WebRecord.class)
    private WebRecord webRecord = null;

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

    public List<WebEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<WebEntry> entries) {
        this.entries = entries;
    }

    public void setEntriesFromClarity(List<Entry> entries) {
        List<WebEntry> webEntries = new ArrayList<>();

        for (Entry entry : entries) {
            WebEntry webEntry = new WebEntry(entry);
            webEntries.add(webEntry);
        }

        setEntries(webEntries);
    }

    public WebRecord getWebRecord() {
        return webRecord;
    }

    public void setWebRecord(WebRecord webRecord) {
        this.webRecord = webRecord;
    }
}
