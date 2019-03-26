package game;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.UUID;

public class GameManager {
    private static GameManager instance;
    private static Logger log = Logger.getLogger(GameManager.class);

    private HashMap<UUID, Game> games = new HashMap<>();
    private Game currentGame = null;

    private GameManager() {
        instance = this;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Game createNewGame() {
        Game game = new Game();
        log.info("Created new game, ready to play");

        games.put(game.getUuid(), game);
        currentGame = game;
        return game;
    }

    public Game getCurrentGame() {
        return currentGame;
    }
}
