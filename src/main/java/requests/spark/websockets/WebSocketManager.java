package requests.spark.websockets;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebSocketManager {
    private static WebSocketManager instance;
    private static Logger log = Logger.getLogger(WebSocketManager.class);

    private List<WebSocketSession> admins = new ArrayList<>();
    private List<WebSocketSession> players = new ArrayList<>();
    private List<WebSocketSession> allSessions = new ArrayList<>();
    private HashMap<Session, WebSocketSession> sessionQuickReference = new HashMap<>();

    private WebSocketManager() {
        instance = this;
    }

    public static WebSocketManager getInstance() {
        if (instance == null) {
            instance = new WebSocketManager();
        }
        return instance;
    }

    public void addSession(WebSocketSession webSocketSession) {
        allSessions.add(webSocketSession);
        sessionQuickReference.put(webSocketSession.getSession(), webSocketSession);

        if (webSocketSession.getType() == WebSocketSession.TYPE_ADMIN) {
            admins.add(webSocketSession);
        } else if (webSocketSession.getType() == WebSocketSession.TYPE_PLAYER) {
            players.add(webSocketSession);
        }
    }

    public void removeSession(Session session) {
        WebSocketSession webSocketSession = sessionQuickReference.get(session);

        if (webSocketSession != null) {
            admins.remove(webSocketSession);
            players.remove(webSocketSession);
            allSessions.remove(webSocketSession);
            sessionQuickReference.remove(session);
        }
    }

    public List<WebSocketSession> getAdmins() {
        return List.copyOf(admins);
    }

    public List<WebSocketSession> getPlayers() {
        return List.copyOf(players);
    }

    public List<WebSocketSession> getAllSessions() {
        return List.copyOf(allSessions);
    }
}
