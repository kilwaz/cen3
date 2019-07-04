package requests.spark.websockets.objects;

import error.Error;
import org.apache.log4j.Logger;
import requests.spark.websockets.objects.messages.dataobjects.WebSocketData;

import java.lang.reflect.InvocationTargetException;

public class Message {
    private static Logger log = Logger.getLogger(Message.class);

    private WebSocketData webSocketData = null;

    public void process() {

    }

    public Message webSocketData(WebSocketData webSocketData) {
        this.webSocketData = webSocketData;
        return this;
    }

    public WebSocketData getWebSocketData() {
        return webSocketData;
    }

    public static <WSMessage extends Message, WSData extends WebSocketData> void push(Class<WSMessage> clazz, WSData wsData, int audience) {
        if (clazz != null) {
            WSMessage message = null;
            try {
                message = clazz.getConstructor().newInstance();
                MessageType messageType = clazz.getAnnotation(MessageType.class);
                wsData.setType(messageType.value());
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                Error.GENERIC_WEBSOCKET_EXCEPTION.record().create(ex);
            }

            message.push(audience);
        }
    }

    public void push(int audience) {
        new WebSocketAction().push(this, audience);
    }
}
