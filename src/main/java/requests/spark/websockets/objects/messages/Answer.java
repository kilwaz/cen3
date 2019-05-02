package requests.spark.websockets.objects.messages;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("Answer")
public class Answer extends Message {
    private static Logger log = Logger.getLogger(Answer.class);

    private String answer = "";

    public void process() {
        // Perform required action
//        Game currentGame = GameManager.getInstance().getCurrentGame();


        // Add response values
//        addResponseData("playerUUID", newPlayer.getUuid());

        log.info("An answer has been sent " + this.answer);

        // Send response
        handleResponse();
    }

    public void populate(JSONObject jsonObject) {
        this.answer = jsonObject.getString("_answer");
    }
}
