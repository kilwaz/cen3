package game;

import java.util.HashMap;
import java.util.UUID;

public class Game {
    public UUID uuid;
    private HashMap<UUID, Player> players = new HashMap<>();

    public Game() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player createPlayer() {
        Player player = new Player();
        players.put(player.getUuid(), player);
        return player;
    }
}
