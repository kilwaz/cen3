package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.UpdateScore;

@MessageType("ResetGame")
public class ResetGame extends Message {
    private static Logger log = Logger.getLogger(ResetGame.class);

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();

        currentGame.resetGame();

        UpdateScore updateScore = Message.create(UpdateScore.class);
        updateScore.scores(currentGame.getScores());
        updateScore.sendTo(Message.ALL_ADMINS);

        log.info("Game has been reset");

        handleResponse();
    }

    public void prepareToSend() {

    }
}
