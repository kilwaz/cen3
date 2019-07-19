package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.ResetGameData;
import requests.spark.websockets.objects.messages.dataobjects.UpdateScoreData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("ResetGame")
@WebSocketDataClass(ResetGameData.class)
public class ResetGame extends Message {
    private static Logger log = Logger.getLogger(ResetGame.class);

    public void process() {
        ResetGameData resetGameData = (ResetGameData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        currentGame.resetGame();
        Push.message(PushMessage.UPDATE_SCORE)
                .data(new UpdateScoreData(currentGame.getScores()))
                .to(Audience.ALL_ADMINS)
                .push();

        log.info("Game has been reset");
    }
}
