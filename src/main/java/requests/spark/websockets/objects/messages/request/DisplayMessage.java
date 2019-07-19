package requests.spark.websockets.objects.messages.request;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.*;
import requests.spark.websockets.objects.messages.dataobjects.DisplayGameMessageData;
import requests.spark.websockets.objects.messages.dataobjects.DisplayMessageData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("DisplayMessage")
@WebSocketDataClass(DisplayMessageData.class)
public class DisplayMessage extends Message {
    private static Logger log = Logger.getLogger(DisplayMessage.class);

    public void process() {
        DisplayMessageData displayMessageData = (DisplayMessageData) this.getWebSocketData();

        Push.message(PushMessage.DISPLAY_GAME_MESSAGE)
                .data(new DisplayGameMessageData(displayMessageData.getMessage()))
                .to(Audience.ALL_ADMINS)
                .push();
    }
}
