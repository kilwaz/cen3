package utils.managers;

public class PlayerCounterManager {
    private static PlayerCounterManager instance;

    private Integer playerCount = 0;

    public static PlayerCounterManager getInstance() {
        if (instance == null) {
            instance = new PlayerCounterManager();
        }
        return instance;
    }

    public synchronized Integer getNextPlayerID() {
        playerCount++;
        return playerCount;
    }
}
