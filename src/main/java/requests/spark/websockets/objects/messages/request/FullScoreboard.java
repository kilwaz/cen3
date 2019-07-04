package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Score;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.FullScoreboardData;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("FullScoreboard")
@WebSocketDataClass(FullScoreboardData.class)
public class FullScoreboard extends Message {
    private static Logger log = Logger.getLogger(FullScoreboard.class);

    public void process() {
        FullScoreboardData fullScoreboardData = (FullScoreboardData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        JSONArray allScores = new JSONArray();
        for (Score score : currentGame.getScores()) {
            allScores.put(score.prepareForJSON(WSData.SCORE_SCORE, WSData.SCORE_PLAYER));
        }

        fullScoreboardData.setAllScores(allScores);
    }
}
