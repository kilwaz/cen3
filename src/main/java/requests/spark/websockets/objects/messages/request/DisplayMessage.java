package requests.spark.websockets.objects.messages.request;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.DisplayGameMessageData;
import requests.spark.websockets.objects.messages.dataobjects.DisplayMessageData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.DisplayGameMessage;

@MessageType("DisplayMessage")
@WebSocketDataClass(DisplayMessageData.class)
public class DisplayMessage extends Message {
    private static Logger log = Logger.getLogger(DisplayMessage.class);

    public void process() {
        DisplayMessageData displayMessageData = (DisplayMessageData) this.getWebSocketData();
        Message.push(DisplayGameMessage.class, new DisplayGameMessageData(displayMessageData.getMessage()), WebSocketAction.ALL_ADMINS);
    }
}
