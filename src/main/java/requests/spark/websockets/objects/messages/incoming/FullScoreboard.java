package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.Score;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("FullScoreboard")
public class FullScoreboard extends Message {
    private static Logger log = Logger.getLogger(FullScoreboard.class);

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();

        JSONArray allScores = new JSONArray();
        for (Score score : currentGame.getScores()) {
            JSONObject scoreJSON = new JSONObject();

            scoreJSON.put("score", score.getScore());
            scoreJSON.put("playerUUID", score.getPlayer().getUuid());

            allScores.put(scoreJSON);
        }

        addResponseData("scores", allScores);

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {

    }
}
