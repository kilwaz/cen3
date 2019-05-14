package requests.spark.websockets.objects.messages.outgoing;

import game.actors.Score;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("UpdateScore")
public class UpdateScore extends Message {
    private static Logger log = Logger.getLogger(UpdateScore.class);

    private Score score = null;

    public UpdateScore score(Score score) {
        this.score = score;
        return this;
    }

    public void prepareToSend() {
        addResponseData("score", score.getScore());
        addResponseData("playerUUID", score.getPlayer().getUuid());
    }
}
