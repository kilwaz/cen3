package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class HeartBeatData extends WebSocketData {
    @WSDataOutgoing
    private Boolean hb = true;

    public HeartBeatData(){

    }

    public Boolean getHb() {
        return hb;
    }

    public void setHb(Boolean hb) {
        this.hb = hb;
    }
}
