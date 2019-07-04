package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.Score;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

import java.util.List;

public class UpdateScoreData extends WebSocketData {
    @WSDataOutgoing
    private JSONArray scores;

    public UpdateScoreData(List<Score> scores) {
        this.scores = new JSONArray();
        for (Score score : scores) {
            this.scores.put(score.prepareForJSON(WSData.SCORE_PLAYER, WSData.SCORE_SCORE));
        }
    }

    public JSONArray getScores() {
        return scores;
    }

    public void setScores(JSONArray scores) {
        this.scores = scores;
    }
}
