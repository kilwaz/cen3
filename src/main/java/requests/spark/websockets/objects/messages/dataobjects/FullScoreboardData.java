package requests.spark.websockets.objects.messages.dataobjects;

import game.actors.ScoreUpdate;
import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataJSONArrayClass;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class FullScoreboardData extends WebSocketData {
    @WSDataOutgoing
    @WSDataJSONArrayClass(ScoreUpdate.class)
    private JSONArray scores = null;

    public JSONArray getScores() {
        return scores;
    }

    public void setScores(JSONArray scores) {
        this.scores = scores;
    }
}
