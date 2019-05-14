package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import game.actors.Answer;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.AnswerUpdate;

@MessageType("AnswerResponse")
public class AnswerResponse extends Message {
    private static Logger log = Logger.getLogger(AnswerResponse.class);

    private String answerStr = "";
    private String playerUUID = "";

    public void process() {
        Game currentGame = GameManager.getInstance().getCurrentGame();

        // Add response values to this response

        // Send off push request to listeners
        Answer answer = new Answer()
                .answerValue(answerStr)
                .player(currentGame.findPlayer(playerUUID));

        currentGame.updateAnswer(answer);

        AnswerUpdate answerUpdate = Message.create(AnswerUpdate.class);
        answerUpdate.answer(answer);
        answerUpdate.sendTo(Message.ALL_ADMINS);

        // Send response
        handleResponse();
    }

    public void populate(JSONObject jsonObject) {
        this.answerStr = jsonObject.getString("_answerValue");
        this.playerUUID = jsonObject.getString("_playerUUID");
    }
}
