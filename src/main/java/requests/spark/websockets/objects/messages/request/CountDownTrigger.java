package requests.spark.websockets.objects.messages.request;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.CountDownTriggerData;
import requests.spark.websockets.objects.messages.dataobjects.StartCountDownData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

// This is sent by the admin to trigger the next question
@MessageType("CountDownTrigger")
@WebSocketDataClass(CountDownTriggerData.class)
public class CountDownTrigger extends Message {
    private static Logger log = Logger.getLogger(CountDownTrigger.class);

    public void process() {
        Push.message(PushMessage.START_COUNT_DOWN)
                .data(new StartCountDownData())
                .to(Audience.ALL_PLAYERS)
                .push();
    }
}
