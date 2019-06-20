package requests.spark.websockets.objects.messages.incoming;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.DisplayGameMessage;

@MessageType("DisplayMessage")
public class DisplayMessage extends Message {
    private static Logger log = Logger.getLogger(DisplayMessage.class);

    private String message = "";

    public void process() {
        DisplayGameMessage displayGameMessage = Message.create(DisplayGameMessage.class);
        displayGameMessage.message(message);
        displayGameMessage.prepareToSend();
        displayGameMessage.sendTo(Message.ALL_ADMINS);

        handleResponse();
    }

    public void populate(JSONObject jsonObject) {
        this.message = jsonObject.getString("_message");
    }
}
