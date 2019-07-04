package requests.spark.websockets.objects.messages.request;

import org.apache.log4j.Logger;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.ChatMessageData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("ChatMessage")
@WebSocketDataClass(ChatMessageData.class)
public class ChatMessage extends Message {
    private static Logger log = Logger.getLogger(ChatMessage.class);

    public void process() {
        ChatMessageData chatMessageData = (ChatMessageData) this.getWebSocketData();
        log.info("Our message is " + chatMessageData.getMessage());
    }
}
