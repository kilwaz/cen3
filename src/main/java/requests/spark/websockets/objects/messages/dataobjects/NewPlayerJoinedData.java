package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Player;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.UUID;

public class NewPlayerJoinedData extends WebSocketData {
    @WSDataOutgoing
    private UUID uuid;
    @WSDataOutgoing
    private Integer id;
    @WSDataOutgoing
    private String name;

    public NewPlayerJoinedData(Player newPlayer) {
        uuid = newPlayer.getUuid();
        id = newPlayer.getId();
        name = newPlayer.getName();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
