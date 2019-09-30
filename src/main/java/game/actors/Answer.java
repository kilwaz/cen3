package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

import java.util.UUID;

public class Answer extends JSONWeb {
    @WSDataReference(WSData.ANSWER_UUID)
    private UUID uuid;
    @WSDataReference(WSData.ANSWER_ANSWER_VALUE)
    private String answerValue;
    @WSDataTypeScriptClass(Player.class)
    @WSDataReference(WSData.ANSWER_PLAYER)
    private Player player;

    public Answer() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Answer answerValue(String answerValue) {
        this.answerValue = answerValue;
        return this;
    }

    public Answer player(Player player) {
        this.player = player;
        return this;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public Player getPlayer() {
        return player;
    }
}
