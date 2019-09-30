package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Player;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.UUID;

public class GameMasterJoinData extends WebSocketData {
    @WSDataIncoming
    private String localStorageUUID = "";

    @WSDataOutgoing
    private UUID gameMasterUUID = null;
    @WSDataOutgoing
    @WSDataJSONArrayClass(Player.class)
    private JSONArray players = null;

    public String getLocalStorageUUID() {
        return localStorageUUID;
    }

    public void setLocalStorageUUID(String localStorageUUID) {
        this.localStorageUUID = localStorageUUID;
    }

    public UUID getGameMasterUUID() {
        return gameMasterUUID;
    }

    public void setGameMasterUUID(UUID gameMasterUUID) {
        this.gameMasterUUID = gameMasterUUID;
    }

    public JSONArray getPlayers() {
        return players;
    }

    public void setPlayers(JSONArray players) {
        this.players = players;
    }
}
