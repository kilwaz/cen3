package requests.spark.websockets.objects.messages.outgoing;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;


// This is sent by the server to the players with details of the next question
@MessageType("StartCountDown")
public class StartCountDown extends Message {
    private static Logger log = Logger.getLogger(StartCountDown.class);

    private Integer countDownSeconds = 5;

    public void prepareToSend() {
        addResponseData("countDownSeconds", countDownSeconds);
    }
}
