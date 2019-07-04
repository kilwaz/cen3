package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.UUID;

public class JoinGameData extends WebSocketData {
    @WSDataIncoming
    private String localStorageUUID = null;

    @WSDataOutgoing
    private UUID playerUUID = null;
    @WSDataOutgoing
    private Integer playerID = null;
    @WSDataOutgoing
    private String playerName = null;

    public String getLocalStorageUUID() {
        return localStorageUUID;
    }

    public void setLocalStorageUUID(String localStorageUUID) {
        this.localStorageUUID = localStorageUUID;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
