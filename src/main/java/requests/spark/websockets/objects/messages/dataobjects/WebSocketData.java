package requests.spark.websockets.objects.messages.dataobjects;

import org.eclipse.jetty.websocket.api.Session;
import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class WebSocketData {
    @WSDataIncoming @WSDataOutgoing
    private String type;
    @WSDataIncoming @WSDataOutgoing
    private String callBackUUID;
    private Session session;

    public WebSocketData() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCallBackUUID() {
        return callBackUUID;
    }

    public void setCallBackUUID(String callBackUUID) {
        this.callBackUUID = callBackUUID;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
