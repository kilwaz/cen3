package requests.spark.websockets.objects;

import requests.spark.websockets.objects.messages.dataobjects.WebSocketData;

public class Push {
    private PushMessage pushMessage = null;
    private Audience audience = null;
    private WebSocketData webSocketData = null;
    private PushedMessage pushedMessage = null;

    private Push(PushMessage pushMessage) {
        this.pushMessage = pushMessage;
    }

    public static Push message(PushMessage pushMessage) {
        if (pushMessage != null) {
            return new Push(pushMessage);
        }

        return null;
    }

    public <WSData extends WebSocketData> Push data(WSData wsData) {
        webSocketData = wsData;
        return this;
    }

    public Push to(Integer... audiences) {
        audience = new Audience(audiences);
        return this;
    }

    public void push() {
        pushedMessage = new PushedMessage();
        webSocketData.setType(pushMessage.getType());
        pushedMessage.webSocketData(webSocketData);

        new WebSocketAction().push(this);
    }

    public PushMessage getPushMessage() {
        return pushMessage;
    }

    public PushedMessage getPushedMessage() {
        return pushedMessage;
    }

    public Audience getAudience() {
        return audience;
    }

    public WebSocketData getWebSocketData() {
        return webSocketData;
    }
}
