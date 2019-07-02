package game.actors;

import requests.spark.websockets.objects.messages.mapping.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

import java.util.UUID;

public class Player extends JSONWeb {
    @WSDataReference(WSData.PLAYER_UUID)
    private UUID uuid;
    @WSDataReference(WSData.PLAYER_ID)
    private Integer id;
    @WSDataReference(WSData.PLAYER_NAME)
    private String name;

    private static Integer PLAYER_COUNT = 0;

    public Player() {
        uuid = UUID.randomUUID();
        id = Player.getNextPlayerID();
        name = "Player " + id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Integer getId() {
        return id;
    }

    private static synchronized Integer getNextPlayerID() {
        PLAYER_COUNT++;
        return PLAYER_COUNT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
