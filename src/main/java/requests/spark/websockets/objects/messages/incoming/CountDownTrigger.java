package requests.spark.websockets.objects.messages.incoming;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.outgoing.StartCountDown;

// This is sent by the admin to trigger the next question
@MessageType("CountDownTrigger")
public class CountDownTrigger extends Message {
    private static Logger log = Logger.getLogger(CountDownTrigger.class);

    public void process() {
        StartCountDown startCountDown = Message.create(StartCountDown.class);
        startCountDown.sendTo(Message.ALL_PLAYERS);

        handleResponse();
    }
}
