package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.TriggerRoundEndData;
import requests.spark.websockets.objects.messages.dataobjects.UpdateScoreData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("TriggerRoundEnd")
@WebSocketDataClass(TriggerRoundEndData.class)
public class TriggerRoundEnd extends Message {
    private static Logger log = Logger.getLogger(TriggerRoundEnd.class);

    public void process() {
        TriggerRoundEndData triggerRoundEndData = (TriggerRoundEndData) this.getWebSocketData();
        Game currentGame = GameManager.getInstance().getCurrentGame();

        currentGame.resetScores();

        Push.message(PushMessage.UPDATE_SCORE)
                .data(new UpdateScoreData(currentGame.getScores()))
                .to(Audience.ALL_ADMINS)
                .push();
        log.info("Round has ended, scores are now reset");
    }
}
