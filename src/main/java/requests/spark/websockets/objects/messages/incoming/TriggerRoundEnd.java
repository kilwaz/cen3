package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.UpdateScore;

@MessageType("TriggerRoundEnd")
public class TriggerRoundEnd extends Message {
    private static Logger log = Logger.getLogger(TriggerRoundEnd.class);

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();

        currentGame.resetScores();

        UpdateScore updateScore = Message.create(UpdateScore.class);
        updateScore.scores(currentGame.getScores());
        updateScore.sendTo(Message.ALL_ADMINS);

        log.info("Round has ended, scores are now reset");

        handleResponse();
    }

    public void prepareToSend() {

    }
}
