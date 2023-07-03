package requests.spark.websockets.objects.messages.request;

import clarity.load.ConfigJSON;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.ExportConfigurationData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;
import utils.DownloadFileHelper;

@MessageType("ExportConfiguration")
@WebSocketDataClass(ExportConfigurationData.class)
public class ExportConfiguration extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        ExportConfigurationData exportConfigurationData = (ExportConfigurationData) this.getWebSocketData();

        JSONObject jsonConfiguration = new ConfigJSON().buildJSONFromCurrentConfiguration();

        DownloadFileHelper downloadFileHelper = new DownloadFileHelper();

        downloadFileHelper.downloadFileName("config.json");
        downloadFileHelper.content(jsonConfiguration.toString());

        exportConfigurationData.setDownloadFile(downloadFileHelper.buildDownloadFileDataItem());
    }
}
