package requests.spark.websockets.objects.messages.request;

import game.Game;
import game.GameManager;
import game.actors.Answer;
import log.AppLogger;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.AnswerResponseData;
import requests.spark.websockets.objects.messages.dataobjects.AnswerUpdateData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("AnswerResponse")
@WebSocketDataClass(AnswerResponseData.class)
public class AnswerResponse extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        AnswerResponseData answerResponseData = (AnswerResponseData) this.getWebSocketData();

        Game currentGame = GameManager.getInstance().getCurrentGame();

        // Send off push request to listeners
        Answer answer = new Answer()
                .answerValue(answerResponseData.getAnswerValue())
                .player(currentGame.findPlayer(answerResponseData.getPlayerUUID()));

        currentGame.updateAnswer(answer);

        Push.message(PushMessage.ANSWER_UPDATE)
                .data(new AnswerUpdateData(answer))
                .to(Audience.ALL_ADMINS)
                .push();
    }
}