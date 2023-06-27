package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class DownloadTestData extends WebSocketData {
    @WSDataOutgoing
    private String content = null;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
