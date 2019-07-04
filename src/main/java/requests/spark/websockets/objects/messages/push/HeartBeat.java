package requests.spark.websockets.objects.messages.push;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.HeartBeatData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("HeartBeat")
@WebSocketDataClass(HeartBeatData.class)
public class HeartBeat extends Message {
    private static Logger log = Logger.getLogger(HeartBeat.class);
}
