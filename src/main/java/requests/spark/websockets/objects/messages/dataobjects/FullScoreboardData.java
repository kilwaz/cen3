package requests.spark.websockets.objects.messages.dataobjects;

import org.json.JSONArray;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class FullScoreboardData extends WebSocketData {
    @WSDataOutgoing
    private JSONArray allScores = null;

    public JSONArray getAllScores() {
        return allScores;
    }

    public void setAllScores(JSONArray allScores) {
        this.allScores = allScores;
    }
}
