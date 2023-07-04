package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class DownloadFileDataItem extends JSONWeb {
    @WSDataReference()
    private String content = null;

    @WSDataReference()
    private String fileName = null;

    public DownloadFileDataItem(String content, String fileName) {
        this.content = content;
        this.fileName = fileName;
    }

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
