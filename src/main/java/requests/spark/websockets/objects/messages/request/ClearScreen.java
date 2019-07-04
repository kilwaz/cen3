package requests.spark.websockets.objects.messages.request;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.WebSocketAction;
import requests.spark.websockets.objects.messages.dataobjects.ClearGameScreenData;
import requests.spark.websockets.objects.messages.dataobjects.ClearScreenData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import requests.spark.websockets.objects.messages.push.ClearGameScreen;

@MessageType("ClearScreen")
@WebSocketDataClass(ClearScreenData.class)
public class ClearScreen extends Message {
    private static Logger log = Logger.getLogger(ClearScreen.class);

    public void process() {
        Message.push(ClearGameScreen.class, new ClearGameScreenData(), WebSocketAction.ALL_ADMINS);
        log.info("Clearing screen");
    }
}
