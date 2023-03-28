package requests.spark.websockets.objects;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import requests.spark.websockets.objects.messages.dataobjects.WebSocketData;

public class Message {
    private static Logger log = AppLogger.logger();

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
}
