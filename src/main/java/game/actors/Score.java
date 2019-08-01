package game.actors;

import game.Game;
import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

import java.util.UUID;

public class Score extends JSONWeb {
    private static Logger log = AppLogger.logger();

    @WSDataReference(WSData.SCORE_GAME)
    private Game game;
    @WSDataReference(WSData.SCORE_PLAYER)
    private UUID playerUUID;
    @WSDataReference(WSData.SCORE_SCORE)
    private Integer score = 0;

    private Player player;

    public Score(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.playerUUID = player.getUuid();
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getScore() {
        return score;
    }

    public Score givePoint() {
        score++;
        log.info("Score is now " + score + " for " + player.getUuid());
        return this;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
