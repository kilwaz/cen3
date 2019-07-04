package requests.spark.websockets.objects.messages.request;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.CountDownTriggerData;
import requests.spark.websockets.objects.messages.dataobjects.StartCountDownData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.StartCountDown;

// This is sent by the admin to trigger the next question
@MessageType("CountDownTrigger")
@WebSocketDataClass(CountDownTriggerData.class)
public class CountDownTrigger extends Message {
    private static Logger log = Logger.getLogger(CountDownTrigger.class);

    public void process() {
        Message.push(StartCountDown.class, new StartCountDownData(), WebSocketAction.ALL_PLAYERS);
    }
}
