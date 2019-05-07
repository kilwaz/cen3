package game;

import game.actors.Admin;
import game.actors.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {
    public UUID uuid;
    private HashMap<UUID, Player> players = new HashMap<>();
    private HashMap<UUID, Admin> admins = new HashMap<>();

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

    public Admin createAdmin() {
        Admin admin = new Admin();
        admins.put(admin.getUuid(), admin);
        return admin;
    }

    public Player findPlayer(String uuidStr) {
        return players.get(UUID.fromString(uuidStr));
    }

    public Admin findAdmin(String uuidStr) {
        return admins.get(UUID.fromString(uuidStr));
    }
}
