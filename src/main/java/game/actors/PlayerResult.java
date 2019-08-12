package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

import java.util.UUID;

public class PlayerResult extends JSONWeb {

    @WSDataReference(WSData.PLAYER_RESULT_PLAYER_UUID)
    private UUID playerUUID;
    @WSDataReference(WSData.PLAYER_RESULT_IS_CORRECT_ANSWER)
    private Boolean isCorrectAnswer;

    public PlayerResult(UUID playerUUId, Boolean isCorrectAnswer) {
        this.playerUUID = playerUUId;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public Boolean getCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }
}
