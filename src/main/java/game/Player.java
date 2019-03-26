package game;

import java.util.UUID;

public class Player {

    public UUID uuid;

    public Player() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
