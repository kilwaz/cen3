package requests.spark.websockets.objects.messages.outgoing;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;


@MessageType("DisplayGameMessage")
public class DisplayGameMessage extends Message {
    private static Logger log = Logger.getLogger(DisplayGameMessage.class);

    private String message = "";

    public DisplayGameMessage message(String message) {
        this.message = message;
        return this;
    }

    public void prepareToSend() {
        addResponseData("message", message);
    }
}
