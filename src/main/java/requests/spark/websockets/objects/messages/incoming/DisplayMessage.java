package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.ClearGameScreen;
import requests.spark.websockets.objects.messages.outgoing.DisplayGameMessage;
import requests.spark.websockets.objects.messages.outgoing.UpdateScore;

@MessageType("DisplayMessage")
public class DisplayMessage extends Message {
    private static Logger log = Logger.getLogger(DisplayMessage.class);

    private String message = "";

    public void process() {
        DisplayGameMessage displayGameMessage = Message.create(DisplayGameMessage.class);
        displayGameMessage.message(message);
        displayGameMessage.prepareToSend();
        displayGameMessage.sendTo(Message.ALL_ADMINS);

        log.info("Display a message");

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {
        this.message = jsonObject.getString("_message");
    }

    public void prepareToSend() {

    }
}
