package requests.spark.websockets.objects.messages.dataitems;

import clarity.Entry;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class WebEntryDataItem extends JSONWeb {
    @WSDataReference()
    private String value = null;

    @WSDataReference()
    private String recordUUID = null;

    @WSDataReference()
    private String name = null;

    public WebEntryDataItem(Entry entryClarity) {
        if (entryClarity.get().getValue() != null) {
            this.value = entryClarity.get().getValue().toString();
        }
        this.name = entryClarity.getReference();
        this.recordUUID = entryClarity.getRecord().getUuidString();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRecordUUID() {
        return recordUUID;
    }

    public void setRecordUUID(String recordUUID) {
        this.recordUUID = recordUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
