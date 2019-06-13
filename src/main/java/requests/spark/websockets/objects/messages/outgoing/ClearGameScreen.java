package requests.spark.websockets.objects.messages.outgoing;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;


@MessageType("ClearGameScreen")
public class ClearGameScreen extends Message {
    private static Logger log = Logger.getLogger(ClearGameScreen.class);

    public void prepareToSend() {
        addResponseData("clear", true);
    }
}
