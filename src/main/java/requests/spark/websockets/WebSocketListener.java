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

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@WebSocket
@RequestName("ws")
public class WebSocketListener {
    private static Logger log = Logger.getLogger(WebSocketListener.class);

    // Store sessions if you want to, for example, broadcast a message to all users
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    @OnWebSocketConnect
    public void connected(Session session) {
        sessions.add(session);
        log.info("Newly connected session - Total sessions " + sessions.size());
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        sessions.remove(session);
        log.info("Session closed - " + reason + " - Total sessions " + sessions.size());
    }

    @OnWebSocketMessage
    public void message(Session session, String rawMessage) throws IOException {
        log.info("Incoming message " + rawMessage);

        JSONContainer messageContainer = new JSONContainer(rawMessage);

        Message message = Message.decode(messageContainer);
        if (message != null) {
            message.process(session);
        }

        //session.getRemote().sendString(messageContainer.writeResponse()); // and send it back
    }
}