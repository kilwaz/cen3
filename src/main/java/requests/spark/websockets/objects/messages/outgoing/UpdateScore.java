package requests.spark.websockets.objects.messages.outgoing;

import game.actors.Score;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

import java.util.ArrayList;
import java.util.List;

@MessageType("UpdateScore")
public class UpdateScore extends Message {
    private static Logger log = Logger.getLogger(UpdateScore.class);

    private List<Score> scores = new ArrayList<>();

    public UpdateScore scores(List<Score> scores) {
        this.scores = scores;
        return this;
    }

    public void prepareToSend() {
        JSONArray allScores = new JSONArray();
        for (Score score : scores) {
            JSONObject option = new JSONObject();

            option.put("score", score.getScore());
            option.put("playerUUID", score.getPlayer().getUuid());

            allScores.put(option);
        }

        addResponseData("scores", allScores);
    }
}
