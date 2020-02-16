package game.actors;

import org.json.JSONArray;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class Record extends JSONWeb {
    @WSDataReference(WSData.RECORD_UUID)
    private String uuid = null;

    @WSDataReference(WSData.RECORD_ENTRIES)
    @WSDataJSONArrayClass(Entry.class)
    private JSONArray entries = null;

    public Record() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public JSONArray getEntries() {
        return entries;
    }

    public void setEntries(JSONArray entries) {
        this.entries = entries;
    }
}
