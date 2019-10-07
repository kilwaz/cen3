package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class EchoPushData extends WebSocketData {
    @WSDataOutgoing
    private String echoPushedMessage;

    public EchoPushData(String pushedText) {
        this.echoPushedMessage = pushedText;
    }

    public String getEchoPushedMessage() {
        return echoPushedMessage;
    }

    public void setEchoPushedMessage(String echoPushedMessage) {
        this.echoPushedMessage = echoPushedMessage;
    }
}