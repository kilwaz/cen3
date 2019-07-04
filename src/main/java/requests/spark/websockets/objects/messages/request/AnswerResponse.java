package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Answer;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.AnswerResponseData;
import requests.spark.websockets.objects.messages.dataobjects.AnswerUpdateData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.AnswerUpdate;

@MessageType("AnswerResponse")
@WebSocketDataClass(AnswerResponseData.class)
public class AnswerResponse extends Message {
    private static Logger log = Logger.getLogger(AnswerResponse.class);

    public void process() {
        AnswerResponseData answerResponseData = (AnswerResponseData) this.getWebSocketData();

        Game currentGame = GameManager.getInstance().getCurrentGame();

        // Send off push request to listeners
        Answer answer = new Answer()
                .answerValue(answerResponseData.getAnswerStr())
                .player(currentGame.findPlayer(answerResponseData.getPlayerUUID()));

        currentGame.updateAnswer(answer);

        Message.push(AnswerUpdate.class, new AnswerUpdateData(answer), WebSocketAction.ALL_ADMINS);
    }
}