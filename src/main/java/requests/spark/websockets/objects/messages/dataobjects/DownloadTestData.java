package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;

public class DownloadTestData extends WebSocketData {
    @WSDataOutgoing
    private String content = null;

    @WSDataOutgoing
    private String fileName = null;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
