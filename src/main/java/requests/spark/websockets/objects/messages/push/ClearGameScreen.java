package requests.spark.websockets.objects.messages.push;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.ClearGameScreenData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("ClearGameScreen")
@WebSocketDataClass(ClearGameScreenData.class)
public class ClearGameScreen extends Message {
    private static Logger log = Logger.getLogger(ClearGameScreen.class);
}
