package game.actors;

import java.util.UUID;

public class Player {
    private UUID uuid;
    private Integer id;

    private static Integer PLAYER_COUNT = 0;

    public Player() {
        uuid = UUID.randomUUID();
        id = Player.getNextPlayerID();
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
}
