package game.actors;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class Score extends JSONWeb {
    @WSDataReference(WSData.SCORE_SCORE)
    private Integer score = 0;

    @WSDataReference(WSData.SCORE_PLAYER)
    @WSDataTypeScriptClass(Player.class)
    private Player player;

    public Score(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getScore() {
        return score;
    }

    public Score givePoint() {
        score++;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
