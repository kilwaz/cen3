package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class EchoData extends WebSocketData {
    @WSDataOutgoing
    private String echoResponse = null;

    public String getEchoResponse() {
        return echoResponse;
    }

    public void setEchoResponse(String echoResponse) {
        this.echoResponse = echoResponse;
    }
}
