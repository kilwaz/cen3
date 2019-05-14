package game.actors;

import java.util.UUID;

public class Player {
    private UUID uuid;
    private Integer id;
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
}
