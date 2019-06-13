package requests.spark.websockets.objects.messages.incoming;

import game.Game;
import game.GameManager;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.ClearGameScreen;
import requests.spark.websockets.objects.messages.outgoing.UpdateScore;

@MessageType("ClearScreen")
public class ClearScreen extends Message {
    private static Logger log = Logger.getLogger(ClearScreen.class);

    private String message = "";

    public void process() {
        ClearGameScreen clearGameScreen = Message.create(ClearGameScreen.class);
        clearGameScreen.sendTo(Message.ALL_ADMINS);
        clearGameScreen.prepareToSend();

        log.info("Clearing screen");

        handleResponse();
    }

    public void prepareToSend() {

    }
}
