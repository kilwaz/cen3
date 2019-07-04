package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Answer;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.UUID;

public class AnswerUpdateData extends WebSocketData {
    @WSDataOutgoing
    private UUID answerUUID;
    @WSDataOutgoing
    private UUID playerUUID;
    @WSDataOutgoing
    private String answerValue;

    public AnswerUpdateData(Answer answer) {
        answerUUID = answer.getUuid();
        playerUUID = answer.getPlayer().getUuid();
        answerValue = answer.getAnswerValue();
    }

    public UUID getAnswerUUID() {
        return answerUUID;
    }

    public void setAnswerUUID(UUID answerUUID) {
        this.answerUUID = answerUUID;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }
}
