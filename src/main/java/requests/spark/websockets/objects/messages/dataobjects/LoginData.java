package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataIncoming;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class LoginData extends WebSocketData {
    // INCOMING
    @WSDataIncoming
    private String username = null;

    @WSDataIncoming
    private String password = null;

    // OUTGOING
    @WSDataOutgoing
    private Boolean acceptedAuth = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAcceptedAuth() {
        return acceptedAuth;
    }

    public void setAcceptedAuth(Boolean acceptedAuth) {
        this.acceptedAuth = acceptedAuth;
    }
}
