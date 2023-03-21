package requests.spark.websockets.listeners;

import data.model.objects.json.JSONContainer;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import requests.annotations.RequestName;
import requests.spark.websockets.WebSocketManager;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.WebSocketAction;

import java.io.IOException;

@WebSocket
@RequestName("ws")
public class AppCoreListener {
    private static Logger log = AppLogger.logger();

    // New connections trigger here
    @OnWebSocketConnect
    public void connected(Session session) {
        log.info("New connected session " + session.getRemoteAddress());
    }

    // Closed connections trigger here
    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        log.info("Closed connected session " + session.getRemoteAddress());
        WebSocketManager.getInstance().removeSession(session);
    }

    // Incoming messages if it is a byte buffer
    @OnWebSocketMessage
    public void handleBinaryMessage(Session session, byte[] buffer, int offset, int length) throws IOException {
        log.info("New Binary Message Received " + length + " (Server did nothing with this message)");
    }

    // Incoming messages reach the server via this method
    @OnWebSocketMessage
    public void message(Session session, String rawMessage) {
        try {
            log.info("-> Incoming message " + rawMessage);

            JSONContainer messageContainer = new JSONContainer(rawMessage);

            WebSocketAction webSocketAction = new WebSocketAction();
            Message message = webSocketAction.request(messageContainer);

            if (message != null) {
                message.getWebSocketData().setSession(session);
                message.process();
                JSONContainer responseContainer = webSocketAction.response(message);
                if (session.isOpen()) {
                    log.info("<- Outgoing message " + responseContainer.writeResponse());
                    session.getRemote().sendString(responseContainer.writeResponse());
                }
            }
        } catch (Exception ex) {
            error.Error.GENERIC_WEBSOCKET_EXCEPTION.record().create(ex);
        }
    }
}