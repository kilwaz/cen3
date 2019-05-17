package requests.spark.websockets.objects.messages.outgoing;

import game.actors.Player;
import game.actors.Question;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("QuestionResults")
public class QuestionResults extends Message {
    private static Logger log = Logger.getLogger(QuestionResults.class);

    private Question question = null;

    public QuestionResults question(Question question) {
        this.question = question;
        return this;
    }

    public void prepareToSend() {
        addResponseData("newPlayerUUID", newPlayer.getUuid());
        addResponseData("newPlayerID", newPlayer.getId());
        addResponseData("newPlayerName", newPlayer.getName());
    }
}
