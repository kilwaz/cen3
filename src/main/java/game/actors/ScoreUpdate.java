package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

import java.util.UUID;

public class ScoreUpdate extends JSONWeb {
    @WSDataReference(WSData.SCORE_UPDATE_SCORE)
    private Integer score = 0;
    @WSDataReference(WSData.SCORE_UPDATE_PLAYER_UUID)
    private UUID playerUUID;

    public ScoreUpdate(Integer score, UUID playerUUID) {
        this.score = score;
        this.playerUUID = playerUUID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
}
