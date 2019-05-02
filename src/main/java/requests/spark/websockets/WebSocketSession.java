package requests.spark.websockets;

import org.eclipse.jetty.websocket.api.Session;

public class WebSocketSession {
    public static final int TYPE_PLAYER = 1;
    public static final int TYPE_ADMIN = 2;

    private Session session;
    private int type;

    public WebSocketSession() {

    }

    public WebSocketSession session(Session session) {
        this.session = session;
        return this;
    }

    public WebSocketSession type(int type) {
        this.type = type;
        return this;
    }

    public Session getSession() {
        return session;
    }

    public int getType() {
        return type;
    }
}
