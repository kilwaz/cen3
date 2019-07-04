package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.TriggerRoundEndData;
import requests.spark.websockets.objects.messages.dataobjects.UpdateScoreData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.UpdateScore;

@MessageType("TriggerRoundEnd")
@WebSocketDataClass(TriggerRoundEndData.class)
public class TriggerRoundEnd extends Message {
    private static Logger log = Logger.getLogger(TriggerRoundEnd.class);

    public void process() {
        TriggerRoundEndData triggerRoundEndData = (TriggerRoundEndData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        currentGame.resetScores();

        Message.push(UpdateScore.class, new UpdateScoreData(currentGame.getScores()), WebSocketAction.ALL_ADMINS);
        log.info("Round has ended, scores are now reset");
    }
}
