package game.actors;

import java.util.UUID;

public class GameMaster {
    public UUID uuid;

    public GameMaster() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
