package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.ResetGameData;
import requests.spark.websockets.objects.messages.dataobjects.UpdateScoreData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.UpdateScore;

@MessageType("ResetGame")
@WebSocketDataClass(ResetGameData.class)
public class ResetGame extends Message {
    private static Logger log = Logger.getLogger(ResetGame.class);

    public void process() {
        ResetGameData resetGameData = (ResetGameData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        currentGame.resetGame();

        Message.push(UpdateScore.class, new UpdateScoreData(currentGame.getScores()), WebSocketAction.ALL_ADMINS);

        log.info("Game has been reset");
    }
}
