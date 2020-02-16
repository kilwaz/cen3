package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class Entry extends JSONWeb {
    @WSDataReference(WSData.ENTRY_UUID)
    private String uuid = null;

    @WSDataReference(WSData.ENTRY_VALUE)
    private String value = null;

    @WSDataReference(WSData.ENTRY_RECORD_UUID)
    private String recordUUID = null;

    @WSDataReference(WSData.ENTRY_NAME)
    private String name = null;

    public Entry(clarity.Entry entryClarity) {
        this.uuid = entryClarity.getUuid().toString();
        this.value = entryClarity.get().getValue().toString();
        this.name = entryClarity.getReference();
        this.recordUUID = entryClarity.getRecord().getUuid().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
