package game.actors;

import java.util.UUID;

public class Admin {
    public UUID uuid;

    public Admin() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
