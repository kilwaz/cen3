package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Player;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.UUID;

public class NewPlayerJoinedData extends WebSocketData {
    @WSDataOutgoing
    private UUID newPlayerUUID;
    @WSDataOutgoing
    private Integer newPlayerID;
    @WSDataOutgoing
    private String newPlayerName;

    public NewPlayerJoinedData(Player newPlayer) {
        newPlayerUUID = newPlayer.getUuid();
        newPlayerID = newPlayer.getId();
        newPlayerName = newPlayer.getName();
    }

    public UUID getNewPlayerUUID() {
        return newPlayerUUID;
    }

    public void setNewPlayerUUID(UUID newPlayerUUID) {
        this.newPlayerUUID = newPlayerUUID;
    }

    public Integer getNewPlayerID() {
        return newPlayerID;
    }

    public void setNewPlayerID(Integer newPlayerID) {
        this.newPlayerID = newPlayerID;
    }

    public String getNewPlayerName() {
        return newPlayerName;
    }

    public void setNewPlayerName(String newPlayerName) {
        this.newPlayerName = newPlayerName;
    }
}
