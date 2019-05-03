package game.actors;

import java.util.UUID;

public class Player {
    private UUID uuid;

    public Player() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
