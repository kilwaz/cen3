package requests.spark.websockets.objects.messages.outgoing;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("HeartBeat")
public class HeartBeat extends Message {
    private static Logger log = Logger.getLogger(HeartBeat.class);

    public void prepareToSend() {
        addResponseData("hb", "true");
    }
}
