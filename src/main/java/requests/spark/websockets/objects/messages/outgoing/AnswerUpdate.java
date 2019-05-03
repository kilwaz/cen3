package requests.spark.websockets.objects.messages.outgoing;

import game.actors.Answer;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("AnswerUpdate")
public class AnswerUpdate extends Message {
    private static Logger log = Logger.getLogger(AnswerUpdate.class);

    private Answer answer = null;

    public AnswerUpdate answer(Answer answer) {
        this.answer = answer;
        return this;
    }

    public void prepareToSend() {
        addResponseData("answerUUID", answer.getUuid());
        addResponseData("playerUUID", answer.getPlayer().getUuid());
        addResponseData("answerValue", answer.getAnswerValue());
    }
}
