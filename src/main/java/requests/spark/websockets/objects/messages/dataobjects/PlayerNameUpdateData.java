package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Player;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.UUID;

public class PlayerNameUpdateData extends WebSocketData {
    @WSDataOutgoing
    private UUID playerUUID;
    @WSDataOutgoing
    private String newName;

    public PlayerNameUpdateData(Player player) {
        playerUUID = player.getUuid();
        newName = player.getName();
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
