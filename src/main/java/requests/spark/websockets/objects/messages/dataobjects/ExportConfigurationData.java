package requests.spark.websockets.objects.messages.dataobjects;

import requests.spark.websockets.objects.messages.dataitems.DownloadFileDataItem;
import requests.spark.websockets.objects.messages.mapping.WSDataOutgoing;
import requests.spark.websockets.objects.messages.mapping.WSDataTypeScriptClass;

public class ExportConfigurationData extends WebSocketData {
    @WSDataOutgoing
    @WSDataTypeScriptClass(DownloadFileDataItem.class)
    private DownloadFileDataItem downloadFile;

    public ExportConfigurationData() {
    }

    public ExportConfigurationData(DownloadFileDataItem downloadFile) {
        this.downloadFile = downloadFile;
    }

    public DownloadFileDataItem getDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(DownloadFileDataItem downloadFile) {
        this.downloadFile = downloadFile;
    }
}
