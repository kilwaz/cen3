package requests.spark.websockets;

import data.model.objects.json.JSONContainer;
import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import requests.annotations.RequestName;
import requests.spark.websockets.objects.Message;

@WebSocket
@RequestName("ws")
public class WebSocketListener {
    private static Logger log = Logger.getLogger(WebSocketListener.class);

    @OnWebSocketConnect
    public void connected(Session session) {

    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        WebSocketManager.getInstance().removeSession(session);
    }

    @OnWebSocketMessage
    public void message(Session session, String rawMessage) {
        try {
            log.info("Incoming message " + rawMessage);

            JSONContainer messageContainer = new JSONContainer(rawMessage);

            Message message = Message.decode(messageContainer);
            if (message != null) {
                message.session(session);
                message.process();
            }
        } catch (Exception ex) {
            error.Error.GENERIC_WEBSOCKET_EXCEPTION.record().create(ex);
        }
    }
}