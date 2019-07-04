package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;

public class AnswerResponseData extends WebSocketData {
    @WSDataIncoming
    private String answerStr = "";
    @WSDataIncoming
    private String playerUUID = "";

    public String getAnswerStr() {
        return answerStr;
    }

    public void setAnswerStr(String answerStr) {
        this.answerStr = answerStr;
    }

    public String getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(String playerUUID) {
        this.playerUUID = playerUUID;
    }
}
