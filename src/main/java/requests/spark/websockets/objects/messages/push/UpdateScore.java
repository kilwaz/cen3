package requests.spark.websockets.objects.messages.push;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.UpdateScoreData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("UpdateScore")
@WebSocketDataClass(UpdateScoreData.class)
public class UpdateScore extends Message {
    private static Logger log = Logger.getLogger(UpdateScore.class);
}
