package requests.spark.websockets.objects.messages.dataobjects;

import org.json.JSONArray;
import requests.spark.websockets.objects.messages.incoming.AdminGame;

import java.util.UUID;

@WebSocketDataClass(AdminGame.class)
public class AdminGameData extends WebSocketData {
    private String localStorageUUID = "";

    private UUID adminUUID = null;
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
