package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;
import utils.managers.PlayerCounterManager;

import java.util.UUID;

public class Player extends JSONWeb {
    @WSDataReference(WSData.PLAYER_UUID)
    private UUID uuid;
    @WSDataReference(WSData.PLAYER_ID)
    private Integer id;
    @WSDataReference(WSData.PLAYER_NAME)
    private String name;

    @WSDataReference(WSData.PLAYER_SCORE)
    private Integer score;
    @WSDataReference(WSData.PLAYER_PLAYER_STATUS)
    private String playerStatus;

    @WSDataReference(WSData.PLAYER_LATEST_ANSWER)
    @WSDataTypeScriptClass(Answer.class)
    private Answer latestAnswer;

    public Player() {
        uuid = UUID.randomUUID();
        id = PlayerCounterManager.getInstance().getNextPlayerID();
        name = "Player " + id;
        score = 0;
        playerStatus = "alert-light";
        latestAnswer = null;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(String playerStatus) {
        this.playerStatus = playerStatus;
    }

    public Answer getLatestAnswer() {
        return latestAnswer;
    }

    public void setLatestAnswer(Answer latestAnswer) {
        this.latestAnswer = latestAnswer;
    }
}
