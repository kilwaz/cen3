package game.actors;

import java.util.UUID;

public class Question {
    public UUID uuid;

    public Question() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
