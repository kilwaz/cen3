package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class StartCountDownData extends WebSocketData {
    @WSDataOutgoing
    private Integer countDownSeconds;

    public StartCountDownData() {
        countDownSeconds = 5;
    }

    public Integer getCountDownSeconds() {
        return countDownSeconds;
    }

    public void setCountDownSeconds(Integer countDownSeconds) {
        this.countDownSeconds = countDownSeconds;
    }
}
