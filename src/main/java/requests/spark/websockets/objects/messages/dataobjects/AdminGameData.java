package requests.spark.websockets.objects.messages.dataobjects;

import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.UUID;

public class AdminGameData extends WebSocketData {
    @WSDataIncoming
    private String localStorageUUID = "";

    @WSDataOutgoing
    private UUID adminUUID = null;
    @WSDataOutgoing
    private JSONArray allPlayers = null;

    public String getLocalStorageUUID() {
        return localStorageUUID;
    }

    public void setLocalStorageUUID(String localStorageUUID) {
        this.localStorageUUID = localStorageUUID;
    }

    public UUID getAdminUUID() {
        return adminUUID;
    }

    public void setAdminUUID(UUID adminUUID) {
        this.adminUUID = adminUUID;
    }

    public JSONArray getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(JSONArray allPlayers) {
        this.allPlayers = allPlayers;
    }
}
