package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class ClearGameScreenData extends WebSocketData {
    @WSDataOutgoing
    private Boolean clear = true;

    public ClearGameScreenData() {

    }

    public Boolean getClear() {
        return clear;
    }

    public void setClear(Boolean clear) {
        this.clear = clear;
    }
}
