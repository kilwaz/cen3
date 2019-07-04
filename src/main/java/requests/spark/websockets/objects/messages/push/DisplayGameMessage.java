package requests.spark.websockets.objects.messages.push;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;

@MessageType("DisplayGameMessage")
public class DisplayGameMessage extends Message {
    private static Logger log = Logger.getLogger(DisplayGameMessage.class);
}
